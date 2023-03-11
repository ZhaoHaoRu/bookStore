import { Layout } from 'antd';

import React from 'react';
import 'antd/dist/antd.min.css';
import '../css/detail.css';
import '../css/browse.css';
import '../css/global.css';
import { Row, Col, Divider } from 'antd';
import {BookDetail} from "../components/BookDetail";
import {withRouter} from "react-router-dom";
import MyFooter from "../components/Footer";
import MyHeader from "../components/Header";
import {getBook} from "../services/bookService";


const { Content} = Layout;

class DetailView extends React.Component{
    constructor(props) {
        super(props);
        this.state = {books:[]};
    }

    componentDidMount(){
        let user = localStorage.getItem("user");
        console.log("user: ", user);
        let user_json = JSON.parse(user);
        this.setState({user:user_json});

        const query = this.props.location.search;
        const arr = query.split('&');
        const bookId = arr[0].substr(4);
        getBook(bookId, (data) => {this.setState({books: data})});
    }

    render() {
        return(
            <div className="layout background_pic">
                <MyHeader/>
                    <Content style={{padding: '0 50px', margin:"50px", backgroundColor:"white"}}>
                        <BookDetail info = {this.state.books} user = {this.state.user}/>
                    </Content>
                <MyFooter/>
            </div>
        );
    }
}

export default withRouter(DetailView);

