package com.example.job.controllers;

import com.example.job.model.Job;
import com.example.job.repository.JobRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        assertEquals(3, jobsList.size());
        assertEquals("job1", jobsList.get(0).getName());
    }

    @Test
    void shouldAddPost() throws Exception {
        // given
        Job job = new Job("job4");
        String inputJson = objectMapper.writeValueAsString(job);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andReturn();
        //then
        assertEquals(201, mvcResult.getResponse().getStatus());
        Job returnedJob = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Job.class);
        assertEquals(4, returnedJob.getId());
        assertEquals("job4", returnedJob.getName());
    }
}
