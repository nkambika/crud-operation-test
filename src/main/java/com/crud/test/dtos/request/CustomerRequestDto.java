package com.crud.test.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDto {
    private String name;
    private String mobile;
    private String email;
    private String address;
}
