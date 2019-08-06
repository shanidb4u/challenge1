package com.nab.currencyenquiry.domain;

import com.nab.currencyenquiry.domain.entity.CurrencyPriceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyQuote {
    private String time;
    private Double price;

    public static CurrencyQuote buildQuoteFromEntity(CurrencyPriceEntity currencyPriceEntity) {
        return new CurrencyQuote(currencyPriceEntity.getId().getCurrencyTime(),
                currencyPriceEntity.getPrice());
    }

}
