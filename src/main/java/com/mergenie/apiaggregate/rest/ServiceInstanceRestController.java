package com.mergenie.apiaggregate.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rest controller for services.
 * <p>
 * Created by Ayhan.Ugurlu on 31/07/2018
 */
@RestController
public class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * Returns available service names and urls.
     *
     * @return List of string
     */
    @GetMapping("/service-instances")
    public final List<String> serviceInstancesByApplicationName() {
        return discoveryClient.getServices()
            .stream()
            .map(servicesName ->
                discoveryClient.getInstances(servicesName)
                    .stream()
                    .filter(serviceInstance ->
                        serviceInstance instanceof EurekaDiscoveryClient.EurekaServiceInstance)
                    .map(serviceInstance -> ((EurekaDiscoveryClient.EurekaServiceInstance) serviceInstance))
                    .map(esi ->  String.format("%s %s", servicesName, esi.getInstanceInfo().getHomePageUrl()))
                    .collect(Collectors.toList()))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }
}
