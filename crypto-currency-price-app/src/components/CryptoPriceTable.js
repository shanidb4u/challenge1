import React from 'react';
import PropTypes from 'prop-types';

const CryptoPriceTable = (props) => (
    <div>
        <table className="table">
            <thead>
                <tr>
                    <th className="text-uppercase">Time</th>
                    <th className="text-uppercase">Price</th>
                </tr>
            </thead>
            <tbody className="table-body">
                {props.quotes.map((quote, index) => {
                    return (
                        <tr key={index}>
                            <td>{quote.time}</td>
                            <td>{"$" + quote.price ? quote.price.toFixed(2): 0.0 }</td>
                        </tr>
                    );
                })
                }
            </tbody>
        </table>
    </div>
);

CryptoPriceTable.propTypes = {
    currency: PropTypes.object,
    quotes: PropTypes.array
};

CryptoPriceTable.defaultProps = {
    currency: {},
    quotes: []
};

export default CryptoPriceTable;