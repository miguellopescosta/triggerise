package com.trigger.rest.views.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class CheckoutResponse implements Serializable {

    private List<String> names;
    private BigDecimal price;

}
