import React from "react";
import ReactDOM from "react-dom";
import {
  Route,
  NavLink,
  BrowserRouter as Router,
  Switch
} from "react-router-dom";
import App from './components/App';
import CryptoCurrencyPrice from './components/CryptoCurrencyPrice';
import './styles/app.scss';

const routing = (
  <Router>
    <div>
      <NavLink exact activeClassName="active" to="/">
        Home
      </NavLink>
      <hr />
      <Switch>
        <Route exact path="/" component={App} />
        <Route path="/price/:symbol" component={CryptoCurrencyPrice} />
      </Switch>
    </div>
  </Router>
);

ReactDOM.render(routing, document.getElementById("app"));
