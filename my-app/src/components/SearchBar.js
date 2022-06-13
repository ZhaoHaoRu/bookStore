import React from 'react';
import 'antd/dist/antd.css';
import { Input, Space } from 'antd';
import { AudioOutlined } from '@ant-design/icons';
import '../css/browse.css';

const { Search } = Input;

const suffix = (
    <AudioOutlined
        style={{
            fontSize: 16,
            color: '#BD362F',
        }}
    />
);

const onSearch = value => console.log(value);

class SearchBar extends React.Component{
    constructor(props) {
        super(props);
        this.handleFilterTextChange = this.handleFilterTextChange.bind(this);
    }

    handleFilterTextChange(e) {
        this.props.onFilterTextChange(e.target.value);
    }
    render() {
        return(
            <Space direction="vertical" className="my_search_bar" >
                <Search
                    placeholder="在这里输入你想搜索的书名吧！"
                    // enterButton="Search"
                    size="large"
                    suffix={suffix}
                    onChange={this.handleFilterTextChange}
                    style={{background: 'rgb(189, 54, 47)',borderColor: 'rgb(189, 54, 47)', borderRadius:"20px"}}
                    className="search_input"
                />
            </Space>
        );
    }
}
export default SearchBar;
