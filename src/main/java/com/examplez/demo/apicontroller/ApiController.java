package com.examplez.demo.apicontroller;

import com.examplez.demo.apigatewayservice.ApiGatewayService;
import com.examplez.demo.github.GitHubService;
import com.examplez.demo.postmanservice.PostmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ApiController {

    @Autowired
    private GitHubService gitHubService;

    @Autowired
    private PostmanService postmanService;

    @Autowired
    private ApiGatewayService apiGatewayService;

    @GetMapping("/update-api")
    public String updateApi() {
        try {
            // Step 1: Fetch OpenAPI spec from GitHub
            String openApiSpec = gitHubService.fetchOpenApiSpec();

            // Step 2: Update Postman collection
            postmanService.updatePostmanCollection(openApiSpec);

            // Step 3: Update AWS API Gateway
            apiGatewayService.updateApiGateway(openApiSpec);

            return "API updated successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to update API: " + e.getMessage();
        }
    }

}
