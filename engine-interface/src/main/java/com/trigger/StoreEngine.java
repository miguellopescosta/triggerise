package com.trigger;

import com.trigger.parameters.CheckoutParameters;
import com.trigger.results.CheckoutResult;
import org.springframework.stereotype.Component;

@Component
public interface StoreEngine {

    CheckoutResult checkout(CheckoutParameters checkOutParameters);

}
