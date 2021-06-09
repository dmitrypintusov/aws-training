package com.pintusau.banking.system.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompositeKey {

  private Key primaryKey;
  private Key sortKey;
}