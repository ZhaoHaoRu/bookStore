import React from "react";
import {history} from "../utils/history";
import {Image} from "antd";
import { Layout,  Button, Divider, Col, message} from 'antd';
import MyHeader from "../components/Header";
import MyFooter from "../components/Footer";
import OneOrderList from "../components/OrderItem";
import {withRouter} from "react-router-dom";
import {getLatestOrder} from "../services/orderService";
import {DollarOutlined} from "@ant-design/icons";
import {addCartService} from "../services/cartService";

const { Content} = Layout;

class PayView extends React.Component{

    render(){
        // console.log(this.state.orders);

        const pay = () => {
            console.log("already pay!\n");
            message.success("购买成功！");
            history.push("/home");
        }

        return(
                <>
                    <Divider plain style={{color:"rgb(189, 54, 47)"}}><a style={{fontSize:"30px", color:"rgb(189, 54, 47)", fontWeight:"bold"}}>我的订单</a></Divider>
                        <OneOrderList order={this.state.orders}/>
                    <Col>
                        <p style={{fontSize:"25px", color:"black", textAlign:"center"}} >请扫描下面的付款码完成支付！</p>
                        <Image src={[require("../img/微信支付.png")]} style={{marginLeft:"55%", width:"50%"}} />
                        <p></p>
                        <Button onClick={pay} type="primary" icon={<DollarOutlined />} size={"large"} style={{backgroundColor:'rgb(189, 54, 47)', border:"none", marginLeft:"43%", marginBottom:"5%"}}>
                            确认购买
                        </Button>
                    </Col>
                </>
        );
    }
}

export default PayView;
