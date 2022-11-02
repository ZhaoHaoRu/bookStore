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
            <Card style={{ width: '100%', marginTop: '2%'}}>
                <Row gutter={20}>
                    <Meta style={{fontSize:"25px"}}
                          title={<a style={{fontSize:"25px", color:"rgb(189, 54, 47)"}} >{info.name}</a>}/>
                    <Col className="gutter-row" span={14} >
                        <Space direction="vertical" size="large" style={{ display: 'flex', marginLeft: '10%'}}>
                            <Row>
                                <Space size="large" style={{ display: 'flex' }}>
                                    <a style={{fontSize:"20px", color:"black"}}>作者：{info.author}</a>
                                    <a style={{fontSize:"20px", color:"black"}}>类型：{info.type}</a>
                                </Space>
                            </Row>
                        </Space>
                    </Col>
                </Row>
                <Row gutter={24}>
                    <a style={{fontSize:"20px", color:"black"}}>简介：{info.descript}</a>
                </Row>
            </Card>
        );
    }
}

class SearchingList extends React.Component{
    render(){
        const {books}=this.props;
        let i = 0;
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

export default SearchingList;
