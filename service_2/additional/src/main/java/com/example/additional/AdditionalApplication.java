package com.example.additional;

import com.example.additional.config.RibbonClientConfig;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RibbonClients(defaultConfiguration = RibbonClientConfig.class)
public class AdditionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdditionalApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        try {
            HttpClient httpClient = HttpClients.custom().build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            requestFactory.setReadTimeout(6000);
            requestFactory.setConnectTimeout(6000);
            requestFactory.setConnectionRequestTimeout(6000);
            return new RestTemplate(requestFactory);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
