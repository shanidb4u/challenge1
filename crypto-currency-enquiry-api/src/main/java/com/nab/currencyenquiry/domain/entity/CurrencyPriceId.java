package com.nab.currencyenquiry.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPriceId implements Serializable {
    private String currencyName;
    private LocalDate currencyDate;
    private String currencyTime;
}
