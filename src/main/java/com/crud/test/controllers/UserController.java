package com.crud.test.controllers;

import com.crud.test.dtos.request.UserRequestDto;
import com.crud.test.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto requestDto){
        return userService.create(requestDto);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDto requestDto, @PathVariable Long id){
        return userService.update(requestDto,id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.delete(id);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return userService.get(id);
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(){
        return userService.getAll();
    }
}
