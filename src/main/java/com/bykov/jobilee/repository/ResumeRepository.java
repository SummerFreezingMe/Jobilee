package com.bykov.jobilee.repository;

import com.bykov.jobilee.domain.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}