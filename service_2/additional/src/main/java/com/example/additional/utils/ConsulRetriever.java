package com.example.additional.utils;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.health.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConsulRetriever {

    @Value("${spring.cloud.consul.host}")
    private String CONSUL_HOST;

    @Value("${spring.cloud.consul.port}")
    private String CONSUL_PORT;


    public List<String> getCrudAddresses() {
        Consul client = Consul.builder().withUrl("http://" + CONSUL_HOST + ":" + CONSUL_PORT).build();
        AgentClient agentClient = client.agentClient();
        Map<String, Service> services = agentClient.getServices();
        return services.values().stream().filter(x -> x.getAddress().contains("basic")).map(service -> "http://" + service.getAddress() + ":" + service.getPort()).collect(Collectors.toList());
    }
}
