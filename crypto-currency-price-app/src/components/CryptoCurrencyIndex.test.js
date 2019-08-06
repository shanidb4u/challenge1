import React from 'react';
import CryptoCurrencyIndex from './CryptoCurrencyIndex';
import { shallow } from 'enzyme';
import "../setupTests"

it('renders without crashing', () => {
  shallow(<CryptoCurrencyIndex />)
});