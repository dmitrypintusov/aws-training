package com.pintusau.banking.system.entities;

import com.pintusau.banking.system.entities.enums.AttributeType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Key {

  private String name;
  private String value;
  private AttributeType type;
}