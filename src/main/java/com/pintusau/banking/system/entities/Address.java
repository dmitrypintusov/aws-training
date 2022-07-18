package com.pintusau.banking.system.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Address extends AbstractEntity {

  private String street;
  private int house;
  private int flat;
}
