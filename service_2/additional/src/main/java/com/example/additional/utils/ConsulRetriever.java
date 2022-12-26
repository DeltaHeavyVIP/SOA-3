package com.example.additional.utils;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.health.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConsulRetriever {

    public List<String> getCrudAddresses() { //todo
        Consul client = Consul.builder().withUrl("http://localhost:8500").build();
        AgentClient agentClient = client.agentClient();
        Map<String, Service> services = agentClient.getServices();
        return services.values().stream().filter(x -> x.getTags().contains("basic")).map(service ->  service.getAddress() + ":" + service.getPort()).collect(Collectors.toList());
    }
}
