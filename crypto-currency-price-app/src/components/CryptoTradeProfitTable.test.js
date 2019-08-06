import React from 'react';
import CryptoTradeProfitTable from './CryptoTradeProfitTable';
import { shallow } from 'enzyme';
import "../setupTests"

it('renders without crashing', () => {
  shallow(<CryptoTradeProfitTable />)
});