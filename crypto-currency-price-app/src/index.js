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

const routing = (
  <Router>
    <div>
      <ul>
        <li>
          <NavLink exact activeClassName="active" to="/">
            Home
          </NavLink>
        </li>
      </ul>
      <hr />
      <Switch>
        <Route exact path="/" component={App} />
        <Route path="/price/:symbol" component={CryptoCurrencyPrice} />
      </Switch>
    </div>
  </Router>
);

ReactDOM.render(routing, document.getElementById("app"));
