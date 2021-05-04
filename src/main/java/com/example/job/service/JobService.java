package com.example.job.service;

import com.example.job.model.Job;
import com.example.job.repository.JobRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        jobRepository.save(new Job("job1"));
        jobRepository.save(new Job("job2"));
        jobRepository.save(new Job("job3"));
    }
}
