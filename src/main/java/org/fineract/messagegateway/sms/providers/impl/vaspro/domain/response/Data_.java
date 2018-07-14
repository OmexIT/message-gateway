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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sms_data",
    "sms_reporting"
})
public class Data_ implements Serializable
{

    @JsonProperty("sms_data")
    private List<SmsDatum> smsData = new ArrayList<SmsDatum>();
    @JsonProperty("sms_reporting")
    private SmsReporting smsReporting;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4460446343108345395L;

    @JsonProperty("sms_data")
    public List<SmsDatum> getSmsData() {
        return smsData;
    }

    @JsonProperty("sms_data")
    public void setSmsData(List<SmsDatum> smsData) {
        this.smsData = smsData;
    }

    @JsonProperty("sms_reporting")
    public SmsReporting getSmsReporting() {
        return smsReporting;
    }

    @JsonProperty("sms_reporting")
    public void setSmsReporting(SmsReporting smsReporting) {
        this.smsReporting = smsReporting;
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
        return new ToStringBuilder(this).append("smsData", smsData).append("smsReporting", smsReporting).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(smsReporting).append(additionalProperties).append(smsData).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data_) == false) {
            return false;
        }
        Data_ rhs = ((Data_) other);
        return new EqualsBuilder().append(smsReporting, rhs.smsReporting).append(additionalProperties, rhs.additionalProperties).append(smsData, rhs.smsData).isEquals();
    }

}
