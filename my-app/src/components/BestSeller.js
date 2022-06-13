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
                    <Col className="gutter-row" span={3}>
                        <Button type="primary" shape="circle" style={{backgroundColor:"rgb(189, 54, 47)", color:"white", fontWeight:"bold", border:"none", marginTop:"35%"}} size={"large"}>
                            {info.id}
                        </Button>
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
                                    <a style={{fontSize:"20px",color:"black"}} >售价：{'¥' + info.priceYuan+'.' + info.priceJiao}</a>
                                    <a style={{fontSize:"20px", color:"black"}}>ISBN：{info.isbn}</a>
                                    <a style={{fontSize:"20px", color:"black"}}>作者：{info.author}</a>
                                    <a style={{fontSize:"20px", color:"black"}}>类型：{info.type}</a>
                                </Space>
                            </Row>
                        </Space>
                    </Col>
                </Row>
            </Card>
        );
    }
}

class BestSellerList extends React.Component{
    render(){
        const {books}=this.props;
        let i = 0;
        if(books.length == 0)
            return;
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
                <Divider orientation="left" plain/>
            </>
        );
    }
}

export default BestSellerList;
