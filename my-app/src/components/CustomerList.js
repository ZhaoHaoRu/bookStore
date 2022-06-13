import React from 'react';
import 'antd/dist/antd.css';
import { Card, Image, Row, Col, List, Divider, Button, Space} from 'antd';
import {render} from "react-dom";
const { Meta } = Card;

class CustomerDisplay extends React.Component{
    render(){
        const {info} = this.props;
        const {number} = this.props;
        const {length} = this.props;
        return (
            <Card style={{ width: '100%' }}>
                <Row gutter={16}>
                    <Space size="large" style={{ display: 'flex' }}>
                        <Col className="gutter-row" span={1}>
                        </Col>
                        <Col className="gutter-row" span={3}>
                            <Button type="primary" shape="circle" style={{backgroundColor:"rgb(189, 54, 47)", color:"white", fontWeight:"bold", border:"none"}} size={"large"}>
                                {number - length + 1}
                            </Button>
                        </Col>
                        <a className="gutter-row" span={6} style={{fontSize:"20px",color:"black"}}>
                            ID: {info.id}
                        </a>
                        <a className="gutter-row" span={6} style={{fontSize:"20px",color:"black"}}>
                            用户名：{info.name}
                        </a>
                        <a className="gutter-row" span={6} style={{fontSize:"20px",color:"black"}}>
                            邮箱：{info.email}
                        </a>
                        <a className="gutter-row" span={6} style={{fontSize:"20px",color:"black"}}>
                            电话：{info.phone}
                        </a>
                        <a className="gutter-row" span={4} style={{fontSize:"20px",color:"red", fontWeight:"bold"}}>
                            消费金额: ￥{info.consumption.toFixed(2)}
                        </a>
                    </Space>
                </Row>
            </Card>
        );
    }
}

class CustomerList extends React.Component{
    render(){
        const {customers}=this.props;
        let i = 0;
        if(customers.length == 0)
            return;
        return(
            <>
                <List
                    grid={{gutter: 10, column: 1}}
                    dataSource={customers}
                    pagination={{
                        onChange: page => {
                            console.log(page);
                        },
                        pageSize: 16,
                    }}

                    renderItem={item => (
                        <div>
                            <List.Item>
                                <CustomerDisplay info={item} number={i++} length={customers.length}/>
                            </List.Item>
                        </div>
                    )}
                />
                <Divider orientation="left" plain/>
            </>
        );
    }
}

export default CustomerList;
