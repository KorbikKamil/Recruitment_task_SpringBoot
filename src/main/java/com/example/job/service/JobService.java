package com.example.job.service;

import com.example.job.model.Job;
import com.example.job.model.JobPage;
import com.example.job.repository.JobRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Job> getJobs(JobPage jobPage){
        Sort sort = Sort.by(jobPage.getSortDirection(), jobPage.getSortBy());
        Pageable pageable = PageRequest.of(jobPage.getPage(),jobPage.getSize(),  sort);
        return jobRepository.findAll(pageable);
    }

    public Job addJob(Job job){
        return jobRepository.save(job);
    }

}
