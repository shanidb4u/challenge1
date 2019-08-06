package com.nab.currencyenquiry.service.impl;

import com.nab.currencyenquiry.domain.CurrencyPrice;
import com.nab.currencyenquiry.domain.TradeProfit;
import com.nab.currencyenquiry.domain.entity.CurrencyPriceEntity;
import com.nab.currencyenquiry.domain.entity.CurrencyPriceId;
import com.nab.currencyenquiry.repo.CurrencyPriceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyPriceServiceImplTest {

    CurrencyPriceServiceImpl testInstance;

    @Mock
    CurrencyPriceRepository currencyPriceRepository;


    @Before
    public void setUp() {
        testInstance = new CurrencyPriceServiceImpl(currencyPriceRepository);
    }

    @Test
    public void getCurrencyPrices() {
        when(currencyPriceRepository.findPricesByCurrencyAndDate("BTC", LocalDate.parse("2018-12-31")))
                .thenReturn(getCurrencyPriceEntities());
        CurrencyPrice result = testInstance.getCurrencyPrices("BTC", LocalDate.parse("2018-12-31"));
        assertEquals(1, result.getQuotes().size());
        Assert.assertEquals("BTC", result.getCurrency());
        Assert.assertEquals(LocalDate.parse("2018-12-31"), result.getDate());
        Assert.assertEquals("1010", result.getQuotes().get(0).getTime());
        Assert.assertEquals(35.00, result.getQuotes().get(0).getPrice().doubleValue(), 0.000);
    }


    @Test
    public void getTradeProfit() {
        when(currencyPriceRepository.findPricesByCurrencyAndDate("BTC", LocalDate.parse("2018-12-31")))
                .thenReturn(getCurrencyPriceEntities(35.00, 40.00));
        TradeProfit result = testInstance.getTradeProfit("BTC", LocalDate.parse("2018-12-31"));

        assertEquals("BTC", result.getCurrency());
        assertEquals(LocalDate.parse("2018-12-31"), result.getDate());
        assertEquals("0030", result.getBuyTime());
        assertEquals(35.00, result.getBuyPrice().doubleValue(), 0.001);
        assertEquals("0130", result.getSellTime());
        assertEquals(40.00, result.getSellPrice().doubleValue(), 0.001);
        assertEquals(5, result.getProfit().doubleValue(), 0.001);
    }

    @Test
    public void getTradeProfit_NoProfit() {
        when(currencyPriceRepository.findPricesByCurrencyAndDate("BTC", LocalDate.parse("2018-12-31")))
                .thenReturn(getCurrencyPriceEntities(35.00, 30.00));
        TradeProfit result = testInstance.getTradeProfit("BTC", LocalDate.parse("2018-12-31"));
        assertNull(result.getBuyTime());
        assertNull(result.getBuyPrice());
        assertNull(result.getSellTime());
        assertNull(result.getSellPrice());
        assertEquals(0, result.getProfit().doubleValue(), 0.001);
    }

    @Test
    public void getTradeProfit_MultipleScenarios() {

        double[][] input = {{34, 36, 37, 35, 50},
                {34, 38, 40, 32, 70},
                {20, 25, 10, 100, 30, 35},
                {10, 15, 25, 35, 110},
                {1, 3, 2, 4, 5, 2},
                {34, 36, 37, 35, 50, 2, 100},
                {5, 100, 34, 36, 37, 35, 50, 1},
                {100, 95, 90, 93, 85, 80}
        };

        double[] expectedProfit = {16, 38, 90, 100, 4, 98, 95, 3};

        IntStream.range(0, input.length).forEach(index -> {
            when(currencyPriceRepository.findPricesByCurrencyAndDate("BTC", LocalDate.parse("2018-12-31")))
                    .thenReturn(getCurrencyPriceEntities(input[index]));
            TradeProfit result = testInstance.getTradeProfit("BTC", LocalDate.parse("2018-12-31"));
            assertEquals(expectedProfit[index], result.getProfit().doubleValue(), 0.001);

        });
    }


    private List<CurrencyPriceEntity> getCurrencyPriceEntities(double... prices) {
        List<CurrencyPriceEntity> result = new ArrayList<>();
        int count = 0;
        for (double price : prices) {
            CurrencyPriceId currencyPriceId = new CurrencyPriceId("BTC",
                    LocalDate.parse("2018-12-31"),
                    LocalTime.of(count, 30).format(DateTimeFormatter.ofPattern("HHmm")));
            CurrencyPriceEntity currencyPriceEntity = new CurrencyPriceEntity(currencyPriceId, price);
            result.add(currencyPriceEntity);
            count++;
        }

        return result;
    }

    private List<CurrencyPriceEntity> getCurrencyPriceEntities() {
        CurrencyPriceId currencyPriceId = new CurrencyPriceId("BTC", LocalDate.parse("2018-12-31"), "1010");
        CurrencyPriceEntity currencyPriceEntity = new CurrencyPriceEntity(currencyPriceId, 35.00);
        return Arrays.asList(currencyPriceEntity);
    }
}