package com.nab.currencyenquiry.service;

import com.nab.currencyenquiry.domain.CurrencyPrice;
import com.nab.currencyenquiry.domain.TradeProfit;

import java.time.LocalDate;

public interface CurrencyPriceService {
    CurrencyPrice getCurrencyPrices(String currencyName, LocalDate date);
    TradeProfit getTradeProfit(String currencyName, LocalDate date);
}
