import React from 'react';
import 'antd/dist/antd.css';
import { Card } from 'antd';
import {Link} from 'react-router-dom'
const { Meta } = Card;

export class Book extends React.Component{


    render() {

        const {info} = this.props;

        return (
            <Link to={{
                pathname: '/bookDetails',
                search: '?id=' + info.id}}
                  target="_blank"
            >
                <Card
                    hoverable
                    style={{width: 270}}
                    cover={<img alt="image" src={info.image} className={"bookImg"}/>}
                    //onClick={this.showBookDetails.bind(this, info.bookId)}
                >
                    <Meta title={<a style={{ color:"black", fontSize:"20px"}}>{info.name} </a>}description={<a style={{fontSize:"17px", color:"rgb(189, 54, 47)"}}>{'¥' + info.priceYuan +'.' + info.priceJiao}</a>}/>
                </Card>
            </Link>
        );
    }

}
