package com.pintusau.banking.system.entities;

import com.pintusau.banking.system.entities.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends AbstractEntity {

  private String email;
  private String firstName;
  private String lastName;
  private boolean active;

  private Role role;
  private Address address;
  private Account account;

}
