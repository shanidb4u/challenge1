import React from 'react';
import PropTypes from 'prop-types';

const CryptoTradeProfitTable = (props) => (
    <div>
        <table className="table">
            <thead className="thead-light">
                <tr>
                    <th className="text-uppercase">BUY</th>
                    <th className="text-uppercase">SELL</th>
                </tr>
            </thead>
            <tbody className="table-body">
                <tr>
                    <td> {props.tradeProfit.buyTime}  </td>
                    <td> {props.tradeProfit.sellTime} </td>
                </tr>
                <tr>
                    <td> {props.tradeProfit.buyPrice ? "$" + props.tradeProfit.buyPrice.toFixed(2) : "$" + 0.00} </td>
                    <td> {props.tradeProfit.sellPrice ? "$" + props.tradeProfit.sellPrice.toFixed(2) : "$" + 0.00} </td>
                </tr>
                <tr>
                    <td> profit </td>
                    <td> {props.tradeProfit.profit ? "$" + props.tradeProfit.profit.toFixed(2) : "$" + 0.00}</td>
                </tr>
            </tbody>
        </table>
    </div>
);

CryptoTradeProfitTable.propTypes = {
    currency: PropTypes.object,
    tradeProfit: PropTypes.object
};

CryptoTradeProfitTable.defaultProps = {
    currency: {},
    tradeProfit: {
        buyTime: '00:00',
        sellTime: '00:00',
        buyPrice: 0,
        sellPrice: 0,
        profit: 0
    }
};

export default CryptoTradeProfitTable;