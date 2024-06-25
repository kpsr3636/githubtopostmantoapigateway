package com.examplez.demo.github;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;



@Service
public class GitHubService {

    @Value("${github.repo.url}")
    private String githubRepoUrl;

    public String fetchOpenApiSpec() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(githubRepoUrl, String.class);
    }

}
