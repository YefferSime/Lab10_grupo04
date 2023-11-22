package com.tecsup.petclinic.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.SpecialtyTO;
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
public class SpecialtyControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllSpecialties() throws Exception {
        this.mockMvc.perform(get("/specialties"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id").exists()); // Assuming there is at least one specialty
    }

    @Test
    void findSpecialtyById() throws Exception {
        mockMvc.perform(get("/specialties/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void createSpecialty() throws Exception {
        String name = "Surgery";

        SpecialtyTO newSpecialtyTO = new SpecialtyTO();
        newSpecialtyTO.setName(name);

        mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(name)));
    }

    @Test
    void updateSpecialty() throws Exception {
        String newName = "Internal Medicine";

        SpecialtyTO newSpecialtyTO = new SpecialtyTO();
        newSpecialtyTO.setName(newName);

        // Create a new specialty
        ResultActions createAction = mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        // Extract the ID of the created specialty
        String response = createAction.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        // Update the specialty
        mockMvc.perform(put("/specialties/" + id)
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(newName)));
    }

    @Test
    void deleteSpecialty() throws Exception {
        String name = "dentistry";

        SpecialtyTO newSpecialtyTO = new SpecialtyTO();
        newSpecialtyTO.setName(name);

        // Create a new specialty
        ResultActions createAction = mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        // Extract the ID of the created specialty
        String response = createAction.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        // Delete the specialty
        mockMvc.perform(delete("/specialties/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
