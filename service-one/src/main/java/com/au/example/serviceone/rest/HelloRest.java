package com.au.example.serviceone.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ayhan.Ugurlu on 31/07/2018
 */
@RestController
public class HelloRest {



    @Autowired
    private DiscoveryClient discoveryClient;


    @ApiOperation(value = "api operation",
            notes = "api operation note<br/>")
    @GetMapping("sayHello")
    public @ResponseBody
    String sayHello(){
        discoveryClient.getServices();

        return "hello";
    }

    @ApiOperation(value = "api operation",
            notes = "api operation note<br/>")
    @PostMapping("hello")
    public @ResponseBody
    String hello(@ApiParam(value = "input param") @RequestBody String input){
        return "service one"+input;
    }
}
