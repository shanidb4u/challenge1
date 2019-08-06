package com.nab.currencyenquiry.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currency_price")
public class CurrencyPriceEntity {
    @EmbeddedId
    private CurrencyPriceId id;
    private Double price;
}
