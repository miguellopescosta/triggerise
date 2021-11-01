package com.trigger.rest;

import com.trigger.StoreEngine;
import com.trigger.parameters.CheckoutParameters;
import com.trigger.rest.errorhandling.InvalidParametersException;
import com.trigger.rest.mapper.Mapper;
import com.trigger.rest.views.errors.Status;
import com.trigger.rest.views.request.CheckoutRequest;
import com.trigger.rest.views.response.ResponseWithData;
import com.trigger.rest.views.response.CheckoutResponse;
import com.trigger.results.CheckoutResult;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class StoreRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(StoreRestApi.class);
    private final StoreEngine engine;

    @Autowired
    public StoreRestApi(StoreEngine engine) {
        this.engine = engine;
    }

    @GetMapping("/pricing")
    public ResponseWithData<Map<String, Double>> pricing() {

        /* For this exercise, we assume pricing rules have already been set, so we build one */
        Map<String, Double> pricingRules = new HashMap<>();
        pricingRules.put("MUG", 4.00);
        pricingRules.put("TSHIRT", 21.00);
        pricingRules.put("USBKEY", 10.00);

        return new ResponseWithData<>(pricingRules, new Status("Success","Ok"));
    }

    @SneakyThrows
    @PostMapping("/checkout")
    public ResponseWithData<CheckoutResponse> checkout(@RequestBody CheckoutRequest checkoutRequest) {

        LOG.info("Request received: " + checkoutRequest.toString());

        /* Mapping values to assigned engine interface */
        CheckoutParameters params = Mapper.getInstance().map(checkoutRequest, CheckoutParameters.class);

        String errors = null;

        /* For this exercise, we assume pricing rules have already been set, so we build one */
        Map<String, Double> pricingRules = new HashMap<>();
        pricingRules.put("MUG", 4.00);
        pricingRules.put("TSHIRT", 21.00);
        pricingRules.put("USBKEY", 10.00);

        params.setPricingRules(pricingRules);

        /* Validate received parameters and throw exception invalid parameters if a field is null */
        if (!params.validateParameters()) {
            errors = "No codes or pricing rules founds!";
            throw new InvalidParametersException(errors);
        }

        /* We invoke the engine interface to obtain the checkout result and immediately map to the response */
        CheckoutResult result = engine.checkout(params);

        CheckoutResponse checkoutResponse = Mapper.getInstance().map(result, CheckoutResponse.class);

        Status status = Mapper.getInstance().map(result.getMessage(), Status.class);

        /* We can add additional errors in the response */
        return new ResponseWithData<>(checkoutResponse, status);
    }

}
