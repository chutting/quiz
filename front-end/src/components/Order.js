import React, {Component} from 'react';
import { Table, Space, Button} from 'antd';

const { Column, ColumnGroup } = Table;

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

  // handleDeleteOrder = (e, record) => {
  //
  //   fetch('http://localhost:8080/order', {
  //     headers: {
  //       'Content-Type': 'application/json'
  //     },
  //     body: JSON.stringify(record),
  //     method: 'DELETE'
  //   }).then(response => {
  //     if (response.status === 204) {
  //       window.location.reload();
  //     }
  //   });
  // }


  render() {
    if (this.state.orders.length === 0) {
      return <section className="no-orders">
        <p>暂无订单，返回商城页面继续购买</p>
      </section>
    }

    const columns = [
      {
        title: '#',
        dataIndex: 'id',
        key: 'id',
        align: 'left'
      },
      {
        title: '名称',
        dataIndex: 'name',
        key: 'name',
        align: 'left'
      },
      {
        title: '单价',
        dataIndex: 'price',
        key: 'price',
        align: 'left'
      },
      {
        title: '数量',
        dataIndex: 'count',
        key: 'count',
        align: 'left'
      }
    ];

    let data = [];

    this.state.orders.map((order, index) => {
      let covertDataOrder = {};
      covertDataOrder.id = index + 1;
      covertDataOrder.orderId = order.orderId;
      covertDataOrder.name = order.commodity.name;
      covertDataOrder.count = order.count + order.commodity.unit;
      covertDataOrder.price = order.commodity.price.toFixed(2);
      data.push(covertDataOrder);
    })


    const grouped = groupBy(data, order => order.orderId);

    return <section className="order">
            <Table className="order-table" dataSource={data} pagination={{ position: 'bottomRight', pageSize: 10, hideOnSinglePage: true}}>
              <ColumnGroup title={`订单号：`}>
                <Column title="#" dataIndex="id" key="id" align="left"></Column>
                <Column title="名称" dataIndex="name" key="name" align="left"></Column>
                <Column title="单价" dataIndex="price" key="price" align="left"></Column>
                <Column title="数量" dataIndex="count" key="count" align="left"></Column>
              </ColumnGroup>
          </Table>
    </section>
  }
}

// class OrderItem extends Component {
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
//       <td>{this.props.item.commodity.price}</td>
//       <td>{this.props.item.count}</td>
//       <td>{this.props.item.commodity.unit}</td>
//       <td>
//         <input type="submit" value="删除" onClick={this.handleDeleteOrder}></input>
//       </td>
//     </tr>
//   }
// }

function groupBy(list, keyGetter) {
  const map = new Map();
  list.forEach((item) => {
    const key = keyGetter(item);
    const collection = map.get(key);
    if (!collection) {
      map.set(key, [item]);
    } else {
      collection.push(item);
    }
  });
  return map;
}

export default Order;