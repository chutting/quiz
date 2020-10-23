import React, {Component} from 'react';
import imgPlaceholder from '../placeholder.png';
import shoppingCart from '../icon/shopping-cart.png';
import ShoppingCartPop from './ShoppingCartPop';
import { Popover, Button } from 'antd';
import 'antd/dist/antd.css';
import '../style/market.css';

class Market extends Component {
  state = {
    commodities: [],
    orders: []
  }

  componentDidMount() {
    fetch('http://localhost:8080/commodities').then(response => { 
      if (response.status === 200) {
        return response.json();
      }
    }).then(data => {
      this.setState({
        commodities: data
      })
    })
  }

  handleAddToCart(e, commodity) {
    console.log(commodity);

    let order = this.state.orders.filter(order => order.id === commodity.id);
    let currentOrders = [];

    if (order.length === 0) {
      currentOrders = this.state.orders;
      commodity.count = 1;
      currentOrders.push(commodity);
    } else {
      currentOrders = this.state.orders.map(order => {
        if (order.id === commodity.id) {
          order.count ++;
        }
        return order;
      })
    }

    this.setState({
      orders: currentOrders
    })
  }

  render() {
    const content = (
      <div>
        <ShoppingCartPop orders={this.state.orders}/>
      </div>
    );

    return <section className="market">
      {
        this.state.commodities.map((commodity, index) => {
          return <div className="market-item" key={`market-item${index}`}>
            <img src={imgPlaceholder} alt={commodity.name} className="commodity-item-img"></img>
            <p>{commodity.name}</p>
            <p>{`单价：${commodity.price}元/${commodity.unit}`}</p>
            <input type="submit" value = "+" className ="add-to-cart" onClick={(e) => this.handleAddToCart(e, commodity)}></input>
          </div>;
        })
      }
      <div>
        <Popover content={content} title="Title" trigger="click">
          <Button type="primary" className="shopping-cart-button"><img src={shoppingCart} alt="shopping-cart" className="shopping-cart-icon"/></Button>
        </Popover>
      </div>
    </section>
  }
}

export default Market;