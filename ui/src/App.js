import './App.css';
import React, { Component } from 'react';
import Home from './Home';
import Game from './Game';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {

  render() {
    return (
      <Router>
        <Switch>
        <Route path='/' exact={true} component={Home}/>
          <Route path='/:id' exact={true} component={Home}/>
          <Route path='/game/:id' exact={true} component={Game}/>
        </Switch>
      </Router>
  )
  }
}
export default App;