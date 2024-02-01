package com.example.sunbaseassignment.Service.impl;

import com.example.sunbaseassignment.Dto.Request.customerRequestDto;
import com.example.sunbaseassignment.Dto.Responce.customerResponseDto;
import com.example.sunbaseassignment.Service.customerService;
import com.example.sunbaseassignment.repository.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class customerServiceImpl implements customerService {

    @Autowired
    customerRepository customerRepository;

    @Override
     public customerResponseDto createCustomer(customerRequestDto customerRequestDto){
        return new customerResponseDto();
    }


}
