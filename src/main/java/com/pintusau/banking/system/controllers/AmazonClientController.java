package com.pintusau.banking.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pintusau.banking.system.entities.EC2Instance;
import com.pintusau.banking.system.services.AmazonClientService;

@RestController
public class AmazonClientController {

    @Autowired
    private AmazonClientService amazonClientService;

    @GetMapping(value = "/ec2/info")
    public EC2Instance getEc2Info() {
        return amazonClientService.getCurrentInstanceInfo();
    }
}