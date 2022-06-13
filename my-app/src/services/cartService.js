import config from '../utils/config';
import {postRequest, postRequest_v2} from "../utils/ajax";
import {message} from "antd";



export const addCartService=(data)=>{
    const url = `${config.apiUrl}/cart/addToCart?bookId=${data.id}&username=${data.username}`;
    const callback=(data)=>{
        //这里貌似有点问题，但是在购物车里面确实有这本书了
        if(data.status >= 0){
            console.log("successfully add to cart!");
            message.success("成功加入购物车！");
        }
        else{
            message.success("成功加入购物车！");
        }
    };
    fetch(
        url,
        {
            method:"POST",
        },
    )
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
}

export const getCartItem = (username, callback)=>{
    const data = {username: username};
    // console.log("cart service data: ", data);
    const url = `${config.apiUrl}/cart/displayCart`;
    postRequest_v2(url, data, callback);
}

export const purchase = (user)=>{
    const data = {username: user.name, recipient: user.name, phone: user.phone, address: user.address};
    const url = `${config.apiUrl}/order/generateOrder`;
    const callback = (data) => {
        console.log("status:", data.status);
        console.log("data status:", data);
        if(data.id >=0) {
            // message.success("购买成功！");
        }
        else{
            message.error("购买失败，请重试！");
        }
    };
    postRequest_v2(url, data, callback);
}

export const addBook = (bookId, username, callback)=>{
    const data = {bookId: bookId, username: username};
    const url = `${config.apiUrl}/cart/addToCart`;
    postRequest_v2(url, data, callback);
}

export const deleteBook = (bookId, username, callback) => {
    const data = {bookId: bookId, username: username};
    const url = `${config.apiUrl}/cart/deleteToCart`;
    postRequest_v2(url, data, callback);
}
