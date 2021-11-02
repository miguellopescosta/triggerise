package com.trigger.Operations;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutOperationTest {

    @Test
    void scan() {
        List<String> test = List.of("MUG", "MUG", "TSHIRT", "USBKEY");

        Map<String, Double> pricingRules = new HashMap<>();
        pricingRules.put("MUG", 4.00);
        pricingRules.put("TSHIRT", 21.00);
        pricingRules.put("USBKEY", 10.00);

        CheckoutOperation checkout = new CheckoutOperation(pricingRules);

        checkout.scan("MUG").scan("MUG").scan("TSHIRT").scan("USBKEY");

        for (String s : test) {
            assertTrue(checkout.getItemList().containsKey(s), "There was something wrong building up the item list!");
        }

    }

    @Test
    void total() {

        Map<String, Double> pricingRules = new HashMap<>();
        pricingRules.put("MUG", 4.00);
        pricingRules.put("TSHIRT", 21.00);
        pricingRules.put("USBKEY", 10.00);

        CheckoutOperation checkout = new CheckoutOperation(pricingRules);

        checkout.scan("MUG").scan("MUG").scan("TSHIRT").scan("USBKEY");

        assertEquals(checkout.total().getPrice(), new BigDecimal(4 + 21 + 10), "There was something wrong with the calculation!");

    }
}