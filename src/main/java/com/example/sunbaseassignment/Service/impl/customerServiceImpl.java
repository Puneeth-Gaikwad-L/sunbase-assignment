package com.example.sunbaseassignment.Service.impl;

import com.example.sunbaseassignment.Dto.Request.customerRequestDto;
import com.example.sunbaseassignment.Dto.Responce.customerResponseDto;
import com.example.sunbaseassignment.Exceptions.customerAlreadyExists;
import com.example.sunbaseassignment.Exceptions.customerNotFound;
import com.example.sunbaseassignment.Service.customerService;
import com.example.sunbaseassignment.Transformers.CustomerTransformer;
import com.example.sunbaseassignment.models.Customer;
import com.example.sunbaseassignment.repository.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class customerServiceImpl implements customerService {

    @Autowired
    customerRepository customerRepository;

    @Override
     public customerResponseDto createCustomer(customerRequestDto customerRequestDto){

//      getting the customer with the given email for validation and to reduce redundancy
        Customer customer = customerRepository.findByEmail(customerRequestDto.getEmail());

        if (customer != null) {
//          id customer is not empty then there is an existing customer with same email already hence throw an error
            throw new customerAlreadyExists("found an existing account with the same email");
        }

//      adding the data inside DTO to customer abject
        customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);

//        assigning a unique ID
        customer.setUid(String.valueOf(UUID.randomUUID()));

        Customer savedCustomer = customerRepository.save(customer);      // save the customer obj to db

//        building the response DTO
        customerResponseDto customerResponseDto =CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
        customerResponseDto.setMessage("user Account created successfully");

        return customerResponseDto;
    }
    @Override
    public customerResponseDto updateCustomer(String emailId, customerRequestDto customerRequestDto){
//        validating the customer
        Customer existingCustomer = customerRepository.findByEmail(emailId);
        if (existingCustomer == null) {
//            the customer is not registered
            throw new customerNotFound("Account not found with " + emailId);
        }

//        check for the attributes that are having a value and update them

//        alternatively we can make the findByEmail return Optional<Customer> and use the .updateFields
//        method to update the fields that are not null
        if (customerRequestDto.getFirstName() != null){
            existingCustomer.setFirst_name(customerRequestDto.getFirstName());
        }
        if (customerRequestDto.getLastName() != null){
            existingCustomer.setLast_name(customerRequestDto.getLastName());
        }
        if (customerRequestDto.getStreet() != null){
            existingCustomer.setStreet(customerRequestDto.getStreet());
        }
        if (customerRequestDto.getAddress() != null){
            existingCustomer.setAddress(customerRequestDto.getAddress());
        }
        if (customerRequestDto.getCity() != null){
            existingCustomer.setCity(customerRequestDto.getCity());
        }
        if (customerRequestDto.getState() != null){
            existingCustomer.setState(customerRequestDto.getState());
        }
        if (customerRequestDto.getPhone() != null){
            existingCustomer.setPhone(customerRequestDto.getPhone());
        }
        if (customerRequestDto.getEmail() != null){
            existingCustomer.setEmail(customerRequestDto.getEmail());
        }

        Customer savedCustomer = customerRepository.save(existingCustomer);
        customerResponseDto customerResponseDto = CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
        customerResponseDto.setMessage("user info updated successfully");

        return customerResponseDto;
    }


    @Override
    public Page<customerResponseDto> getAllCustomers(int pageNo, int rowsCount, String sortBy, String searchBy){

//        this function will return a list of customers with the required number of rows

        Pageable currentPageWithRequiredRows;

        if (sortBy != null) {
//            if sort by is given
             currentPageWithRequiredRows = PageRequest.of(pageNo-1, rowsCount, Sort.by(sortBy));
        }else {
            currentPageWithRequiredRows = PageRequest.of(pageNo-1, rowsCount);
        }

        Page<Customer> customersPage = customerRepository.findAll(currentPageWithRequiredRows);
        return customersPage.map(this::convertToDto);
    }

    public customerResponseDto convertToDto(Customer customer){
        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }
    @Override
    public customerResponseDto getCustomerWithId(String email){
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new customerNotFound("no account found with the given email");
        }

        customerResponseDto customerResponseDto = CustomerTransformer.customerToCustomerResponseDto(customer);
        customerResponseDto.setMessage("account found with " + email);
        return customerResponseDto;
    }

    @Override
    public String deleteCustomer(String email){
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new customerNotFound("no account found with " + email);
        }
        customerRepository.deleteByEmail(email);

        return "account deleted";
    }
}
