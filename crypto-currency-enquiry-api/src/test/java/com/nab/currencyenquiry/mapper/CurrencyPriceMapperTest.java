package com.nab.currencyenquiry.mapper;

import com.nab.currencyenquiry.domain.CurrencyPrice;
import com.nab.currencyenquiry.domain.entity.CurrencyPriceEntity;
import com.nab.currencyenquiry.domain.entity.CurrencyPriceId;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CurrencyPriceMapperTest {


    @Test
    public void mapToCurrencyPrices() {
        CurrencyPrice result = CurrencyPriceMapper.mapToCurrencyPrices("BTC", LocalDate.parse("2018-05-07"), getCurrencyPriceEntities());
        Assert.assertEquals("BTC", result.getCurrency());
        Assert.assertEquals(LocalDate.parse("2018-05-07"), result.getDate());
        Assert.assertEquals("1010", result.getQuotes().get(0).getTime());
        Assert.assertEquals(35.00, result.getQuotes().get(0).getPrice().doubleValue(), 0.001);
    }

    private List<CurrencyPriceEntity> getCurrencyPriceEntities() {
        CurrencyPriceId currencyPriceId = new CurrencyPriceId("", null, "1010");
        CurrencyPriceEntity currencyPriceEntity = new CurrencyPriceEntity(currencyPriceId, 35.00);
        return Arrays.asList(currencyPriceEntity);
    }
}