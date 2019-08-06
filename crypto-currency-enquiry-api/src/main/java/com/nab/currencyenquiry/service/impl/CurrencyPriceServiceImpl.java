package com.nab.currencyenquiry.service.impl;

import com.nab.currencyenquiry.domain.CurrencyPrice;
import com.nab.currencyenquiry.domain.TradeProfit;
import com.nab.currencyenquiry.domain.entity.CurrencyPriceEntity;
import com.nab.currencyenquiry.exception.NotFoundException;
import com.nab.currencyenquiry.mapper.CurrencyPriceMapper;
import com.nab.currencyenquiry.repo.CurrencyPriceRepository;
import com.nab.currencyenquiry.service.CurrencyPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class CurrencyPriceServiceImpl implements CurrencyPriceService {

    CurrencyPriceRepository repository;

    @Autowired
    public CurrencyPriceServiceImpl(CurrencyPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public CurrencyPrice getCurrencyPrices(String currencyName, LocalDate date) {
        List<CurrencyPriceEntity> prices = repository.findPricesByCurrencyAndDate(currencyName, date);
        if (CollectionUtils.isEmpty(prices)) {
            throw new NotFoundException();
        } else {
            return CurrencyPriceMapper.mapToCurrencyPrices(currencyName, date,
                    repository.findPricesByCurrencyAndDate(currencyName, date));
        }
    }

    @Override
    public TradeProfit getTradeProfit(String currencyName, LocalDate date) {
        List<CurrencyPriceEntity> prices = repository.findPricesByCurrencyAndDate(currencyName, date);
        if (CollectionUtils.isEmpty(prices)) {
            throw new NotFoundException();
        } else {
            return calculateTradeProfit(prices);
        }
    }


    public TradeProfit calculateTradeProfit(List<CurrencyPriceEntity> priceEntities) {
        int i = 0;
        int j = 1;
        int size = priceEntities.size();
        double maxProfit = 0;
        CurrencyPriceEntity buyPriceEntity = null;
        CurrencyPriceEntity sellPriceEntity = null;

        while (i < size && j < size) {
            double prevPrice = priceEntities.get(i).getPrice().doubleValue();
            double currentPrice = priceEntities.get(j).getPrice().doubleValue();
            double currentProfit = currentPrice - prevPrice;
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
                buyPriceEntity = priceEntities.get(i);
                sellPriceEntity = priceEntities.get(j);
            }
            if (currentPrice < prevPrice) {
                i = j;
            }
            j++;
        }

        if (buyPriceEntity != null) {
            return TradeProfit.builder()
                    .currency(buyPriceEntity.getId().getCurrencyName())
                    .date(buyPriceEntity.getId().getCurrencyDate())
                    .buyPrice(buyPriceEntity.getPrice())
                    .buyTime(buyPriceEntity.getId().getCurrencyTime())
                    .sellPrice(sellPriceEntity.getPrice())
                    .sellTime(sellPriceEntity.getId().getCurrencyTime())
                    .profit(maxProfit)
                    .build();
        } else {
            return TradeProfit.builder()
                    .profit(0.0)
                    .build();
        }
    }
}
