import { Layout, Menu, Breadcrumb, Button} from 'antd';
import 'antd/dist/antd.min.css';
import '../css/browse.css';
import React from 'react';
import '../css/global.css';
import "../css/cart.css";
import MyHeader from "../components/Header";
import MyFooter from "../components/Footer";
import CartList from "../components/CartList";
import SearchBar from "../components/SearchBar";
import {withRouter} from "react-router-dom";


const { Content} = Layout;

class CartView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }
    componentDidMount() {
        let user = localStorage.getItem("user");
        let user_json = JSON.parse(user);
        this.setState({user:user_json});
    }

    render() {
        // console.log("user in view: ", this.state.user);
        return(
            <Layout className="layout background_pic" >
                <MyHeader />
                <Content style={{padding: '0 50px', backgroundColor:"white", marginTop:'5%', marginLeft:"10%", marginRight:"10%", minHeight:"400px"}}>
                    <CartList user = {this.state.user}/>
                </Content>
                <MyFooter />
            </Layout>
        );
    }
}

export default withRouter(CartView);
