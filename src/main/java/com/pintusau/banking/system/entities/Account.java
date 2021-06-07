package com.pintusau.banking.system.entities;

import java.util.List;
import com.pintusau.banking.system.entities.enums.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Account extends AbstractEntity {

    private Double value;
    private Currency currency;
    private List<Payment> payments;

}