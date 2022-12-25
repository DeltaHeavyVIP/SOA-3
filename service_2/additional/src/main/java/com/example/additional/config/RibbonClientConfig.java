package com.example.additional.config;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.context.annotation.Bean;

public class RibbonClientConfig extends DefaultClientConfigImpl {
    @Override
    public String getDefaultSeverListClass() {
        return ConsulServerList.class.getName();
    }

    @Bean
    public IPing ribbonPing(ServerList<Server> servers) {
        String pingPath = "/api/v1/ping";
        IPing ping = new PingUrl(false, pingPath);
        System.out.println(String.format("Configuring ping URI to [{%s}]", pingPath));
        return ping;
    }

}
