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
package org.fineract.messagegateway.sms.providers.impl.vaspro.domain.response;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "transactionId",
    "correlator",
    "reason"
})
public class SmsDatum implements Serializable
{

    @JsonProperty("code")
    private Long code;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("correlator")
    private String correlator;
    @JsonProperty("reason")
    private String reason;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6067236255631279419L;

    @JsonProperty("code")
    public Long getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Long code) {
        this.code = code;
    }

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("correlator")
    public String getCorrelator() {
        return correlator;
    }

    @JsonProperty("correlator")
    public void setCorrelator(String correlator) {
        this.correlator = correlator;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
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
        return new ToStringBuilder(this).append("code", code).append("transactionId", transactionId).append("correlator", correlator).append("reason", reason).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(transactionId).append(reason).append(correlator).append(additionalProperties).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SmsDatum) == false) {
            return false;
        }
        SmsDatum rhs = ((SmsDatum) other);
        return new EqualsBuilder().append(transactionId, rhs.transactionId).append(reason, rhs.reason).append(correlator, rhs.correlator).append(additionalProperties, rhs.additionalProperties).append(code, rhs.code).isEquals();
    }

}
