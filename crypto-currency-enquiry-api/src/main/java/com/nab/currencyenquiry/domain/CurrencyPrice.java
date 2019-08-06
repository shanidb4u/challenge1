package com.nab.currencyenquiry.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPrice {
    private String currency;
    private LocalDate date;
    private List<CurrencyQuote> quotes;
}
