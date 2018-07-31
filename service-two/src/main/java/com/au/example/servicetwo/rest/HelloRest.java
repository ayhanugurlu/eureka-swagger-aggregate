package com.au.example.servicetwo.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ayhan.Ugurlu on 31/07/2018
 */
@RestController
public class HelloRest {


    @ApiOperation(value = "api operation",
            notes = "api operation note<br/>")
    @PostMapping("hello")
    public @ResponseBody
    String addAccount(@ApiParam(value = "input param") @RequestBody String input){
        return "service two"+input;
    }
}
