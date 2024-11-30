package com.crud.test.services;

import com.crud.test.dtos.request.UserRequestDto;
import com.crud.test.dtos.response.UserResponseDto;
import com.crud.test.entities.User;
import com.crud.test.exceptions.ResourceNotFoundException;
import com.crud.test.repositories.UserRepository;
import com.crud.test.utils.UserUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements CommonService<UserRequestDto, Long> {
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<?> create(UserRequestDto requestDto) {
        try{
            User user = UserUtility.convertUserRequestDtoToUser(requestDto);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("New user created successfully!!!");
        }catch (Exception e){
            log.error("Error occurred while creating new user ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> update(UserRequestDto userRequestDto, Long id) {
        try{
            User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with id: "+id+" is not found!!!"));
            BeanUtils.copyProperties(userRequestDto, user);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("User with id: "+id+" updated successfully!!!");
        }catch (Exception e){
            log.error("Error occurred while creating new user ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try{
            User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id: "+id+" is not found!"));
            user.setStatus(false);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("User with id: "+id+" deleted successfully");
        }catch (Exception e){
            log.error("Error occurred while deleting user with id: "+id,e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        try{
            User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id: "+id+" is not found!"));
            return ResponseEntity.status(HttpStatus.OK).body(UserUtility.convertUserToUserResponseDto(user));
        }catch (Exception e){
            log.error("Error occurred while fetching user with id: "+id,e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        try{
            List<User> userList = userRepository.findAll();
            List<UserResponseDto> responseDtoList = userList.stream().map(UserUtility::convertUserToUserResponseDto).toList();
            return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
        }catch (Exception e){
            log.error("Error occurred while fetching all users",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
