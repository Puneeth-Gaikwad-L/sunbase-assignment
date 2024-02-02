package com.example.sunbaseassignment.controllers;

import com.example.sunbaseassignment.Auth.Service.JwtService;
import com.example.sunbaseassignment.Dto.Request.customerRequestDto;
import com.example.sunbaseassignment.Dto.Responce.customerResponseDto;
import com.example.sunbaseassignment.Exceptions.customerAlreadyExists;
import com.example.sunbaseassignment.Exceptions.customerNotFound;
import com.example.sunbaseassignment.Service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class customerController {

    @Autowired
    customerService customerService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<customerResponseDto> createCustomer(@RequestBody customerRequestDto customerRequestDto){
        try{
            customerResponseDto customerResponseDto = customerService.createCustomer(customerRequestDto);
            return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
        }catch (customerAlreadyExists e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{emailId}")
    public ResponseEntity<customerResponseDto> updateCustomer(@PathVariable String emailId, @RequestBody customerRequestDto customerRequestDto){
        try{
            customerResponseDto customerResponseDto = customerService.updateCustomer(emailId, customerRequestDto);
            return new ResponseEntity<>(customerResponseDto, HttpStatus.ACCEPTED);
        }catch(customerNotFound e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCustomers")
    public ResponseEntity<Page<customerResponseDto>> getAllCustomers(@RequestParam int pageNo, @RequestParam int rowsCount, @RequestParam(required = false)String sortBy,@RequestParam(required = false) String searchBy){

        Page<customerResponseDto> customerList = customerService.getAllCustomers(pageNo, rowsCount, sortBy, searchBy);
        return new ResponseEntity<>(customerList, HttpStatus.FOUND);

    }

    @GetMapping("/get/{email}")
    public ResponseEntity<customerResponseDto> getCustomerWithId(@PathVariable String email){

        try{
            customerResponseDto customerResponseDto = customerService.getCustomerWithId(email);
            return new ResponseEntity<customerResponseDto>(customerResponseDto, HttpStatus.FOUND);
        }catch (customerNotFound e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@RequestParam String email){
        try {
            String result = customerService.deleteCustomer(email);
            return new ResponseEntity(result, HttpStatus.ACCEPTED);
        }catch (customerNotFound e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
