import React from 'react';
import 'antd/dist/antd.min.css';
import '../css/login.css';
import { Form, Input, Button, Checkbox } from 'antd';
import { BookOutlined, EditOutlined , DatabaseOutlined } from '@ant-design/icons';
import {changeAttribute} from "../services/bookService";

const AdminChangeForm = () => {
    const onFinish = (values) => {
        console.log('Received values of change form: ', values);
        changeAttribute(values);
        window.location.reload();
    };

    return (
        <Form
            name="admin_book_form"
            className="login-form"
            initialValues={{
                remember: true,
            }}
            onFinish={onFinish}
            size={"large"}
        >
            <Form.Item
                name="bookId"
                rules={[
                    {
                        required: true,
                        message: "请输入bookId",
                    },
                ]}
                style={{width:"700px",
                    marginLeft:"360px"
            }}
            >
                <Input prefix={<BookOutlined className="site-form-item-icon" />}
                       placeholder="bookId"
                       style={{borderRadius:"7px"}}
                />
            </Form.Item>
            <Form.Item
                name="attribute"
                rules={[
                    {
                        required: true,
                        message: "请输入希望修改的属性！",
                    },
                ]}
                style={{width:"700px",  marginLeft:"360px"}}
            >
                <Input
                    prefix={<DatabaseOutlined className="site-form-item-icon" />}
                    placeholder="希望修改的属性：name/author/cover/ISBN/inventory"
                    style={{borderRadius:"7px"}}
                />
            </Form.Item>
            <Form.Item
                name="newValue"
                rules={[
                    {
                        required: true,
                        message: "请输入希望修改的新值!",
                    },
                ]}
                style={{width:"700px", marginLeft:"360px"}}
            >
                <Input
                    prefix={<EditOutlined className="site-form-item-icon" />}
                    placeholder="希望修改的新值"
                    style={{borderRadius:"7px"}}
                />
            </Form.Item>
            <Form.Item style={{width:"700px", marginLeft:"360px"}}>
                <Button type="primary" htmlType="submit" className="login-form-button">
                    提交
                </Button>
            </Form.Item>
        </Form>
    );
};

export default () => <AdminChangeForm />;

