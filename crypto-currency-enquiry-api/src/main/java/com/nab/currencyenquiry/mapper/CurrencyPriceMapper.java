package com.nab.currencyenquiry.mapper;

import com.nab.currencyenquiry.domain.CurrencyPrice;
import com.nab.currencyenquiry.domain.CurrencyQuote;
import com.nab.currencyenquiry.domain.entity.CurrencyPriceEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyPriceMapper {

    private CurrencyPriceMapper() {
    }

    public static CurrencyPrice mapToCurrencyPrices(String currency, LocalDate date, List<CurrencyPriceEntity> currencyPriceEntityList) {

        List<CurrencyQuote> quotes = currencyPriceEntityList.stream()
                .map(CurrencyQuote::buildQuoteFromEntity)
                .collect(Collectors.toList());

        return CurrencyPrice.builder()
                .currency(currency)
                .date(date)
                .quotes(quotes)
                .build();

    }

}
