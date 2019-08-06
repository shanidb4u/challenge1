import React from 'react';
import CryptoCurrencyList from './CryptoCurrencyList';
import { shallow } from 'enzyme';
import "../setupTests"

it('renders without crashing', () => {
  shallow(<CryptoCurrencyList />)
});