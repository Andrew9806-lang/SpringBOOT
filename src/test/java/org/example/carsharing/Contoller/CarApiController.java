package org.example.carsharing.Contoller;

import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CarApiController {
    @Autowired
    private  MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCars() throws Exception{
        mockMvc.perform(get("/api/cars")).andExpect(status().isOk());
    }
    @Test
    public void car_negativeSeats_returns422WithViolations() throws Exception {
        mockMvc.perform(get("/api/cars")
                        .param("seats"))
                .andExpect(status().isUnprocessableContent())
                .andExpect(JsonPath("$.violations.length()").value(Matchers.greaterThanOrEqualTo(0)));
    }
    @Test
    public void availablity_negativeSeats_returns422WithViolations() throws Exception {

        mockMvc.perform(get("/api/cars")
                        .param("startAT","2025-12-17T12:00:00Z")
                        .param("seats",-5))
                .andExpect(status().isUnprocessableContent())
                .andExpect(JsonPath("$.violations.length()").value(Matchers.greaterThanOrEqualTo(0)));
    }
}
