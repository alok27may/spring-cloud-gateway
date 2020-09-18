package com.kumaral.gateway.springcloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class HystrixController {

    @GetMapping("/msg")
    public String firstServiceFallback(){
        return "This is a fallback for message.";
    }

}
