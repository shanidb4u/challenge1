package com.nab.currencyenquiry.controller;

import com.nab.currencyenquiry.domain.CurrencyPrice;
import com.nab.currencyenquiry.domain.TradeProfit;
import com.nab.currencyenquiry.service.CurrencyPriceService;
import com.nab.currencyenquiry.util.DateValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class CurrencyPriceController {

    CurrencyPriceService currencyPriceService;

    @Autowired
    public CurrencyPriceController(CurrencyPriceService currencyPriceService) {
        this.currencyPriceService = currencyPriceService;
    }

    @GetMapping(value = "/prices/{currency}")
    public CurrencyPrice getCryptoPrices(@PathVariable String currency, @RequestParam(name = "date", required = false) String dateStr) {
        log.info(CurrencyPriceController.class.getName() + ":" + "getCryptoPrices()");
        LocalDate priceDate = LocalDate.now().minusDays(1);
        if (!StringUtils.isEmpty(dateStr)) {
            priceDate = DateValidator.validateAndConvertStringToDate(dateStr);
        }
        return currencyPriceService.getCurrencyPrices(currency, priceDate);
    }

    @GetMapping(value = "/tradeProfit/{currency}")
    public TradeProfit getTradeProfit(@PathVariable String currency, @RequestParam(name = "date", required = false) String dateStr) {
        log.info(CurrencyPriceController.class.getName() + ":" + "getTradeProfit()");
        LocalDate priceDate = LocalDate.now().minusDays(1);
        if (!StringUtils.isEmpty(dateStr)) {
            priceDate = DateValidator.validateAndConvertStringToDate(dateStr);
        }
        return currencyPriceService.getTradeProfit(currency, priceDate);
    }

}
