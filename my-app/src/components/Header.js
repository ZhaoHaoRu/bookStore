import React, {useState} from 'react';
import '../css/browse.css'
import {Menu, Layout, Alert} from 'antd';
import {ShoppingCartOutlined, HomeOutlined,UnorderedListOutlined, BarChartOutlined, UserDeleteOutlined, SearchOutlined } from '@ant-design/icons';
import {Link} from 'react-router-dom';
import * as userService from '../services/userService';
const { Header, Content, Footer } = Layout;


const MyHeader=()=>{
    return (
        <div>
            <Header style={{backgroundColor: "#BD362F"}}>
                <div className="logo">
                    <p style={{margin:"auto",height: '31px'}} className="header_heading">
                        烟海书店
                    </p>
                </div>
                <Menu
                    theme="dark"
                    mode="horizontal"
                    style={{backgroundColor: "#BD362F"}}
                    defaultSelectedKeys={['2']}
                >
                    <Menu.Item key="home" icon={<HomeOutlined />}>
                        <Link to="/home">
                            主页
                        </Link>
                    </Menu.Item>
                    <Menu.Item key="cart" icon={<ShoppingCartOutlined />}>
                        <Link to="/cart">
                            购物车
                        </Link>
                    </Menu.Item>
                    <Menu.Item key="order" icon={<UnorderedListOutlined />}>
                        <Link to="/order">
                            订单
                        </Link>
                    </Menu.Item>
                    <Menu.Item key="statistics" icon={<BarChartOutlined />}>
                        <Link to="/statistics">
                            统计
                        </Link>
                    </Menu.Item>
                    <Menu.Item key="statistics" icon={<SearchOutlined />}>
                        <a href="/search" onClick={userService.logout}>
                            全局搜索
                        </a>
                    </Menu.Item>
                    <Menu.Item key="statistics" icon={<UserDeleteOutlined />}>
                        <a href="#" onClick={userService.logout}>
                            退出
                        </a>
                    </Menu.Item>
                </Menu>
            </Header>
        </div>
    );
}

export default MyHeader;

