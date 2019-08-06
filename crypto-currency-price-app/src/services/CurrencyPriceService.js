import axios from 'axios';

const BASE_URL = "http://localhost:8080/cryptocurrencyapi"

export class CurrencyPriceService {
    getPrices(currency, dateValue) {
        return new Promise((resolve, reject) => {
            axios.get(`${BASE_URL}/prices/${currency}/`, dateValue ? { params: { date: dateValue.toISOString().slice(0, 10) } } : null)
                .then(Response => {
                    if (Response.data && Response.data.quotes) {
                        resolve(Response.data.quotes)
                    }
                })
                .catch(error => reject(error.message))
        });
    }

    getTradeProfit(currency, dateValue) {
        return new Promise((resolve, reject) => {
            axios.get(`${BASE_URL}/tradeProfit/${currency}/`, dateValue ? { params: { date: dateValue.toISOString().slice(0, 10) } } : null)
                .then(Response => {
                    if (Response.data) {
                        resolve(Response.data)
                    }
                })
                .catch(error => reject(error.message))
        });

    }
}

