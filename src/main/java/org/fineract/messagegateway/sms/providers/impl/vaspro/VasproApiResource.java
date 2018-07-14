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

import org.fineract.messagegateway.sms.domain.SMSMessage;
import org.fineract.messagegateway.sms.providers.impl.vaspro.domain.response.SMSResponse;
import org.fineract.messagegateway.sms.repository.SmsOutboundMessageRepository;
import org.fineract.messagegateway.sms.util.SmsMessageStatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaspro")
public class VasproApiResource {

    private static final Logger logger = LoggerFactory.getLogger(VasproApiResource.class);

    private final SmsOutboundMessageRepository smsOutboundMessageRepository;

    @Autowired
    public VasproApiResource(final SmsOutboundMessageRepository smsOutboundMessageRepository) {
        this.smsOutboundMessageRepository = smsOutboundMessageRepository;
    }

    @RequestMapping(value = "/report/{messageId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateDeliveryStatus(@PathVariable("messageId") final Long messageId, @RequestBody final SMSResponse payload) {
        SMSMessage message = this.smsOutboundMessageRepository.findOne(messageId);
        if (message != null) {
            logger.info("Status Callback received from Vaspro for " + messageId + " with status:" + payload);

            if (payload.getCode().equalsIgnoreCase("Success")) {
                message.setDeliveryStatus(SmsMessageStatusType.DELIVERED.getValue());
            } else {
                message.setDeliveryStatus(SmsMessageStatusType.FAILED.getValue());
            }
            this.smsOutboundMessageRepository.save(message);
        } else {
            logger.info("Message with Message id " + messageId + " Not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
