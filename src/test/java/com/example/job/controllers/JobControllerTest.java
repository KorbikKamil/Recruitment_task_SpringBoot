package com.example.job.controllers;

import com.example.job.controller.JobController;
import com.example.job.model.Job;
import com.example.job.repository.JobRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.DataInput;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JobRepository jobRepository;

    @Test
    void shouldGetJobs() throws Exception {
        // given

        //when
        MvcResult mvcResult = mockMvc.perform(get("/jobs"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        JSONObject jsonObj = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONArray jsonArray = jsonObj.getJSONArray("content");
        List<Job> jobsList = Arrays.asList(objectMapper.readValue(jsonArray.toString(), Job[].class));
        for(Job j : jobsList)
            System.out.println(j);
    }
}
