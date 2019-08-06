import React from 'react';
import PropTypes from 'prop-types';
import CryptoCurrencyCard from './CryptoCurrencyCard';

const getCurrencies = (currencies) => {
    return (
        <div className="card-deck">
            {
                currencies.map(currency => <CryptoCurrencyCard key={currency.id} currency={currency} />)
            }
        </div>
    );
};

const CryptoCurrencyList = (props) => (
    <div>
        {getCurrencies(props.currencies)}
    </div>
);

CryptoCurrencyList.defaultProps = {
    currencies: []
};

CryptoCurrencyList.propTypes = {
    currencies: PropTypes.array
};

export default CryptoCurrencyList;