package com.example.sunbaseassignment.Service;

import com.example.sunbaseassignment.Dto.Request.customerRequestDto;
import com.example.sunbaseassignment.Dto.Responce.customerResponseDto;

public interface customerService {

    customerResponseDto createCustomer(customerRequestDto customerRequestDto);
}
