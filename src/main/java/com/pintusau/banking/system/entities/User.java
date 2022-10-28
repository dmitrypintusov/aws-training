package com.pintusau.banking.system.entities;

import com.pintusau.banking.system.entities.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends AbstractEntity {

  @Column(unique = true)
  private String email;
  private String firstName;
  private String lastName;
  private boolean active;
  @Enumerated(EnumType.STRING)
  private Role role;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;
}
