import React from 'react';
import CryptoPriceTable from './CryptoPriceTable';
import { shallow } from 'enzyme';
import "../setupTests"

it('renders without crashing', () => {
  shallow(<CryptoPriceTable />)
});