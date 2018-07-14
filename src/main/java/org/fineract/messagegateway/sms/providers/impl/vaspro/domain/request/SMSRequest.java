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
package org.fineract.messagegateway.sms.providers.impl.vaspro.domain.request;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "apiKey",
    "shortCode",
    "message",
    "contacts",
    "prefix",
    "callbackURL"
})
public class SMSRequest implements Serializable
{

    @JsonProperty("apiKey")
    private String apiKey;
    @JsonProperty("shortCode")
    private String shortCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("contacts")
    private Contacts contacts;
    @JsonProperty("prefix")
    private String prefix;
    @JsonProperty("callbackURL")
    private String callbackURL;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8837383369619073193L;

    @JsonProperty("apiKey")
    public String getApiKey() {
        return apiKey;
    }

    @JsonProperty("apiKey")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @JsonProperty("shortCode")
    public String getShortCode() {
        return shortCode;
    }

    @JsonProperty("shortCode")
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("contacts")
    public Contacts getContacts() {
        return contacts;
    }

    @JsonProperty("contacts")
    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @JsonProperty("prefix")
    public String getPrefix() {
        return prefix;
    }

    @JsonProperty("prefix")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @JsonProperty("callbackURL")
    public String getCallbackURL() {
        return callbackURL;
    }

    @JsonProperty("callbackURL")
    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("apiKey", apiKey).append("shortCode", shortCode).append("message", message).append("contacts", contacts).append("prefix", prefix).append("callbackURL", callbackURL).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(shortCode).append(additionalProperties).append(prefix).append(callbackURL).append(apiKey).append(contacts).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SMSRequest) == false) {
            return false;
        }
        SMSRequest rhs = ((SMSRequest) other);
        return new EqualsBuilder().append(message, rhs.message).append(shortCode, rhs.shortCode).append(additionalProperties, rhs.additionalProperties).append(prefix, rhs.prefix).append(callbackURL, rhs.callbackURL).append(apiKey, rhs.apiKey).append(contacts, rhs.contacts).isEquals();
    }

}
