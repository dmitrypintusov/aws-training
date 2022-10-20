package com.pintusau.banking.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Address extends AbstractEntity {

  private String street;
  private int house;
  private int flat;
  @OneToOne(mappedBy = "address")
  @JsonIgnore
  private User user;
}
