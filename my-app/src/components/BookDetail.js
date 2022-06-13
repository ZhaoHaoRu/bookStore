import React from 'react';
import { Descriptions, Button, Col, Row, Image, message} from 'antd';
import {DollarOutlined} from '@ant-design/icons';
import {addCartService} from "../services/cartService";
import {history} from "../utils/history";

export class BookDetail extends React.Component{


    render() {

        const {info} = this.props;
        const {user} = this.props;

        if(info == null){
            console.log("failed");
            return null;
        }

        const addToCart = () => {
            console.log("add to cart!\n");
            console.log(info.id);
            console.log(user.name);
            addCartService({"id":info.id, "username":user.name});
        }

        return (
            <div >
                    <Row gutter={20}>
                        <Col className="gutter-row" span={12}>
                            <Image style={{width:"350px", height:"350px"}} className='book-image'
                                   src={info.image}
                            />
                        </Col>
                        <Col className="gutter-row" span={12} >
                            <Descriptions className="my-descriptions">
                                <Descriptions.Item  span={3} style={{fontSize:"30px"}}><span style={{fontSize:'45px', color:'rgb(189, 54, 47)',  fontWeight: 'bold' }} >{info.name}</span></Descriptions.Item>
                                <Descriptions.Item label={<label style={{fontSize:'20px', fontWeight: 'bold'}}>{"作者"}</label>} span={3}><span style={{fontSize:'20px'}} >{info.author}</span></Descriptions.Item>
                                <Descriptions.Item label={<label style={{fontSize:'20px', fontWeight: 'bold'}}>{"分类"}</label>} span={3}><span style={{fontSize:'20px'}} >{info.type}</span></Descriptions.Item>
                                <Descriptions.Item label={<label style={{fontSize:'20px', fontWeight: 'bold'}}>{"定价"}</label>} span={3}>{<span className={"price"} style={{fontSize:'20px'}}>{'¥' + info.priceYuan + '.' + info.priceJiao}</span>}</Descriptions.Item>
                                <Descriptions.Item label={<label style={{fontSize:'20px', fontWeight: 'bold'}}>{"状态"}</label>} span={3}>{info.inventory !== 0? <span style={{fontSize:'20px'}}>有货 <span className={"inventory"} style={{fontSize:'20px'}}>库存{info.inventory}件</span></span> : <span className={"status"} style={{fontSize:'20px'}}>无货</span>}</Descriptions.Item>
                            </Descriptions>
                            <Button onClick={addToCart} type="primary" icon={<DollarOutlined />} size={"large"} style={{backgroundColor:'rgb(189, 54, 47)', border:"none"}}>
                                加入购物车
                            </Button>
                        </Col>
                    </Row>
                    <Row gutter={16}>
                        <Col className="gutter-row" span={24} style={{marginTop:"5%"}}>
                            <div style={{fontSize:'45px', color:'rgb(189, 54, 47)',  fontWeight: 'bold', width:"1400px", textAlign:'center'}}>作品简介</div>
                            <div style={{fontSize:'25px', width:"80%", margin:"auto"}}>{info.descript}</div>
                        </Col>
                    </Row>
            </div>
        )
    }
}

export default BookDetail;
