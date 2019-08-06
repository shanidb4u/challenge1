package com.nab.currencyenquiry.integration;

import com.nab.currencyenquiry.domain.CurrencyPrice;
import com.nab.currencyenquiry.domain.TradeProfit;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CurrencyPriceControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port + "/cryptocurrencyapi";
    }

    @Test
    public void getCryptoCurrencyPrices() {
        CurrencyPrice result = RestAssured.given()
                .queryParam("date", "2018-05-07")
                .get(uri + "/prices/BTC")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(CurrencyPrice.class);

        Assert.assertEquals("BTC", result.getCurrency());
        Assert.assertEquals(LocalDate.parse("2018-05-07"), result.getDate());
        Assert.assertEquals(2, result.getQuotes().size());
        Assert.assertEquals("02:15", result.getQuotes().get(0).getTime());
        Assert.assertEquals(25.98, result.getQuotes().get(0).getPrice().doubleValue(), 0.001);
    }

    @Test
    public void getCryptoCurrencyTradeProfit() {
        TradeProfit result = RestAssured.given()
                .queryParam("date", "2018-05-07")
                .get(uri + "/tradeProfit/BTC")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(TradeProfit.class);

        assertEquals("BTC", result.getCurrency());
        assertEquals(LocalDate.parse("2018-05-07"), result.getDate());
        assertEquals("02:15", result.getBuyTime());
        assertEquals(25.98, result.getBuyPrice().doubleValue(), 0.001);
        assertEquals("03:45", result.getSellTime());
        assertEquals(30.13, result.getSellPrice().doubleValue(), 0.001);
        assertEquals(4.149, result.getProfit().doubleValue(), 0.001);
    }

    @Test
    public void getCryptoCurrencyPrices_NoCurrencyOrPricesFound() {
        RestAssured.given()
                .queryParam("date", "2019-06-10")
                .get(uri + "/prices/BTC")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void getCryptoCurrencyPrices_400Error_BadRequest() {
        RestAssured.given()
                .queryParam("date", "12-06W-10")
                .get(uri + "/prices/BTC")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


}
