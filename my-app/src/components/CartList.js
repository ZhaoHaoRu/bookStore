import React from 'react';
import $ from 'jquery'
import 'antd/dist/antd.min.css';
import {List, Avatar, Space, Image, Button, Divider, Col, message} from 'antd';
import "../css/login.css"
import {MessageOutlined, LikeOutlined, StarOutlined, DollarOutlined} from '@ant-design/icons';
import {getCartItem, purchase, addBook, deleteBook} from "../services/cartService";
import {history} from "../utils/history";
import {getOrderResult} from "../services/orderService";
import payView from "../view/PayView";
import OneOrderList from "./OrderItem";

//这里的数据就是book的数据

let socket;

class CartList extends React.Component{
    constructor(props) {
        super(props);

        this.state = {books:[],
            cartItem:[],
            isCart:1,
            timeCount:0,
        };
        this.onClickPurchase = this.onClickPurchase.bind(this);
        this.onClickAdd = this.onClickAdd.bind(this);
        this.queryOrder = this.queryOrder.bind(this);
        this.openSocket = this.openSocket.bind(this);
        this.closeSocket = this.closeSocket.bind(this);
        this.sendMessage = this.sendMessage.bind(this);
        this.timer = null;
    }

    componentDidMount(){
        let user = localStorage.getItem("user");
        let user_json = JSON.parse(user);
        this.setState({user:user_json});
        console.log("username get: ", user_json);
        console.log("username: ", user_json.name);
        getCartItem(user_json.name,(data) =>{this.setState({cartItem:data})});

    }

    openSocket(name) {
        if (typeof (WebSocket) == "undefined") {
            alert("您的浏览器不支持WebSocket");
        } else {

            if (socket != null) {
                return;
            }

            //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
            // let user = localStorage.getItem("user");
            // let user_json = JSON.parse(user);
            console.log("websocket username: " , name);
            let socketUrl = "ws://localhost:8080/websocket/transfer/" + name;
            console.log(socketUrl);
            message.success("websocket 成功连接！");


            const changeState = () => {
                this.setState({isCart:0});
            };

            socket = new WebSocket(socketUrl);
            //打开事件
            socket.onopen = function () {
                console.log("websocket已打开", 20);
                //socket.send("这是来自客户端的消息" + location.href + new Date());
            };
            //获得消息事件
            socket.onmessage = function (msg) {
                var serverMsg = "收到服务端信息：" + msg.data;
                console.log(serverMsg);
                //发现消息进入    开始处理前端触发逻辑
                // showGreeting(msg.data);
                if(msg.data === "下单成功！") {
                    changeState();
                    message.success(msg.data);
                    socket.close();
                } else {
                    message.error(msg.data);
                    socket.close();
                }
            };
            //关闭事件
            socket.onclose = function () {
                console.log("websocket已关闭");
            };
            //发生了错误事件
            socket.onerror = function () {
                console.log("websocket发生了错误");
            }
        }
    }

    closeSocket() {
        if (socket === undefined || socket === null) {
            alert("请先连接");
            return;
        }
        socket.close();
        socket = null;
    }

    sendMessage() {
        if (socket === undefined || socket === null) {
            alert("请先连接");
            return;
        }
        if (typeof (WebSocket) == "undefined") {
            console.log("您的浏览器不支持WebSocket");
        } else {
            console.log("您的浏览器支持WebSocket");
            // $.ajax({
            //     type: "get",
            //     url: "http://localhost:8080/send",
            //     dataType: 'jsonp' //【jsonp进行跨域请求 只支持get】
            // });

            // if (socket.readyState != 1) {
            //     socket.send(this.state.user.name);
            // }
            purchase(this.state.user);
        }
    }

    onClickPurchase(){
        // purchase(this.state.user);
        // this.setState({timeCount:0});
        this.openSocket(this.state.user.name);
        this.sendMessage();

        // this.timer = setInterval(()=> this.queryOrder(), 100);
    }

