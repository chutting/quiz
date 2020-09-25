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
          return <CommodityItem 
          name={commodity.name} 
          price={commodity.price} 
          img_url = {commodity.price.img_url}
          key = {`commodity${index}`}/>
        })
      }
    </section>
  }
}

class CommodityItem extends Component {
  render() {
    return <div className="market-item">
      <img src={imgPlaceholder} alt={this.props.name} className="commodity-item-img"></img>
      <p>{this.props.name}</p>
      <p>{`单价：${this.props.price}元/瓶`}</p>
    </div>
  }
}

export default Market;