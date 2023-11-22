package com.tecsup.petclinic.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.OwnerTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class OwnerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllOwners() throws Exception {
        this.mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id").exists()); // Assuming there is at least one owner
    }

    @Test
    void findOwnerById() throws Exception {
        mockMvc.perform(get("/owners/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void createOwner() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        String city = "Anytown";
        String telephone = "123-456-7890";

        OwnerTO newOwnerTO = new OwnerTO();
        newOwnerTO.setFirstName(firstName);
        newOwnerTO.setLastName(lastName);
        newOwnerTO.setAddress(address);
        newOwnerTO.setCity(city);
        newOwnerTO.setTelephone(telephone);

        mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(firstName)))
                .andExpect(jsonPath("$.lastName", is(lastName)))
                .andExpect(jsonPath("$.address", is(address)))
                .andExpect(jsonPath("$.city", is(city)))
                .andExpect(jsonPath("$.telephone", is(telephone)));
    }

    @Test
    void updateOwner() throws Exception {
        String newFirstName = "Jane";
        String newLastName = "Smith";
        String newAddress = "456 Oak St";
        String newCity = "Newtown";
        String newTelephone = "555-5678";

        OwnerTO newOwnerTO = new OwnerTO();
        newOwnerTO.setFirstName(newFirstName);
        newOwnerTO.setLastName(newLastName);
        newOwnerTO.setAddress(newAddress);
        newOwnerTO.setCity(newCity);
        newOwnerTO.setTelephone(newTelephone);

        // Create a new owner
        ResultActions createAction = mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        // Extract the ID of the created owner
        String response = createAction.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        // Update the owner
        mockMvc.perform(put("/owners/" + id)
                        .content(om.writeValueAsString(newOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(newFirstName)))
                .andExpect(jsonPath("$.lastName", is(newLastName)))
                .andExpect(jsonPath("$.address", is(newAddress)))
                .andExpect(jsonPath("$.city", is(newCity)))
                .andExpect(jsonPath("$.telephone", is(newTelephone)));
    }

    @Test
    void deleteOwner() throws Exception {
        String firstName = "Alice";
        String lastName = "Johnson";
        String address = "789 Pine St";
        String city = "Villagetown";
        String telephone = "555-8765";

        OwnerTO newOwnerTO = new OwnerTO();
        newOwnerTO.setFirstName(firstName);
        newOwnerTO.setLastName(lastName);
        newOwnerTO.setAddress(address);
        newOwnerTO.setCity(city);
        newOwnerTO.setTelephone(telephone);

        // Create a new owner
        ResultActions createAction = mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        // Extract the ID of the created owner
        String response = createAction.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        // Delete the owner
        mockMvc.perform(delete("/owners/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
