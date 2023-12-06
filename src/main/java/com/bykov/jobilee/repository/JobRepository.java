package com.bykov.jobilee.repository;

import com.bykov.jobilee.domain.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}