package com.example.sunbaseassignment.Service;

import com.example.sunbaseassignment.Dto.Request.customerRequestDto;
import com.example.sunbaseassignment.Dto.Responce.customerResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface customerService {

    customerResponseDto createCustomer(customerRequestDto customerRequestDto,  boolean SyncDb);

    customerResponseDto updateCustomer(String email, customerRequestDto customerRequestDto);

    Page<customerResponseDto> getAllCustomers(int pageNo, int rowsCount, String sortBy, String searchBy);

    List<customerResponseDto> searchByCol(String searchBy, String searchQuery);

    customerResponseDto getCustomerWithId(String email);

    String deleteCustomer(String email);

}
