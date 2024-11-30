package com.crud.test.services;

import com.crud.test.dtos.request.CustomerRequestDto;
import com.crud.test.dtos.response.CustomerResponseDto;
import com.crud.test.entities.Customer;
import com.crud.test.exceptions.ResourceNotFoundException;
import com.crud.test.repositories.CustomerRepository;
import com.crud.test.utils.CustomerUtility;
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
public class CustomerService implements CommonService<CustomerRequestDto, Long> {
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<?> create(CustomerRequestDto customerRequestDto) {
        try {
            Customer customer = CustomerUtility.convertCustomerRequestDtoToCustomer(customerRequestDto);
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("New customer created successfully!!!");
        } catch (Exception e) {
            log.error("Error occurred while creating new customer ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> update(CustomerRequestDto customerRequestDto, Long id) {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " is not found!!!"));
            BeanUtils.copyProperties(customerRequestDto, customer);
            customer.setId(id);
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.OK).body("customer with id: " + id + " updated successfully!!!");
        } catch (Exception e) {
            log.error("Error occurred while updating existing customer ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " is not found!!!"));
        customer.setStatus(false);
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.OK).body("customer with id: " + id + " deleted successfully!!!");
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " is not found!!!"));
        return ResponseEntity.status(HttpStatus.OK).body(CustomerUtility.convertCustomerToCustomerResponseDto(customer));
    }

    @Override
    public ResponseEntity<?> getAll() {
        try {
            List<Customer> customerList = customerRepository.findAll();
            List<CustomerResponseDto> responseDtoList = customerList.stream().map(CustomerUtility::convertCustomerToCustomerResponseDto).toList();
            return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
        } catch (Exception e) {
            log.error("Error occurred while fetching existing customers ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
