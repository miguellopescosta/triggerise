package com.trigger.Operations;


import com.trigger.results.CheckoutResult;
import com.trigger.results.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/* Checkout class - for the purpose of the project architecture it's defined as checkout operation */
@Service
public class CheckoutOperation extends Operation {

    private static final Logger LOG = LoggerFactory.getLogger(CheckoutOperation.class);
    private final Map<String, Integer> itemList;
    private final Map<String, Double> pricingRules;
    private final Status status;

    /* When using spring data repositories we can inject / autowired directly into the constructor */
    //private final PriceRepository repository;

    /* Autowired no needed on a single constructor. here a repository should be injected in order to perform database transactions or queries */
    public CheckoutOperation(Map<String, Double> pricingRules) {
        this.pricingRules = pricingRules;
        this.itemList = new HashMap<>();
        this.status = new Status();
    }


    /* This is redundant, could be a void method. this method adds items to the hashmap */
    public CheckoutOperation scan(String item) {

        if (itemList.containsKey(item)) {
            itemList.put(item, itemList.get(item) + 1);
        } else {
            itemList.put(item, 1);
        }
        return this;
    }

    public CheckoutResult total() {
        double price = 0L;
        List<String> items = new ArrayList<>();

        for (var entry : itemList.entrySet()) {
            if (pricingRules.containsKey(entry.getKey())) {
                items.add(entry.getKey() + " (" + entry.getValue() + " items)");
            } else {
                generateStatus(entry.getKey());
            }
            price += calculate(pricingRules.get(entry.getKey()), entry.getValue(), entry.getKey());
        }
        defineInfo();
        return new CheckoutResult(items, new BigDecimal(price), status);

    }

    private double calculate(Double price, Integer quantity, String item) {

        LOG.info("Calculating: " + price + ", " + quantity + ", " + item);

        if(item.equals("MUG") && quantity > 1){

            return price * (quantity/2) + price * (quantity%2);

        } else if(item.equals("TSHIRT") && quantity > 2) {

            return price * quantity * 0.7;

        } else return quantity != null && price != null ? price * quantity : 0L;

    }

    private void generateStatus(String info) {

        if (status.getMessage() == null) {
            status.setMessage(new StringBuilder("These items don't exist: " + info));
        } else {
            status.getMessage().append(" ,").append(info);
        }
    }

    private void defineInfo(){

        if (status.getMessage() != null) {
            status.setInfo(new StringBuilder("Invalid items on the list"));
        } else {
            status.setMessage(new StringBuilder("Ok"));
            status.setInfo(new StringBuilder("Success"));
        }
    }

}
