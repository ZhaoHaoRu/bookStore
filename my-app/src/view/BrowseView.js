import { Layout, Menu, Breadcrumb } from 'antd';
import 'antd/dist/antd.css';
import '../css/browse.css';
import React from 'react';
import '../css/global.css'
import MyHeader from "../components/Header";
import MyFooter from "../components/Footer";
import BookList from "../components/BookList";
import SearchBar from "../components/SearchBar";
import {withRouter} from "react-router-dom";
const { Content} = Layout;

class BrowseView extends React.Component {
    render() {
        return(
        <Layout className="layout background_pic" >
           <MyHeader />
            <Content style={{padding: '0 50px'}}>
                    <BookList/>
            </Content>
            <MyFooter />
        </Layout>
        );
    }
}

export default withRouter(BrowseView);
