package com.trigger.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trigger.StoreEngine;
import com.trigger.parameters.CheckoutParameters;
import com.trigger.rest.mapper.Mapper;
import com.trigger.rest.views.request.CheckoutRequest;
import com.trigger.results.CheckoutResult;
import com.trigger.results.Status;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StoreRestApi.class)
class StoreRestApiTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ModelMapper mapper;

    @MockBean
    private StoreEngine storeEngine;

    @Test
    void obtainPricingList() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/pricing");
        /* This integrated test verifies if the response is 200, further validations might be added */
        MockHttpServletResponse response = mvc.perform(request).andExpect(status().isOk()).andReturn().getResponse();
        System.out.println("Result: " + response.getContentAsString());
    }

    @Test
    void buyItems() throws Exception {

        CheckoutRequest requestExample = new CheckoutRequest(List.of("MUG","MUG","TSHIRT","USBKEY"));
        CheckoutParameters params = Mapper.getInstance().map(requestExample, CheckoutParameters.class);
        CheckoutResult checkoutResult = new CheckoutResult(Arrays.asList("USBKEY (1 items)", "TSHIRT (1 items)", "MUG (2 items)"),
                new BigDecimal(35), new Status(new StringBuilder("Success"), new StringBuilder("Ok")));

        /* For this exercise, we assume pricing rules have already been set, so we build one */
        Map<String, Double> pricingRules = new HashMap<>();
        pricingRules.put("MUG", 4.00);
        pricingRules.put("TSHIRT", 21.00);
        pricingRules.put("USBKEY", 10.00);

        params.setPricingRules(pricingRules);

        when(storeEngine.checkout(params)).thenReturn(checkoutResult);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestExample))
                .accept(MediaType.APPLICATION_JSON);
        /* This integrated test verifies if the response is 200, further validations might be added */
        this.mvc.perform(request).andDo(print()).andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}