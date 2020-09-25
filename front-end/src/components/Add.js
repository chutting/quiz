import React, {Component} from 'react';
import { Input, Button } from 'antd';

class Add extends Component {

  state = {
    name: '',
    price: '',
    unit: '',
    imgUrl: '',
  }

  handleChange = (field, event) => {
    this.setState({
      [field]: event.target.value
    })
  }

  handleSubmit = () => {
    fetch('http://localhost:8080/commodity', {
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.state),
      method: 'POST'
    }).then(response => { 
      if (response.status === 204) {
        console.log('添加成功');
      }
    });
  }


  render() {
    return <section className="add">
      <div className="info">
        <p><span className="star">*</span>名称：</p>
        <Input placeholder="名称" onChange={e => this.handleChange('name', e)}/>
      </div>
      <div className="info">
        <p><span className="star">*</span>价格：</p>
        <Input placeholder="价格" onChange={e => this.handleChange('price', e)}/>
      </div>
      <div className="info">
        <p><span className="star">*</span>单位：</p>
        <Input placeholder="单位" onChange={e => this.handleChange('unit', e)}/>
      </div>
      <div className="info">
        <p><span className="star">*</span>图片：</p>
        <Input placeholder="URL" onChange={e => this.handleChange('imgUrl', e)}/>
      </div>
      <Button type="primary" 
      onClick={this.handleSubmit} 
      disabled={this.state.name === '' || this.state.price === '' || this.state.unit === '' || this.state.imgUrl === ''}>提交</Button>
    </section>
  }
}


export default Add;