package com.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "base_entity") // if table name != class name
public class Customer extends  BaseEntity {

    @Id // primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment

    @Column(name="customer_id")
    private Long customerId;

    private String name;

    private String email;

    @Column(name="mobile_number")
    private String mobileNumber;

}