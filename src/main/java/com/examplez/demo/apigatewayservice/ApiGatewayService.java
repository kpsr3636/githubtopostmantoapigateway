package com.examplez.demo.apigatewayservice;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@Service
public class ApiGatewayService {

    @Value("${aws.region}")
    private String awsRegion;

    public void updateApiGateway(String openApiSpec) throws IOException, InterruptedException {
        // Write the OpenAPI spec to a temporary file
        File tempFile = File.createTempFile("openapi", ".json");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(openApiSpec);
        }

        // Execute AWS CLI command to update API Gateway
        String command = String.format("aws apigateway import-rest-api --parameters mode=overwrite --body 'file://%s' --region %s",
                tempFile.getAbsolutePath(), awsRegion);
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
    }

}
