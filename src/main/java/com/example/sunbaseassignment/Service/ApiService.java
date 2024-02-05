package com.example.sunbaseassignment.Service;

import com.example.sunbaseassignment.Dto.Responce.ResponseFromSunBase;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public List<ResponseFromSunBase> getCustomers(String token, String apiUrl){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ResponseFromSunBase[]> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,  // Change this based on your API's HTTP method
                requestEntity,
                ResponseFromSunBase[].class
        );

        ResponseFromSunBase[] responseBody = responseEntity.getBody();

        return List.of(responseBody);

    }
}

