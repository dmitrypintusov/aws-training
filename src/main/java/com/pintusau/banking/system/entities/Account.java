package com.pintusau.banking.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pintusau.banking.system.entities.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Account extends AbstractEntity {

  private Double value;
  @Enumerated(EnumType.STRING)
  private Currency currency;
  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private List<Payment> payments;
  @OneToOne(mappedBy = "account")
  @JsonIgnore
  private User user;
}
