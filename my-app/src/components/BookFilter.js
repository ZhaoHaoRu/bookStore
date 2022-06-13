import React from "react";
import {Book} from "./Book";
import {List} from "antd";

class BookFilter extends React.Component{
    render() {
        const filterText = this.props.filterText;
        const rows = [];
        this.props.books.forEach((book) => {
            if (book.name.indexOf(filterText) === -1) {
                return;
            }
            rows.push(
               book
            );
            console.log(rows);
        });
        return (
            <List
                grid={{gutter: 10, column: 4}}
                dataSource={rows}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 16,
                }}

                renderItem={item => (
                    <List.Item>
                        <Book info={item} />
                    </List.Item>
                )}
            />
        );
    }
}

export default BookFilter;
