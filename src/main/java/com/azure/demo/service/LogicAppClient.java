package com.azure.demo.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LogicAppClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String LOGIC_APP_URL = "https://employeeintegrationc4c-la.azurewebsites.net:443/api/DisableEmployee/triggers/When_a_HTTP_request_is_received/invoke?api-version=2022-05-01&sp=%2Ftriggers%2FWhen_a_HTTP_request_is_received%2Frun&sv=1.0&sig=Ooc-mPw4Mtcre5A35H5p0ORIlm2-HkVPhaftBn3_5ps"; // Replace this!

    public void sendToLogicApp(String employeeId, String action) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> payload = new HashMap<>();
        payload.put("employeeId", employeeId);
        payload.put("action", action);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        String logicAppUrl = "https://employeeintegrationc4c-la.azurewebsites.net:443/api/DisableEmployee/triggers/When_a_HTTP_request_is_received/invoke?api-version=2022-05-01&sp=%2Ftriggers%2FWhen_a_HTTP_request_is_received%2Frun&sv=1.0&sig=Ooc-mPw4Mtcre5A35H5p0ORIlm2-HkVPhaftBn3_5ps";

        ResponseEntity<String> response = restTemplate.postForEntity(logicAppUrl, request, String.class);

   
    }
}
