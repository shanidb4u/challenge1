package com.nab.currencyenquiry.repo;

import com.nab.currencyenquiry.domain.entity.CurrencyPriceEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyPriceRepositoryIntegrationTest {

    @Autowired
    CurrencyPriceRepository currencyPriceRepository;

    @Test
    public void findPricesByCurrencyAndDate() {
        List<CurrencyPriceEntity> prices = currencyPriceRepository.findPricesByCurrencyAndDate("BTC", LocalDate.parse("2018-05-07"));

        Assert.assertEquals(2, prices.size());

        CurrencyPriceEntity priceEntity1 = prices.get(0);

        Assert.assertEquals("BTC", priceEntity1.getId().getCurrencyName());
        Assert.assertEquals(LocalDate.parse("2018-05-07"), priceEntity1.getId().getCurrencyDate());
        Assert.assertEquals("02:15", priceEntity1.getId().getCurrencyTime());
        Assert.assertEquals(25.98, priceEntity1.getPrice().doubleValue(), 0.001);
    }

}
