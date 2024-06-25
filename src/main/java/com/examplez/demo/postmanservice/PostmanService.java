package com.examplez.demo.postmanservice;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;



@Service
public class PostmanService {

    @Value("${postman.api.key}")
    private String postmanApiKey;

    @Value("${postman.collection.uid}")
    private String postmanCollectionUid;

    public void updatePostmanCollection(String collectionJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", postmanApiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(collectionJson, headers);
        restTemplate.exchange("https://api.getpostman.com/collections/" + postmanCollectionUid,
                HttpMethod.PUT, entity, String.class);
    }
}
