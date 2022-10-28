package com.pintusau.banking.system.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EC2Instance {

  private String instanceId;
  private String instanceType;
  private String availabilityZone;
  private String architecture;
}
