package com.mergenie.apiaggregate.rest;

import com.netflix.appinfo.InstanceInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by can.mogol on 26/08/2018.
 */
public class ServiceInstanceRestControllerTest {

    @Mock
    private DiscoveryClient discoveryClient;

    public static final String SERVICE_ONE = "SERVICE_ONE";
    public static final String SERVICE_TWO = "SERVICE_TWO";
    private static final List<String> SERVICE_NAMES = Arrays.asList(SERVICE_ONE, SERVICE_TWO);

    public static final String HOST_NAME = "localhost";
    public static final int PORT = 9009;
    public static final String HOST_URL = String.format("http://%s:%s", HOST_NAME, PORT);

    private static final String SERVICE_ONE_URL = "/service.one";
    private static final String SERVICE_TWO_URL = "/service.two";
    private static final List<String> SERVICE_NAMES_URLS = Arrays.asList(
        String.format("%s %s%s", SERVICE_ONE, HOST_URL, SERVICE_ONE_URL),
        String.format("%s %s%s", SERVICE_TWO, HOST_URL, SERVICE_TWO_URL));


    /**
     * Service Instance ONE
     */
    public static final InstanceInfo SERVICE_ONE_INSTANCE = InstanceInfo.Builder.newBuilder()
        .setAppName(SERVICE_ONE)
        .setHostName(HOST_NAME)
        .setPort(PORT)
        .setHomePageUrl(SERVICE_ONE_URL, null)
        .getRawInstance();
    private static final List<ServiceInstance> SERVICE_ONE_INSTANCES =
        Collections.singletonList(new EurekaDiscoveryClient.EurekaServiceInstance(SERVICE_ONE_INSTANCE));

    /**
     * Service Instance TWO
     */
    public static final InstanceInfo SERVICE_TWO_INSTANCE = InstanceInfo.Builder.newBuilder()
        .setAppName(SERVICE_TWO)
        .setHostName(HOST_NAME)
        .setPort(PORT)
        .setHomePageUrl(SERVICE_TWO_URL, null)
        .getRawInstance();
    private static final List<ServiceInstance> SERVICE_TWO_INSTANCES =
        Collections.singletonList(new EurekaDiscoveryClient.EurekaServiceInstance(SERVICE_TWO_INSTANCE));


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testServiceInstancesByApplicationName() throws Exception {
        // PREPARE
        // prepare mock objects
        Mockito.when(discoveryClient.getServices())
            .thenReturn(SERVICE_NAMES);
        Mockito.when(discoveryClient.getInstances(SERVICE_ONE))
            .thenReturn(SERVICE_ONE_INSTANCES);
        Mockito.when(discoveryClient.getInstances(SERVICE_TWO))
            .thenReturn(SERVICE_TWO_INSTANCES);
        // create and set test object
        ServiceInstanceRestController controller = new ServiceInstanceRestController();
        Whitebox.setInternalState(controller, "discoveryClient", discoveryClient);

        // CALL METHOD UNDER TEST
        List<String> actualServiceNames = controller.serviceInstancesByApplicationName();

        // ASSERT
        Assert.assertEquals("should get service names and urls using discovery client",
            SERVICE_NAMES_URLS, actualServiceNames);
    }

}
