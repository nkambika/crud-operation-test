package com.crud.test.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto extends CommonResponse{
    private String name;
    private String mobile;
    private String email;
    private String address;
}
