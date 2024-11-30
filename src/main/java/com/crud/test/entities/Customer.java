package com.crud.test.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends CommonEntity{
    private String name;
    private String mobile;
    private String email;
    private String address;
}
