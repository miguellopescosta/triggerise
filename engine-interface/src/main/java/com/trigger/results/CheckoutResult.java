package com.trigger.results;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class CheckoutResult {

    private List<String> names;
    private BigDecimal price;
    private Status message;

    public CheckoutResult(List<String> names, BigDecimal price) {
        this.names = names;
        this.price = price;
    }

}
