import React from 'react';
import CryptoCurrencyCard from './CryptoCurrencyCard';
import { shallow } from 'enzyme';
import "../setupTests"

it('renders without crashing', () => {
  shallow(<CryptoCurrencyCard />)
});