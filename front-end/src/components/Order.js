import React, {Component} from 'react';

class Order extends Component {
  state = {
    orders: []
  }

  componentDidMount() {
    fetch('http://localhost:8080/orders').then(response => { 
      if (response.status === 200) {
        return response.json();
      }
    }).then(data => {
      this.setState({
        orders: data
      })
    })
  }


  render() {
    return <section className="order">
      <table>
        <thead>
          <tr>
            <th>名字</th>
            <th>单价</th>
            <th>数量</th>
            <th>单位</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          {
            this.state.orders.map((order, index) => {
              return <OrderItem item = {order} key = {`order${index}`}></OrderItem>
            })
          }
        </tbody>
      </table>
    </section>
  }
}

class OrderItem extends Component {
  render() {
    return <tr>
      <td>{this.props.item.commodity.name}</td>
      <td>{this.props.item.commodity.price}</td>
      <td>{this.props.item.count}</td>
      <td>{this.props.item.commodity.unit}</td>
      <td>
        <input type="submit" value="删除"></input>
      </td>
    </tr>
  }
}

export default Order;