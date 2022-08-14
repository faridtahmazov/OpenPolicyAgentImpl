package com.company.repo;

import com.company.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, Long> {
    Optional<Salary> findByUsername(String username);
}
