package com.company.service;

import com.company.entity.Salary;
import com.company.repo.SalaryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service @RequiredArgsConstructor
public class SalaryService {
    private final SalaryRepo salaryRepo;

    public void save(Salary salary){
        salaryRepo.save(salary);
    }

    @PreAuthorize("@opaClient.allow('read', T(java.util.Map).of('type', 'salary', 'user', #username))")
    public Salary findSalaryByUsername(String username){
        log.info("user is fetching..");
        return salaryRepo.findByUsername(username)
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND));
    }
}
