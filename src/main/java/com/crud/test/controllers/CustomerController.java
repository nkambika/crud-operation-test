package com.crud.test.controllers;

import com.crud.test.dtos.request.CustomerRequestDto;
import com.crud.test.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDto requestDto){
        return customerService.create(requestDto);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequestDto requestDto, @PathVariable Long id){
        return customerService.update(requestDto,id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        return customerService.delete(id);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        return customerService.get(id);
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCustomers(){
        return customerService.getAll();
    }
}
