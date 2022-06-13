import React from 'react';
import 'antd/dist/antd.css';
import { Layout, Menu, Breadcrumb, Button} from 'antd';
import MyHeader from "../components/Header";
import MyFooter from "../components/Footer";
import OrderFilter from "../components/OrderFilter";
import {withRouter} from "react-router-dom";
import CartList from "../components/CartList";
import {getOrders, getOrderItem} from "../services/orderService";
import SearchBar from "../components/SearchBar";

const { Content} = Layout;

//这里过滤筛选的功能还没做，但是应该不难
class OrderView extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            orders:[],
            filterText:{},
        }
        this.handleFilterTextChange = this.handleFilterTextChange.bind(this);
    }

    componentDidMount(){
        let user = localStorage.getItem("user");
        let user_json = JSON.parse(user);
        this.setState({user:user_json});
        getOrders(user_json.name,(data) =>{this.setState({orders:data})});
        // for(let i = 0; i < this.state.orders.length; ++i){
        //     console.log("orderId:", this.state.orders[i].id);
        //     getOrderItem(this.state.orders[i].id, (data) =>{this.setState({orderItems:[...this.state.orderItems,data]})});
        // }
    }

    handleFilterTextChange(filterText) {
        this.setState({
            filterText: filterText
        });
    }

    render(){
        console.log( "orders:" , this.state.orders);
        return(
            <Layout className="layout background_pic" >
                <MyHeader />
                <SearchBar
                    filterText={this.state.filterText}
                    onFilterTextChange={this.handleFilterTextChange}/>
                <Content style={{padding: '0 50px', backgroundColor:"white", marginTop:'0', marginLeft:"10%", marginRight:"10%"}}>
                   <OrderFilter orderList={this.state.orders} filterText={this.state.filterText}/>
                </Content>
                <MyFooter />
            </Layout>
        );
    }
}

export default withRouter(OrderView);
