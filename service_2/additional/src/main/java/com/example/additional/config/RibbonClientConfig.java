package com.example.additional.config;

import com.netflix.client.config.DefaultClientConfigImpl;

public class RibbonClientConfig extends DefaultClientConfigImpl {
    @Override
    public String getDefaultSeverListClass() {
        return ConsulServerList.class.getName();
    }

}
