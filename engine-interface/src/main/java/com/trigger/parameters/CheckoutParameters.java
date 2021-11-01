package com.trigger.parameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckoutParameters extends ParametersValidation {

    /* EXTENDS ParametersValidation IN ORDER TO CHECK FOR NULLS VIA REFLECTION */

    private List<String> codes;
    private Map<String, Double> pricingRules;

}
