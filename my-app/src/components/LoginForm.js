import React from 'react';
import 'antd/dist/antd.css';
import '../css/login.css';
import { Form, Input, Button, Checkbox } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import * as userService from '../services/userService'

const LoginForm = () => {
    const onFinish = (values) => {
        console.log('Received values of form: ', values);
        delete values.remember;
        console.log('Received values of form after change: ', values);
        userService.login(values);
    };

    return (
        <Form
            name="normal_login"
            className="login-form"
            initialValues={{
                remember: true,
            }}
            onFinish={onFinish}
            size={"large"}
        >
            <Form.Item
                name="username"
                rules={[
                    {
                        required: true,
                        message: '请输入你的用户名！',
                    },
                ]}
            >
                <Input prefix={<UserOutlined className="site-form-item-icon" />}
                       placeholder="用户名"
                       style={{borderRadius:"7px"}}
                />
            </Form.Item>
            <Form.Item
                name="password"
                rules={[
                    {
                        required: true,
                        message: '请输入你的密码！',
                    },
                ]}
            >
                <Input
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    placeholder="密码"
                    style={{borderRadius:"7px"}}
                />
            </Form.Item>
            <Form.Item>
                <Form.Item name="remember" valuePropName="checked" noStyle>
                    <Checkbox style={{ color: "white", fontSize:"15px"}}>记住密码</Checkbox>
                </Form.Item>

                <a className="login-form-forgot" href="" style={{ color: "white", fontSize:"15px"}}>
                    忘记密码
                </a>
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit" className="login-form-button">
                    登录
                </Button>
                Or <a href="\register" style={{ color: "white", fontSize:"15px"}}>从这里注册！</a>
            </Form.Item>
        </Form>
    );
};

export default () => <LoginForm />;
