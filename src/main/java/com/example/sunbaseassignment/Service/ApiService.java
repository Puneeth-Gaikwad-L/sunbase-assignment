package com.example.sunbaseassignment.Service;

import com.example.sunbaseassignment.Dto.Responce.ResponseFromSunBase;
import com.example.sunbaseassignment.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

@Service
public class ApiService {

    public String callApi(String apiUrl, String requestBody) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,  // Change this based on your API's HTTP method
                requestEntity,
                String.class
        );

        // You can handle the response as needed
        String responseBody = responseEntity.getBody();

        return responseBody;
    }

    public List<Object> getCustomers(String token, String apiUrl){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Object[]> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,  // Change this based on your API's HTTP method
                requestEntity,
                Object[].class
        );

        Object[] responseBody = responseEntity.getBody();

        return List.of(responseBody);

    }

    public static final String loginurl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

    public static final String customersApi = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

    public Object[] getToken(){
        String requestBody = "{ \"login_id\": \"test@sunbasedata.com\", \"password\": \"Test@123\" }";
        String token = callApi(loginurl, requestBody);
        String acessToken = token.substring(19, token.length()-3);
        List<Object> customers = getCustomers(acessToken, customersApi);

        Object[] customersReceived = customers.toArray();
        return customersReceived;
    }
}

