import React, { Component } from 'react';
import CryptoPriceTable from './CryptoPriceTable'
import CryptoTradeProfitTable from './CryptoTradeProfitTable'
import { CurrencyPriceService } from '../services/CurrencyPriceService';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const currencyPriceService = new CurrencyPriceService();


export default class CryptoCurrencyPrice extends Component {

    constructor() {
        super();

        this.state = {
            currency: {},
            quotes: [],
            startDate: new Date((new Date()).valueOf() - 1000 * 60 * 60 * 24)
        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(date) {
        this.setState({
            startDate: date
        });
        this.populatePriceAndProfitData(date);
    }

    componentDidMount() {
        this.populatePriceAndProfitData();
    }

    populatePriceAndProfitData(date) {
        currencyPriceService.getPrices(this.props.match.params.symbol, date).then(prices => {
            this.setState(() => ({ quotes: prices }));
        }).catch(() => this.setState(() => (this.getEmptyPrice())));

        currencyPriceService.getTradeProfit(this.props.match.params.symbol, date).then(profit => {
            this.setState(() => ({ tradeProfit: profit }));
        }).catch(() => this.setState(() => (this.getEmptyProfit())));
    }

    getEmptyPrice() {
        return {
            quotes: [{
                price: 0,
                time: "00:00"
            }]
        }
    }

    getEmptyProfit() {
        return {
            tradeProfit: {
                buyTime: '00:00',
                sellTime: '00:00',
                buyPrice: 0,
                sellPrice: 0,
                profit: 0
            }
        }
    }

    render() {
        return (
            <div>
                <div> <DatePicker
                    selected={this.state.startDate}
                    onChange={this.handleChange}
                /></div>
                <div><h3>Price</h3></div>
                <div>
                    <CryptoPriceTable quotes={this.state.quotes} />
                </div>
                <div><h3>Trade Profit</h3></div>
                <div>
                    <CryptoTradeProfitTable tradeProfit={this.state.tradeProfit} />
                </div>
            </div>
        )
    }

}