import React, { Component } from 'react';
import CryptoCurrencyList from './CryptoCurrencyList';
import CurrencyInfoService from '../services/CurrencyInfoService'


export default class CryptoCurrencyIndex extends Component {

    constructor() {
        super();

        this.state = {
            currencies: []
        };
    }

    componentDidMount() {
        this.setState(() => ({
            currencies: CurrencyInfoService.getCurrencies()
        }));
    }

    render() {
        return (
            <div className="container-fluid" style={{ marginLeft: '-15px' }}>
                <div className="d-flex flex-row">
                    <div className="col-sm-12">
                        <CryptoCurrencyList currencies={this.state.currencies} />
                    </div>
                </div>
            </div>
        );
    }

}