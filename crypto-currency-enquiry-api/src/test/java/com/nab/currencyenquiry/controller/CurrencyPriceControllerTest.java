package com.nab.currencyenquiry.controller;


import com.nab.currencyenquiry.domain.CurrencyPrice;
import com.nab.currencyenquiry.domain.CurrencyQuote;
import com.nab.currencyenquiry.domain.TradeProfit;
import com.nab.currencyenquiry.exception.BadRequestException;
import com.nab.currencyenquiry.service.CurrencyPriceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyPriceControllerTest {

    @Mock
    CurrencyPriceService currencyPriceService;

    CurrencyPriceController testInstance;

    @Before
    public void setUp() {
        testInstance = new CurrencyPriceController(currencyPriceService);
    }


    @Test
    public void testGetCurrencyPrices() {
        when(currencyPriceService.getCurrencyPrices("BTC", LocalDate.parse("2018-12-31")))
                .thenReturn(getCurrencyPriceData());
        CurrencyPrice result = testInstance.getCryptoPrices("BTC", "2018-12-31");
        Assert.assertEquals("BTC", result.getCurrency());
        Assert.assertEquals(LocalDate.parse("2018-12-31"), result.getDate());
        Assert.assertEquals("1000", result.getQuotes().get(0).getTime());
        Assert.assertEquals(35.00, result.getQuotes().get(0).getPrice().doubleValue(), 0.001);
    }

    private CurrencyPrice getCurrencyPriceData() {
        return CurrencyPrice.builder()
                .currency("BTC")
                .date(LocalDate.parse("2018-12-31"))
                .quotes(Arrays.asList(CurrencyQuote.builder()
                        .price(35.00)
                        .time("1000")
                        .build()))
                .build();
    }

    @Test(expected = BadRequestException.class)
    public void testGetCurrencyPrices_Exception() {
        testInstance.getCryptoPrices("BTC", "98989-12-31");
    }

    @Test
    public void getTradeProfit() {
        when(currencyPriceService.getTradeProfit("BTC", LocalDate.parse("2018-12-31")))
                .thenReturn(TradeProfit.builder().profit(100.00).build());
        TradeProfit result = testInstance.getTradeProfit("BTC", "2018-12-31");
        Assert.assertEquals(100, result.getProfit().doubleValue(), 0.000);
    }


}