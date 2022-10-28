package com.pintusau.banking.system.entities;

import com.pintusau.banking.system.entities.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Payment extends AbstractEntity {

  private Double sum;
  @Enumerated(EnumType.STRING)
  private Currency currency;
  private String description;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "payment_id")
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private Account account;
}