    componentWillUnmount() {
        this.timer = null; // here...
    }

    queryOrder(){
        const callback =  (data) => {
            console.log("query return data:", data);
            if(this.state.timeCount > 60000) {
                message.error("订单生成失败！");
                clearInterval(this.timer);
            }
            if(data === true) {
                console.log("clear Interval!");
                this.setState({isCart:0});
                clearInterval(this.timer);
            }
            this.setState({timeCount:this.state.timeCount + 100});
        };
        getOrderResult(this.state.user, callback);
    }

    onClickAdd=(e)=>{
        console.log("e:", e);
        console.log("username in add: ", this.state.user.name);
        addBook(e, this.state.user.name, (data) =>{});
        window.location.reload();
    }

    onClickDelete = (e) => {
        console.log("delete", e.value);
        deleteBook(e, this.state.user.name, (data) =>{});
        window.location.reload();
    }

    render(){

        console.log("cartItem: ",  this.state.cartItem);
        const {listData} = this.state.cartItem;
        if(this.state.isCart) {
            return (
                <div>
                    <List
                        itemLayout="vertical"
                        size="large"
                        pagination={{
                            onChange: page => {
                                console.log(page);
                            },
                            pageSize: 10,
                        }}
                        dataSource={this.state.cartItem}
                        renderItem={item => (
                            <List.Item
                                key={item.id}
                                extra={
                                    <img
                                        width={150}
                                        alt="logo"
                                        src={item.book.image}
                                        style={{marginLeft: "-40%"}}
                                    />
                                }
                            >
                                <List.Item.Meta
                                    title={<a href='#'
                                              style={{fontSize: "25px", marginLeft: "15%"}}>{item.book.name}</a>}
                                    // description={item.price}
                                    description={<a style={{
                                        fontSize: "20px",
                                        color: "red",
                                        marginLeft: "15%"
                                    }}>价格：￥{item.book.priceYuan + '.' + item.book.priceJiao}</a>}
                                />
                                <a style={{fontSize: "20px", marginLeft: "15%", color: "black"}}>数量：{item.count}</a>
                                <Button size={"large"} style={{
                                    width: "10%",
                                    marginLeft: "10%",
                                    backgroundColor: "rgb(189, 54, 47,0.7)",
                                    border: "none",
                                    borderRadius: "5px"
                                }} onClick={(e) => this.onClickAdd(item.book.id)}>增加数量</Button>
                                <Button size={"large"} style={{
                                    width: "10%",
                                    marginLeft: "10%",
                                    backgroundColor: "rgb(189, 54, 47,0.7)",
                                    border: "none",
                                    borderRadius: "5px"
                                }} onClick={(e) => this.onClickDelete(item.book.id)}>删除</Button>
                            </List.Item>
                        )}
                    />
                    <Button className="buy-button" style={{marginLeft: "500px"}} onClick={this.onClickPurchase}>
                        提交订单
                    </Button>
                </div>
            );
        }
        else{
            const pay = () => {
                console.log("already pay!\n");
                message.success("购买成功！");
                history.push("/order");
            }
            return(
                <>
                    <Divider plain style={{color:"rgb(189, 54, 47)"}}><a style={{fontSize:"30px", color:"rgb(189, 54, 47)", fontWeight:"bold"}}>我的订单</a></Divider>
                    <OneOrderList order={this.state.cartItem}/>
                    <Col>
                        <p style={{fontSize:"25px", color:"black", textAlign:"center"}} >使用微信支付成功！</p>
                        <Image src={[require("../img/微信支付.png")]} style={{marginLeft:"55%", width:"50%"}} />
                        <p></p>
                        <Button onClick={pay} type="primary" icon={<DollarOutlined />} size={"large"} style={{backgroundColor:'rgb(189, 54, 47)', border:"none", marginLeft:"43%", marginBottom:"5%"}}>
                            确认
                        </Button>
                    </Col>
                </>
            );
        }
    }
}

export default CartList;
