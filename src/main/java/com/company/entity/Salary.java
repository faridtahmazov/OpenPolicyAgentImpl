package com.company.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Salary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    String amount;
}
