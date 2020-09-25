import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class Header extends Component {
  render() {
    return <section className="header">
      <Link to="/">商城</Link>
      <Link to="/orders">订单</Link>
      <Link to="/add">添加商品</Link>
    </section>
  }
}

export default Header;