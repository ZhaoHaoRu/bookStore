import { Layout } from 'antd';

import React from 'react';
import 'antd/dist/antd.css';
import '../css/detail.css';
import '../css/browse.css';
import '../css/global.css';
import { Row, Col, Divider, Radio } from 'antd';
import {withRouter} from "react-router-dom";
import MyFooter from "../components/Footer";
import MyHeader from "../components/Header";
import BestSellerList from "../components/BestSeller";
import CustomerList from "../components/CustomerList";
import CustomerOwnList from "../components/CustomerOwnList";
import {getBestSeller,getCustomers, getCustomerBuy} from "../services/orderService";

const { Content} = Layout;

class StatisticsView extends React.Component{
    constructor(props) {
        super(props);
        this.state = {sellBooks:[],
            dateNum: 0,
            customers:[],
            buyBooks:[],
            isAdmin:0,
        };
    }

    componentDidMount(){
        let user = localStorage.getItem("user");
        console.log("user: ", user);
        let user_json = JSON.parse(user);
        this.setState({isAdmin:user_json.isAdministrators});
        getBestSeller(this.state.dateNum, (data) => {this.setState({sellBooks: data})});
        getCustomers(this.state.dateNum, (data) => {this.setState({customers: data})});
        getCustomerBuy(user_json.name, this.state.dateNum, (data) => {this.setState({buyBooks: data})});
    }


    render() {
        let value = 1;
        const onChange = e => {
            value = e.target.value;
            this.setState({dateNum:e.target.value});
            this.forceUpdate();
            getBestSeller(e.target.value, (data) => {this.setState({sellBooks: data})});
            getCustomers(e.target.value, (data) => {this.setState({customers: data})});
            let user = localStorage.getItem("user");
            let user_json = JSON.parse(user);
            getCustomerBuy(user_json.name, e.target.value, (data) => {this.setState({buyBooks: data})});

        };

        console.log("books: ", this.state.sellBooks);
        console.log("customers:", this.state.customers);
        console.log("buyBooks:", this.state.buyBooks);
        if(this.state.isAdmin)
            return(
                <div className="layout background_pic">
                    <MyHeader/>
                    <Content style={{padding: '0 50px', margin:"50px", backgroundColor:"white", paddingTop:"1%", paddingBottom:"1%"}}>
                        <Radio.Group onChange={onChange} style={{marginLeft:"35%", textAlign:"center"}}>
                            <Radio value={1}>一周内</Radio>
                            <Radio value={30}>一月内</Radio>
                            <Radio value={365}>一年内</Radio>
                            <Radio value={0}>所有</Radio>
                        </Radio.Group>
                        <Divider plain style={{color:"rgb(189, 54, 47)"}}><a style={{fontSize:"30px", color:"rgb(189, 54, 47)", fontWeight:"bold"}}>热销榜</a></Divider>
                        <BestSellerList books={this.state.sellBooks}/>
                        <Divider plain style={{color:"rgb(189, 54, 47)"}}><a style={{fontSize:"30px", color:"rgb(189, 54, 47)", fontWeight:"bold"}}>用户榜</a></Divider>
                        <CustomerList customers={this.state.customers}/>
                    </Content>
                    <MyFooter/>
                </div>
            );
        return(
            <div className="layout background_pic">
                <MyHeader/>
                <Content style={{padding: '0 50px', margin:"50px", backgroundColor:"white", paddingTop:"1%", paddingBottom:"1%"}}>
                    <Radio.Group onChange={onChange} style={{marginLeft:"35%", textAlign:"center"}}>
                        <Radio value={1}>一周内</Radio>
                        <Radio value={30}>一月内</Radio>
                        <Radio value={365}>一年内</Radio>
                        <Radio value={0}>所有订单</Radio>
                    </Radio.Group>
                    <Divider plain style={{color:"rgb(189, 54, 47)"}}><a style={{fontSize:"30px", color:"rgb(189, 54, 47)", fontWeight:"bold"}}>用户消费统计</a></Divider>
                    <CustomerOwnList books={this.state.buyBooks}/>
                </Content>
                <MyFooter/>
            </div>
        );
    }
}

export default withRouter(StatisticsView);
