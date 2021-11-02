package com.trigger.parameters;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CheckoutParameters extends ParametersValidation {

    /* EXTENDS ParametersValidation IN ORDER TO CHECK FOR NULLS VIA REFLECTION */

    private List<String> codes;
    private Map<String, Double> pricingRules;

    public CheckoutParameters(List<String> codes) {
        super();
        this.codes = codes;
    }
}
