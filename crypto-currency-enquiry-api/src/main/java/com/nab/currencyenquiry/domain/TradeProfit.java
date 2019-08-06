package com.nab.currencyenquiry.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeProfit {
    private String currency;
    private LocalDate date;
    private String buyTime;
    private Double buyPrice;
    private String sellTime;
    private Double sellPrice;
    private Double profit;
}
