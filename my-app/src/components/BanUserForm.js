import React from 'react';
import 'antd/dist/antd.min.css';
import { Form, Input, Button, Space, Popover } from 'antd';
import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons';
import "../css/admin.css";
import {banUser} from "../services/userService";

const BanUserForm = () => {
    const onFinish = values => {
        console.log('Received values of ban user form:', values);
        banUser(values);
        // window.location.reload();
    };

    return (
        <Form name="banUserForm" onFinish={onFinish} autoComplete="off" size={"large"}>
            <Form.List name="user">
                {(fields, { add, remove }) => (
                    <>
                        {fields.map(({ key, name, ...restField }) => (
                            <Space key={key} style={{ display: 'flex', marginBottom: 8 }} align="baseline">
                                <Form.Item
                                    {...restField}
                                    name={[name, 'id']}
                                    rules={[{ required: true, message: '缺失userId' }]}
                                    style={{marginLeft:"380px"}}
                                >
                                    <Input placeholder="userId" />
                                </Form.Item>
                                <MinusCircleOutlined onClick={() => remove(name)} />
                            </Space>
                        ))}
                        <Form.Item>
                            <Popover title="输入要操作用户的Id,如果此时用户处于禁用状态，进行解禁，反之禁用用户">
                                <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined />} style={{width:"700px", marginLeft:"360px"}} >
                                    点击禁用/解禁用户
                                </Button>
                            </Popover>
                        </Form.Item>
                    </>
                )}
            </Form.List>
            <Form.Item>
                <Button type="primary" htmlType="submit"  className="login-form-button" style={{width:"700px", marginLeft:"360px"}}>
                    确认禁用/解禁用户
                </Button>
            </Form.Item>
        </Form>
    );
};

export default BanUserForm;
