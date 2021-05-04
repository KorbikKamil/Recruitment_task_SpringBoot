package com.example.job.controller;

import com.example.job.model.Job;
import com.example.job.model.JobPage;
import com.example.job.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<Page<Job>> getJobs(JobPage jobPage){
        return new ResponseEntity<>(jobService.getJobs(jobPage), HttpStatus.OK);
    }
}
