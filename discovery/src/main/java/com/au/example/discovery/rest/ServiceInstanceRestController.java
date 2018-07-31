package com.au.example.discovery.rest;


import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayhan.Ugurlu on 31/07/2018
 */
@RestController
public class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;
    

    @GetMapping("/service-instances")
    public List<String> serviceInstancesByApplicationName() {
        List<String>  servicesInfo = new ArrayList<>();
        List<String>  services = discoveryClient.getServices();
        services.forEach(servicesName -> {
            List<ServiceInstance> instances = discoveryClient.getInstances(servicesName);

            instances.forEach(serviceInstance -> {
                if(serviceInstance instanceof  EurekaDiscoveryClient.EurekaServiceInstance){
                    servicesInfo.add(servicesName+" " +((EurekaDiscoveryClient.EurekaServiceInstance)serviceInstance).getInstanceInfo().getHomePageUrl());
                }

            });
        });
        return servicesInfo;
    }
}