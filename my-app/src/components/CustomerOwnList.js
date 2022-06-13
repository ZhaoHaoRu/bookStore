import React from 'react';
import 'antd/dist/antd.css';
import { Card, Image, Row, Col, List, Divider, Button, Space} from 'antd';
import {render} from "react-dom";
const { Meta } = Card;

class BookDisplay extends React.Component{
    render(){
        const {info} = this.props;
        const {number} = this.props;
        const {length} = this.props;
        // if(info.book == null)
        //     return;
        // console.log("orderItem:", {info});
        return (
            <Card style={{ width: '100%' }}>
                <Row gutter={16}>
                    <Col className="gutter-row" span={1}>
                    </Col>
                    <Col className="gutter-row" span={6}>
                        <Image src={info.image} width={150}/>
                    </Col>
                    <Col className="gutter-row" span={14} style={{marginTop:"2%"}}>
                        <Space direction="vertical" size="large" style={{ display: 'flex' }}>
                            <Meta style={{fontSize:"25px"}}
                                  title={<a style={{fontSize:"25px", color:"rgb(189, 54, 47)"}} >{info.name}</a>}/>
                            <Row>
                                <Space size="large" style={{ display: 'flex' }}>
                                    <a style={{fontSize:"20px", color:"black"}}>类型：{info.type}</a>
                                    <a style={{fontSize:"20px",color:"black"}} >售价：{'¥' + info.priceYuan + '.' + info.priceJiao}</a>
                                    <a style={{fontSize:"20px", color:"black"}}>购买数量：{info.sales}</a>
                                    <a style={{fontSize:"20px", color:"red"}}>总金额：￥{(info.sales * info.priceYuan + info.sales *info.priceJiao / 100).toFixed(2)}</a>
                                </Space>
                            </Row>
                        </Space>
                    </Col>
                </Row>
            </Card>
        );
    }
}

class CustomerOwnList extends React.Component{
    render(){
        const {books}=this.props;
        let i = 0;
        if(books.length == 0)
            return;
        let sum = 0;
        const x =()=>{
            let len = books.length;
            for (let i = 0; i < len; ++i) {
                sum += books[i].price * books[i].sales;
            }
        }
        x();
        return(
            <>
                <List
                    grid={{gutter: 10, column: 1}}
                    dataSource={books}
                    pagination={{
                        onChange: page => {
                            console.log(page);
                        },
                        pageSize: 10,
                    }}

                    renderItem={item => (
                        <div>
                            <List.Item>
                                <BookDisplay info={item} number={i++} length={books.length}/>
                            </List.Item>
                        </div>
                    )}
                />
                <Divider orientation="left" plain style={{marginBottom:"2%"}}><a style={{fontSize:"30px", color:"black", fontWeight:"bold"}}>消费总金额：</a><a style={{fontSize:"30px", color:"rgb(189, 54, 47)", fontWeight:"bold"}}>{sum.toFixed(2)}元</a></Divider>
            </>
        );
    }
}

export default CustomerOwnList;
