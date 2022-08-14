package com.company.api;

import com.company.entity.Salary;
import com.company.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/salary")
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryService salaryService;

    @GetMapping("/{username}")
    public Salary getSalary(@PathVariable String username){
        return salaryService.findSalaryByUsername(username);
    }
}
