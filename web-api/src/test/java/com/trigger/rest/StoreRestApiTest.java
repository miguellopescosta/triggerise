package com.trigger.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trigger.ConfigC;
import com.trigger.Operations.CheckoutOperation;
import com.trigger.StoreEngine;
import com.trigger.rest.views.request.CheckoutRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class StoreRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConfigC configC;

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private StoreEngine storeEngine;

    @Autowired
    private CheckoutOperation checkoutOperation;

    @Test
    void obtainPricingList() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/pricing");
        /* This integrated test verifies if the response is 200, further validations might be added */
        MockHttpServletResponse response = mockMvc.perform(request).andExpect(status().isOk()).andReturn().getResponse();
        System.out.println("Result: " + response.getContentAsString());
    }

    @Test
    void buyItems() throws Exception {

        CheckoutRequest requestExample = new CheckoutRequest(List.of("MUG","MUG","TSHIRT","USBKEY"));

        mockMvc.perform(post("/checkout")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestExample)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}