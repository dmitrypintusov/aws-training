package com.pintusau.banking.system.controllers;

import com.pintusau.banking.system.entities.EC2Instance;
import com.pintusau.banking.system.services.aws.ec2.EC2Service;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/ec2")
@AllArgsConstructor
public class Ec2Controller {

  private EC2Service ec2Service;

  @GetMapping(value = "/info")
  @Operation(summary = "Get current running instance metadata")
  public EC2Instance getEc2Info() {
    return ec2Service.getCurrentInstanceInfo();
  }
}
