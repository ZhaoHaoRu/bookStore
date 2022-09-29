import config from '../utils/config';
import {postRequest, postRequest_v2} from "../utils/ajax";
import {message} from "antd";



export const getOrders=(username,callback)=>{
    const data = {username: username};
    const url = `${config.apiUrl}/order/showOrder`;
    postRequest_v2(url, data, callback);
}

export const getOrderItem = (orderId, callback) => {
    const data = {orderId: orderId};
    const url = `${config.apiUrl}/order/getOneOrder`;
    postRequest_v2(url, data, callback);
}

export const getLatestOrder=(username, callback) => {
    const data = {username: username};
    const url = `${config.apiUrl}/order/getLatestOrder`;
    postRequest_v2(url, data, callback);
}

export const getBestSeller=(dateNum, callback) => {
    const data = {dateNum: dateNum};
    console.log("dateNum in orderService: ", data);
    const url = `${config.apiUrl}/order/showBestSeller`;
    postRequest_v2(url, data, callback);
}

export const getCustomers=(dateNum, callback) => {
    const data = {dateNum: dateNum};
    console.log("dateNum in customer: ", data);
    const url = `${config.apiUrl}/order/showCustomers`;
    postRequest_v2(url, data, callback);
}

export const getCustomerBuy=(username, dateNum, callback) => {
    const data = {username:username, dateNum:dateNum};
    console.log("parameter in customer: ", data);
    const url = `${config.apiUrl}/order/showCustomerBuy`;
    postRequest_v2(url, data, callback);
}

export const getOrderResult=(user, callback) => {
    const data = {username: user.name, recipient: user.name, phone: user.phone, address: user.address};
    const url = `${config.apiUrl}/order/queryOrder`;
    postRequest_v2(url, data, callback);
}
