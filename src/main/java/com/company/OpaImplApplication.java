package com.company;

import com.company.entity.Document;
import com.company.entity.Salary;
import com.company.service.DocumentService;
import com.company.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication @RequiredArgsConstructor
public class OpaImplApplication {
    private final SalaryService salaryService;
    private final DocumentService documentService;

    public static void main(String[] args) {
        SpringApplication.run(OpaImplApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
          salaryService.save(new Salary(null, "farid", "4900"));
          salaryService.save(new Salary(null, "alice", "2500"));
          salaryService.save(new Salary(null, "bob", "4900"));
          salaryService.save(new Salary(null, "david", "2800"));
          salaryService.save(new Salary(null, "carol", "1200"));

          documentService.save(new Document(null, "farid document 1", "farid"));
          documentService.save(new Document(null, "farid document 2", "farid"));
          documentService.save(new Document(null, "alice document 1", "alice"));
          documentService.save(new Document(null, "alice document 2", "alice"));
          documentService.save(new Document(null, "bob document 1", "bob"));
          documentService.save(new Document(null, "david document 1", "david"));
          documentService.save(new Document(null, "carol document 1", "carol"));
        };
    }

}
