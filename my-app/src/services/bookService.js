import config from '../utils/config';
import {postRequest, postRequest_v2} from "../utils/ajax";
import {history} from "../utils/history";
import {message} from "antd";

function handleResponse (url, response) {
    if (response.status < 500) {
        return response.json();
    } else {
        console.error(`Request failed. Url = ${url} . Message = ${response.statusText}`);
        return {error: {message: 'Request failed due to server error '}};
    }
}

export const getBooks = (data, callback) => {
    const url = `${config.apiUrl}/book/getBooks`;
    postRequest(url, data, callback);
};

export const getBook = (id, callback) => {
    const data = {id: id};
    const url = `${config.apiUrl}/book/getBook`;
    postRequest_v2(url, data, callback);
};

export const changeAttribute = (data)=> {
    const send_data = {id:data.bookId, attribute:data.attribute, newValue:data.newValue};
    const url = `${config.apiUrl}/book/changeAttribute`;
    console.log("change Attribute send data: ", send_data);
    const callback = (data) => {
        if(data.id >= 0) {
            message.success("成功修改！");
        }
        else{
            message.error("修改失败，请重试！");
        }
    };
    postRequest_v2(url, send_data, callback);
}

export const addBook = (data)=>{
    const send_data = {ISBN:data.book[0].ISBN, name:data.book[0].name, type:data.book[0].type, author:data.book[0].author, priceYuan:data.book[0].priceYuan, priceJiao:data[0].priceJiao, description:data.book[0].description, inventory:data.book[0].inventory, image:data.book[0].image};
    const url = `${config.apiUrl}/book/addBook`;
    const callback = (data) => {
        if(data.id >= 0) {
            message.success("成功添加！");
        }
        else{
            message.error("添加失败，请重试！");
        }
    };
    postRequest_v2(url, send_data, callback);
}


export const deleteBook = (data)=> {
    const send_data = {id:data.book[0].id};
    const url = `${config.apiUrl}/book/delete`;
    const callback = (data) => {
        if(data.id >= 0) {
            message.success("成功删除！");
        }
        else{
            message.error("删除失败，请重试！");
        }
    };
    postRequest_v2(url, send_data, callback);
}
