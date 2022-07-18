package com.pintusau.banking.system.entities;

import com.pintusau.banking.system.entities.enums.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Payment extends AbstractEntity {

  private Double sum;
  private Currency currency;
  private String description;
}
