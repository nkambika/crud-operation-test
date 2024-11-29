package com.crud.test.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto extends CommonResponse{
    private String username;
    private String password;
}
