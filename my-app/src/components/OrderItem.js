import React from 'react';
import 'antd/dist/antd.css';
import { Card, Image, Row, Col, List, Divider} from 'antd';
const { Meta } = Card;

class OrderItem extends React.Component{
    render(){
        const {info} = this.props;
        return (
            <Card style={{ width: '100%' }}>
                <Row gutter={16}>
                    <Col className="gutter-row" span={10}>
                        <Image src={info.book.image} width={150}/>
                    </Col>
                    <Col className="gutter-row" span={14} style={{marginTop:"10px"}}>
                        <Meta style={{fontSize:"25px"}}
                              title={<a style={{fontSize:"25px", color:"rgb(189, 54, 47)"}} >{info.book.name}</a>} description={<a style={{fontSize:"20px",color:"black"}} >{'¥' + info.book.priceYuan + '.' + info.book.priceJiao}</a>}/>
                        <a style={{fontSize:"20px", color:"black"}}>数量：{info.count}</a>
                    </Col>
                </Row>
            </Card>
        );
    }
}


class OneOrderList extends React.Component{
    render(){
        const {order}=this.props;
        if(order.length == 0)
            return;
        let sum = 0;
        const x =()=>{
            let len = order.length;
            console.log("len: ", len);
            for (let i = 0; i < len; ++i) {
                sum += order[i].book.priceYuan * order[i].count + order[i].book.priceJiao * order[i].count / 100;
            }
            console.log("tmp:", sum);
        }
        console.log("sum: ", order.length);
        x();
        return(
            <>
                <List
                    grid={{gutter: 10, column: 2}}
                    dataSource={order}

                    renderItem={item => (
                        <div>
                            <List.Item>
                                <OrderItem info={item} />
                            </List.Item>
                        </div>
                    )}
                />
                <Divider orientation="left" plain><a style={{fontSize:"30px", color:"black", fontWeight:"bold"}}>商品总额：</a><a style={{fontSize:"30px", color:"rgb(189, 54, 47)", fontWeight:"bold"}}>{sum.toFixed(2)}元</a></Divider>
            </>
        );
    }
}

export default OneOrderList;
