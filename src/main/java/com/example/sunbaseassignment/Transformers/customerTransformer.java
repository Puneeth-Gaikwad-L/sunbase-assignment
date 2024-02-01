package com.example.sunbaseassignment.Transformers;

import com.example.sunbaseassignment.Dto.Request.customerRequestDto;
import com.example.sunbaseassignment.Dto.Responce.customerResponseDto;
import com.example.sunbaseassignment.models.Customer;

public class customerTransformer {

    public static Customer customerResponseToCustomer(customerRequestDto customerRequestDto){
        return Customer.builder()
                .first_name(customerRequestDto.getFirstName())
                .last_name(customerRequestDto.getLastName())
                .email(customerRequestDto.getEmail())
                .phone(customerRequestDto.getPhone())
                .city(customerRequestDto.getCity())
                .address(customerRequestDto.getAddress())
                .state(customerRequestDto.getState())
                .street(customerRequestDto.getStreet())
                .build();
    }

    public static customerResponseDto customerToCustomerResponce(Customer customer){
        return customerResponseDto.builder()
                .Uid(customer.getUid())
                .firstName(customer.getFirst_name())
                .lastName(customer.getLast_name())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .state(customer.getState())
                .street(customer.getStreet())
                .address(customer.getAddress())
                .joinedOn(customer.getJoinedOn())
                .phone(customer.getPhone())
                .build();
    }
}
