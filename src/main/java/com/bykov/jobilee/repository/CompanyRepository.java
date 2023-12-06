package com.bykov.jobilee.repository;

import com.bykov.jobilee.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}