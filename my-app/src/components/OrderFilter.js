import React from "react";
import OneOrderList from "./OrderItem";
import {Divider, List} from "antd";

class OrderFilter extends React.Component{
    render() {
        const filterText = this.props.filterText;
        const rows = [];
        if(JSON.stringify(filterText) === '{}'){
            this.props.orderList.forEach((order) => {
                console.log(order);
                rows.push(order);
            });
        }
        else {
            this.props.orderList.forEach((order) => {
                console.log(order);
                for (let i = 0; i < order.length; i++) {
                    if (order[i].book.name.indexOf(filterText) === -1) {
                        console.log(JSON.stringify(filterText) === '{}');
                        continue;
                    } else {
                        rows.push(order);
                        break;
                    }
                }
                console.log("One Order:", order[0]);
                // rows.push(order);
                // console.log("rows", rows);
            });
        }
        return (
            <List
                grid={{gutter: 10, column: 1}}
                dataSource={rows}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 4,
                }}

                renderItem={item => (
                    <div>
                        <Divider orientation="left">我在{item[0].orderId.addDate.substr(0,10)}的订单</Divider>
                        <List.Item>
                           <OneOrderList order={item}/>
                        </List.Item>
                    </div>
                )}
            />
        );
    }
}

export default OrderFilter;
