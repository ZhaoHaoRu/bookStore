import config from '../utils/config';
import {postRequest, postRequest_v2} from "../utils/ajax";
import {history} from '../utils/history';
import {message} from 'antd';



export const login = (data) => {
    const url = `${config.apiUrl}/login`;
    console.log("PUSH ININ!!!!");
    const callback = (data) => {
        if(data.status >=0) {
            localStorage.setItem('user', JSON.stringify(data.data));
            console.log("push in:", data.data);
            history.push("/home");
            message.success("登录成功！");
        }
        else{
            message.error("登录失败，请检查您的信息或者您已被管理员禁用！");
        }
    };
    console.log("the data:", data);
    postRequest(url, data, callback);
};

export const register=(data)=>{
    const url=`${config.apiUrl}/register?username=${data.username}&password=${data.password}&email=${data.email}&phone=${data.phone}&address=${data.address}`;
    const callback = (data) => {
        console.log("getHere!");
        console.log("data after register: ", data);
        if(data.id >= 0) {
            history.push("/login");
            console.log("success");
            message.success("注册成功！");
        }
        else{
            message.error("注册失败！");
        }
    };
    fetch(
        url,
        {
            method: 'POST',
        },
    )
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
}

export const logout = () => {
    const url = `${config.apiUrl}/logout`;

    const callback = (data) => {
        if(data.status >= 0) {
            localStorage.removeItem("user");
            history.push("/login");
            message.success(data.msg, 20);
        }
        else{
            message.error(data.msg);
        }
    };
    postRequest(url, {}, callback);
};

export const checkSession = (callback) => {
    const url = `${config.apiUrl}/checkSession`;
    postRequest(url, {}, callback);
};


export const banUser = (data)=> {
    const send_data = {userId:data.user[0].id};
    const url = `${config.apiUrl}/admin/banUser`;
    const callback = (data) => {
        if(data.id >= 0) {
            message.success("成功操作！");
        }
        else{
            message.error("操作失败，用户id不正确或无权限！");
        }
    };
    postRequest_v2(url, send_data, callback);
}


