package com.crud.test.utils;

import com.crud.test.dtos.request.UserRequestDto;
import com.crud.test.dtos.response.UserResponseDto;
import com.crud.test.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserUtility {
    public static User convertUserRequestDtoToUser(UserRequestDto userRequestDto){
        User user = new User();
        BeanUtils.copyProperties(userRequestDto, user, "id");
        return user;
    }
    public static UserResponseDto convertUserToUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto, "id");
        return userResponseDto;
    }
}
