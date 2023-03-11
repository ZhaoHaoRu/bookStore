import React from 'react';
import 'antd/dist/antd.min.css';
import { Form, Input, Button, Space } from 'antd';
import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons';
import "../css/admin.css";
import {addBook} from "../services/bookService";

const AddBookForm = () => {
    const onFinish = values => {
        console.log('Received values of add book form:', values);
        addBook(values);
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
                                    name={[name, 'ISBN']}
                                    rules={[{ required: true, message: '缺失ISBN' }]}
                                >
                                    <Input placeholder="ISBN" />
                                </Form.Item>
                                <Form.Item
                                    {...restField}
                                    name={[name, 'name']}
                                    rules={[{ required: true, message: '缺失书名' }]}
                                >
                                    <Input placeholder="书名" />
                                </Form.Item>
                                <Form.Item
                                    {...restField}
                                    name={[name, 'type']}
                                    rules={[{ required: true, message: '缺失类型' }]}
                                >
                                    <Input placeholder="类型" />
                                </Form.Item>
                                <Form.Item
                                    {...restField}
                                    name={[name, 'author']}
                                    rules={[{ required: true, message: '缺失作者' }]}
                                >
                                    <Input placeholder="作者" />
                                </Form.Item>
                                <Form.Item
                                    {...restField}
                                    name={[name, 'priceYuan']}
                                    rules={[{ required: true, message: '缺失价格' }]}
                                >
                                    <Input placeholder="价格(元）" />
                                </Form.Item>
                                <Form.Item
                                    {...restField}
                                    name={[name, 'priceJiao']}
                                    rules={[{ required: true, message: '缺失价格' }]}
                                >
                                    <Input placeholder="价格（小数部分）" />
                                </Form.Item>
                                <Form.Item
                                    {...restField}
                                    name={[name, 'description']}
                                    rules={[{ required: true, message: '缺失描述' }]}
                                >
                                    <Input placeholder="描述" />
                                </Form.Item>
                                <Form.Item
                                    {...restField}
                                    name={[name, 'inventory']}
                                    rules={[{ required: true, message: '缺失库存' }]}
                                >
                                    <Input placeholder="库存" />
                                </Form.Item>
                                <Form.Item
                                    {...restField}
                                    name={[name, 'image']}
                                    rules={[{ required: true, message: '缺失图片' }]}
                                >
                                    <Input placeholder="图片" />
                                </Form.Item>
                                <MinusCircleOutlined onClick={() => remove(name)} />
                            </Space>
                        ))}
                        <Form.Item>
                            <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined />} style={{width:"700px", marginLeft:"360px"}} >
                                点击增加新书
                            </Button>
                        </Form.Item>
                    </>
                )}
            </Form.List>
            <Form.Item>
                <Button type="primary" htmlType="submit"  className="login-form-button" style={{width:"700px", marginLeft:"360px"}}>
                    增加新书
                </Button>
            </Form.Item>
        </Form>
    );
};

export default AddBookForm;
