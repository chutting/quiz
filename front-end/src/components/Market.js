import React, {Component} from 'react';
import imgPlaceholder from '../placeholder.png';

class Market extends Component {
  state = {
    commodities: []
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


  render() {
    return <section className="market">
      {
        this.state.commodities.map((commodity, index) => {
          return <CommodityItem item = {commodity} key = {`commodity${index}`}/>;
        })
      }
    </section>
  }
}

class CommodityItem extends Component {

  handleAddToCart = () => {
    // const json = JSON.parse(this.props);
    console.log(this.props.item);
    fetch('http://localhost:8080/order', {
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.props.item),
      method: 'POST'
    }).then(response => { 
      if (response.status === 201) {
        console.log('添加成功');
      }
    });
  }

  render() {
    const {name, price, unit} = this.props;

    return <div className="market-item">
      <img src={imgPlaceholder} alt={name} className="commodity-item-img"></img>
      <p>{name}</p>
      <p>{`单价：${price}元/${unit}`}</p>
      <input type="submit" value = "+" className ="add-to-cart" onClick={this.handleAddToCart}></input>
    </div>
  }
}

export default Market;