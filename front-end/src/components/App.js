import React from 'react';
import '../style/App.css';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Header from './Header';
import Market from './Market';
import Order from './Order';
import Add from './Add';

function App() {
  return (
    <div id="App">
      <BrowserRouter>
        <Header />
        <Route exact path="/" component={Market}></Route>
        <Route exact path="/orders" component={Order}></Route>
        <Route exact path="/add" component={Add}></Route>
      </BrowserRouter>
    </div>
  );
}

export default App;
