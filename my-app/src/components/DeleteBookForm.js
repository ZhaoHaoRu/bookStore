import React from 'react';
import 'antd/dist/antd.css';
import { Form, Input, Button, Space } from 'antd';
import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons';
import "../css/admin.css";
import {deleteBook} from "../services/bookService";

const DeleteBookForm = () => {
    const onFinish = values => {
        console.log('Received values of delete book form:', values);
        deleteBook(values);
        window.location.reload();
    };

    return (
        <Form name="dynamic_form_nest_item" onFinish={onFinish} autoComplete="off" size={"large"}>
            <Form.List name="book">
                {(fields, { add, remove }) => (
                    <>
                        {fields.map(({ key, name, ...restField }) => (
                            <Space key={key} style={{ display: 'flex', marginBottom: 8 }} align="baseline">
                                <Form.Item
                                    {...restField}
                                    name={[name, 'id']}
                                    rules={[{ required: true, message: '缺失bookId' }]}
                                    style={{marginLeft:"380px"}}
                                >
                                    <Input placeholder="bookId" />
                                </Form.Item>
                                <MinusCircleOutlined onClick={() => remove(name)} />
                            </Space>
                        ))}
                        <Form.Item>
                            <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined />} style={{width:"700px", marginLeft:"360px"}} >
                                点击删除图书
                            </Button>
                        </Form.Item>
                    </>
                )}
            </Form.List>
            <Form.Item>
                <Button type="primary" htmlType="submit"  className="login-form-button" style={{width:"700px", marginLeft:"360px"}}>
                    确认删除
                </Button>
            </Form.Item>
        </Form>
    );
};

export default DeleteBookForm;
