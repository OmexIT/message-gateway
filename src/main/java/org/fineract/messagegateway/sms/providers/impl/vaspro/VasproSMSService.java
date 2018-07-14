/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.fineract.messagegateway.sms.providers.impl.vaspro;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.fineract.messagegateway.configuration.HostConfig;
import org.fineract.messagegateway.constants.MessageGatewayConstants;
import org.fineract.messagegateway.exception.MessageGatewayException;
import org.fineract.messagegateway.sms.domain.SMSBridge;
import org.fineract.messagegateway.sms.domain.SMSMessage;
import org.fineract.messagegateway.sms.providers.SMSProvider;
import org.fineract.messagegateway.sms.providers.impl.vaspro.domain.request.Contacts;
import org.fineract.messagegateway.sms.providers.impl.vaspro.domain.request.SMSRequest;
import org.fineract.messagegateway.sms.providers.impl.vaspro.domain.response.SMSResponse;
import org.fineract.messagegateway.sms.util.SmsMessageStatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service(value = "Vaspro")
public class VasproSMSService extends SMSProvider {

    private static final Logger logger = LoggerFactory.getLogger(VasproSMSService.class);

    private final String callBackUrl;

    @Autowired
    public VasproSMSService(final HostConfig hostConfig) {
        callBackUrl = String.format("%s://%s:%d/vaspro/report/", hostConfig.getProtocol(), hostConfig.getHostName(), hostConfig.getPort());
        logger.info("Registering call back to vaspro:" + callBackUrl);
    }

    @Override
    public void sendMessage(final SMSBridge smsBridgeConfig, final SMSMessage message) throws MessageGatewayException {
        //Based on message id, register call back. so that we get notification from Vaspro about message status
        try {
            String statusCallback = callBackUrl + message.getId();

            SMSRequest smsRequest = new SMSRequest();
            smsRequest.setApiKey(smsBridgeConfig.getConfigValue(MessageGatewayConstants.PROVIDER_AUTH_TOKEN));
            smsRequest.setCallbackURL(statusCallback);
            smsRequest.setMessage(message.getMessage());
            smsRequest.setShortCode(smsBridgeConfig.getPhoneNo());
            smsRequest.setPrefix("CO");

            String mobileNo = formatMobileNo(message.getMobileNumber(), smsBridgeConfig.getCountryCode());
            logger.info("Sending SMS to " + mobileNo + " ...");

            List<String> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(mobileNo);

            Contacts contacts = new Contacts();
            contacts.setRecipients(phoneNumbers);
            smsRequest.setContacts(contacts);

            SmsMessageStatusType smsStatus = SmsMessageStatusType.PENDING;
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<SMSRequest> request = new HttpEntity<SMSRequest>(smsRequest, headers);

            ResponseEntity<SMSResponse> responseEntity = restTemplate.postForEntity(
                    "http://api.vaspro.co/v2/BulkSMS/create", request, SMSResponse.class);

            SMSResponse smsResponse = responseEntity.getBody();
            logger.debug("VasproSMSService.sendMessage({}) - response: {}", message.getId(), smsResponse);

            if (smsResponse.getCode().equalsIgnoreCase("Success")) {
                smsStatus = SmsMessageStatusType.SENT;
            } else {
                smsStatus = SmsMessageStatusType.FAILED;
            }
            message.setDeliveryStatus(smsStatus.getValue());
            message.setDeliveryErrorMessage(smsResponse.getStatusDescription());
        } catch (Exception e) {
            logger.error("VasproSMSService.sendMessage({}) - Exception while sending message to {} with reason: {}",
                    message.getId(), message.getMobileNumber(), e.getMessage());
            message.setDeliveryStatus(SmsMessageStatusType.FAILED.getValue());
            message.setDeliveryErrorMessage(e.getMessage());
        }
    }

    private String formatMobileNo(String msisdn, String defaultRegion) throws NumberParseException {
        String formated = "";
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber proto = phoneUtil.parse(msisdn, defaultRegion);
        formated = phoneUtil.format(proto, PhoneNumberUtil.PhoneNumberFormat.E164);
        return formated.startsWith("+") ? formated.substring(1) : formated;
    }
}
