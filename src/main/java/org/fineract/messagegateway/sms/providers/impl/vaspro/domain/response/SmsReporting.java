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
    "total_submitted",
    "success",
    "failed"
})
public class SmsReporting implements Serializable
{

    @JsonProperty("total_submitted")
    private Long totalSubmitted;
    @JsonProperty("success")
    private Long success;
    @JsonProperty("failed")
    private Long failed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -7450082352627074268L;

    @JsonProperty("total_submitted")
    public Long getTotalSubmitted() {
        return totalSubmitted;
    }

    @JsonProperty("total_submitted")
    public void setTotalSubmitted(Long totalSubmitted) {
        this.totalSubmitted = totalSubmitted;
    }

    @JsonProperty("success")
    public Long getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Long success) {
        this.success = success;
    }

    @JsonProperty("failed")
    public Long getFailed() {
        return failed;
    }

    @JsonProperty("failed")
    public void setFailed(Long failed) {
        this.failed = failed;
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
        return new ToStringBuilder(this).append("totalSubmitted", totalSubmitted).append("success", success).append("failed", failed).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(failed).append(success).append(totalSubmitted).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SmsReporting) == false) {
            return false;
        }
        SmsReporting rhs = ((SmsReporting) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(failed, rhs.failed).append(success, rhs.success).append(totalSubmitted, rhs.totalSubmitted).isEquals();
    }

}
