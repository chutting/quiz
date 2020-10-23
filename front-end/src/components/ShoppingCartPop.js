import React, {Component} from 'react';
import deleteIcon from '../icon/delete.png'
import {Button } from 'antd';

class ShoppingCartPop extends Component {
  state = {
    orders: this.props.orders
  }

  handleDeleteAll() {
    this.setState({
      orders: []
    })
  }

  handleOrderSubmit() {
    fetch('http://localhost:8080/orders', {
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.state.orders),
      method: 'POST'
    }).then(response => {
      if (response.status === 201) {
        console.log('添加成功');
      }
    });
  }

  handleDeleteItem(e, order) {
    console.log(order);
    let currentOrder = this.state.orders.filter(orderItem => orderItem.id !== order.id);
    this.setState({
      orders: currentOrder
    })
  }

  render() {
    if (this.state.orders.length === 0) {
      return <section className="no-orders">
        <p>暂无订单，返回商城页面继续购买</p>
      </section>
    }

    return <section className="pop-order">
      <table>
        <thead>
        <tr>
          <th>商品</th>
          <th>数量</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        {
          this.state.orders.map((order, index) => {
            return <tr key={`order${index}`}>
              <td>{order.name}</td>
              <td>
                <Button shape="circle" size="small">-</Button>
                {order.count}
                <Button shape="circle" size="small">+</Button>
              </td>
              <td><Button type="text" onClick={(e) => {this.handleDeleteItem(e, order)}}><img src={deleteIcon} alt="deleteIcon" className="delete-icon" /></Button></td>
            </tr>
          })
        }
        </tbody>
      </table>
      <div>
        <Button type="primary" onClick={() => this.handleOrderSubmit()}>立即下单</Button>
        <Button type="text" onClick={() => this.handleDeleteAll()}>清空</Button>
      </div>
    </section>
  }
}

// class ShoppingCartItem extends Component {
//
//   handleDeleteOrder = () => {
//     fetch('http://localhost:8080/order', {
//       headers: {
//         'Content-Type': 'application/json'
//       },
//       body: JSON.stringify(this.props.item.commodity),
//       method: 'DELETE'
//     }).then(response => {
//       if (response.status === 204) {
//         window.location.reload();
//       }
//     });
//   }
//
//   render() {
//     return <tr>
//       <td>{this.props.item.commodity.name}</td>
//       <td>
//         <Button shape="circle" size="small">-</Button>
//         {this.props.item.count}
//         <Button shape="circle" size="small">+</Button>
//       </td>
//       <td><Button type="text" onClick={this.handleDeleteOrder}><img src={deleteIcon} alt="deleteIcon" className="delete-icon" /></Button></td>
//     </tr>
//   }
// }

export default ShoppingCartPop;