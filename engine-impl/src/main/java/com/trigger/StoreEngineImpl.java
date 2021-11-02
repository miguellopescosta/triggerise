package com.trigger;

import com.trigger.operations.CheckoutOperation;
import com.trigger.parameters.CheckoutParameters;
import com.trigger.results.CheckoutResult;
import org.springframework.stereotype.Component;

@Component
public class StoreEngineImpl implements StoreEngine {

    public CheckoutResult checkout(CheckoutParameters params) {
        CheckoutOperation checkout = new CheckoutOperation(params.getPricingRules());
        /* Equivalent to  (example: checkout.scan("mug").scan("mug").scan("t-shirt")) this is the builder pattern */
        params.getCodes().forEach(checkout::scan);
        /* Returns the total price of items */
        return checkout.total();
    }

}
