import React, { useState } from 'react';
import 'antd/dist/antd.css';
import {
    Form,
    Input,
    InputNumber,
    Cascader,
    Select,
    Row,
    Col,
    Checkbox,
    Button,
    AutoComplete,
} from 'antd';
import * as userService from "../services/userService";
const { Option } = Select;
// const residences = [
//     {
//         value: 'zhejiang',
//         label: 'Zhejiang',
//         children: [
//             {
//                 value: 'hangzhou',
//                 label: 'Hangzhou',
//                 children: [
//                     {
//                         value: 'xihu',
//                         label: 'West Lake',
//                     },
//                 ],
//             },
//         ],
//     },
//     {
//         value: 'jiangsu',
//         label: 'Jiangsu',
//         children: [
//             {
//                 value: 'nanjing',
//                 label: 'Nanjing',
//                 children: [
//                     {
//                         value: 'zhonghuamen',
//                         label: 'Zhong Hua Men',
//                     },
//                 ],
//             },
//         ],
//     },
// ];
const formItemLayout = {
    labelCol: {
        xs: {
            span: 24,
        },
        sm: {
            span: 8,
        },
    },
    wrapperCol: {
        xs: {
            span: 24,
        },
        sm: {
            span: 16,
        },
    },
};
const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 16,
            offset: 8,
        },
    },
};

const RegistrationForm = () => {
    const [form] = Form.useForm();

    const onFinish = (values) => {
        console.log('Received values of form: ', values);
        delete values.agreement;
        delete values.confirm;
        delete values.prefix;
        userService.register(values);
    };

    const prefixSelector = (
        <Form.Item name="prefix" noStyle>
            <Select
                style={{
                    width: 70,
                }}
            >
                <Option value="86">+86</Option>
                <Option value="87">+87</Option>
            </Select>
        </Form.Item>
    );
    const [autoCompleteResult, setAutoCompleteResult] = useState([]);

    return (
        <Form
            {...formItemLayout}
            form={form}
            name="register"
            onFinish={onFinish}
            initialValues={{
                // residence: ['zhejiang', 'hangzhou', 'xihu'],
                prefix: '86',
            }}
            scrollToFirstError
            size={"large"}
            style={{margin:'auto'}}
        >
            <Form.Item
                name="email"
                label={<label style={{ color: "white", fontSize:"15px"}}>E-mail</label>}
                rules={[
                    {
                        type: 'email',
                        message: '这不是一个有效的邮箱！',
                    },
                    {
                        required: true,
                        message: '请输入你的邮箱！',
                    },
                ]}
            >
                <Input  style={{borderRadius:"7px"}}/>
            </Form.Item>

            <Form.Item
                name="password"
                label={<label style={{ color: "white", fontSize:"15px"}}>密码</label>}
                rules={[
                    {
                        required: true,
                        message: '请输入你的密码！',
                    },
                ]}
                hasFeedback
            >
                <Input.Password style={{borderRadius:"7px"}}/>
            </Form.Item>

            <Form.Item
                name="confirm"
                label={<label style={{ color: "white", fontSize:"15px"}}>确认密码</label>}
                dependencies={['password']}
                hasFeedback
                rules={[
                    {
                        required: true,
                        message: '请输入确认密码！',
                    },
                    ({ getFieldValue }) => ({
                        validator(_, value) {
                            if (!value || getFieldValue('password') === value) {
                                return Promise.resolve();
                            }

                            return Promise.reject(new Error('两次密码的值不一致！'));
                        },
                    }),
                ]}
            >
                <Input.Password style={{borderRadius:"7px"}} />
            </Form.Item>

            <Form.Item
                name="username"
                label={<label style={{ color: "white", fontSize:"15px"}}>用户名</label>}
                tooltip="我们应该怎样称呼你？"
                rules={[
                    {
                        required: true,
                        message: '请输入用户名！',
                        whitespace: true,
                    },
                ]}
            >
                <Input  style={{borderRadius:"7px"}}/>
            </Form.Item>

            <Form.Item
                name="address"
                label={<label style={{ color: "white", fontSize:"15px"}}>地址</label>}
                tooltip="这是你的默认收件地址"
                rules={[
                    {
                        required: true,
                        message: '请输入你的收件地址！',
                        whitespace: true,
                    },
                ]}
            >
                <Input style={{borderRadius:"7px"}}/>
            </Form.Item>

            <Form.Item
                name="phone"
                label={<label style={{ color: "white", fontSize:"15px"}}>手机号码</label>}
                rules={[
                    {
                        required: true,
                        message: '请输入你的手机号码！',
                    },
                ]}
            >
                <Input
                    addonBefore={prefixSelector}
                    style={{
                        width: '100%',
                        borderRadius:"7px"
                    }}
                />
            </Form.Item>
            <Form.Item
                name="agreement"
                valuePropName="checked"
                rules={[
                    {
                        validator: (_, value) =>
                            value ? Promise.resolve() : Promise.reject(new Error('Should accept agreement')),
                    },
                ]}
                {...tailFormItemLayout}
            >
                <Checkbox style={{ color: "white", fontSize:"15px"}}>
                    我同意<a href="">相关条款</a>
                </Checkbox>
            </Form.Item>
            <Form.Item {...tailFormItemLayout}>
                <Button type="primary" htmlType="submit" className="login-button" style={{width:"40%"}}>
                    注册
                </Button>
            </Form.Item>
        </Form>
    );
};

export default () => <RegistrationForm />;
