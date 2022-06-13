import {message} from 'antd';

let postRequest_v2 = (url, data, callback) => {
    let formData = new FormData();

    for (let p in data){
        if(data.hasOwnProperty(p))
            formData.append(p, data[p]);
    }

    let opts = {
        method: "POST",
        body: formData,
        credentials: "include"
    };

    fetch(url,opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            console.log("data:", data);
            console.log("get here!");
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
};


let postRequest = (url, json, callback) => {

    let opts = {
        method: "POST",
        body: JSON.stringify(json),
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: "include"
    };

    console.log("url:", url);
    console.log("json:", json);
    fetch(url,opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            callback(data);
        })
        .catch((error) => {
            console.log(json);
            console.log(error);
        });
};

export {postRequest,postRequest_v2};
