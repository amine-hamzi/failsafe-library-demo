package com.example.failsafe.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class FailsafeDemoApplication implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${example.api.url}")
    private String url;

    public static void main(String[] args) {
        SpringApplication.run(FailsafeDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {

        ResponseEntity<String> res = FailsafeUtil.getNotNull(() -> restTemplate.getForEntity(url, String.class));
        log.info(res.getBody());
    }
}
