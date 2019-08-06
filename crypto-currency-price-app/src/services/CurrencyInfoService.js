import currencies from './currencies.json';

export default class CurrencyInfoService {

    static getCurrencies() {
        return currencies ? currencies : [];
    }

}