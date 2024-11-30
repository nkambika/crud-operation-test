package com.crud.test.utils;

import com.crud.test.dtos.request.CustomerRequestDto;
import com.crud.test.dtos.response.CustomerResponseDto;
import com.crud.test.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerUtility {
    public static Customer convertCustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDto, customer, "id");
        return customer;
    }
    public static CustomerRequestDto convertCustomerToCustomerRequestDto(Customer customer){
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        BeanUtils.copyProperties(customer, customerRequestDto, "id");
        return customerRequestDto;
    }
    public static CustomerResponseDto convertCustomerToCustomerResponseDto(Customer customer){
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        BeanUtils.copyProperties(customer, customerResponseDto);
        return customerResponseDto;
    }
}
