package com.example.sunbaseassignment.controllers;

import com.example.sunbaseassignment.Dto.Request.customerRequestDto;
import com.example.sunbaseassignment.Dto.Responce.customerResponseDto;
import com.example.sunbaseassignment.Exceptions.customerAlreadyExists;
import com.example.sunbaseassignment.Service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class customerController {

    @Autowired
    customerService customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<customerResponseDto> createCustomer(@RequestBody customerRequestDto customerRequestDto){
        try{
            customerResponseDto customerResponseDto = customerService.createCustomer(customerRequestDto);
            return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
        }catch (customerAlreadyExists e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
