import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Link } from "react-router-dom";

export default class CryptoCurrencyCard extends Component {
    render() {
        return (<div className="crypto-currency-card">
            <div className="crypto-currency-card card">
                <img className="card-img-top" src={this.props.currency.imageUrl} alt="" />
                <div className="card-body">
                    <h4 className="card-title">{this.props.currency.name}</h4>
                    <h6 className="card-subtitle mb-2 text-muted">{this.props.currency.symbol}</h6>
                    <p className="text-justify" style={{ fontSize: '13px' }}>{this.props.currency.description}</p>
                    <Link className="btn-view-rates" to={"/price/" + this.props.currency.symbol} >View Price</Link>
                </div>
            </div>
        </div>);
    }

}

CryptoCurrencyCard.defaultProps = {
    currency: {}
};

CryptoCurrencyCard.propTypes = {
    currency: PropTypes.object
};