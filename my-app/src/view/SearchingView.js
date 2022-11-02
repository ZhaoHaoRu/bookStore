import {Layout, Menu, Breadcrumb, Input, Space, Button, Row, Col} from 'antd';
import 'antd/dist/antd.css';
import '../css/browse.css';
import React from 'react';
import '../css/global.css'
import MyHeader from "../components/Header";
import MyFooter from "../components/Footer";
import SearchingList from "../components/SearchingList";
import SearchBar from "../components/SearchBar";
import {withRouter} from "react-router-dom";
import {query} from "../services/bookService";
const { Content} = Layout;

class SearchingView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            books:[],
            filterText:""
        };
    }


    render() {
        const onPress = () => {
            console.log("press button: ", this.state.filterText);
            query(this.state.filterText, (data) => {this.setState({books:data})});
        }

        const handleFilterTextChange = (e) => {
            this.setState({
                filterText: e.target.value
            });
        }

        return(
            <Layout className="layout background_pic" >
                <MyHeader />
                <Row gutter={24} style={{marginLeft:"20%"}}>
                    <Col className="gutter-row" span={18}>
                        <Input
                            placeholder="input search text"
                            enterButton="Search"
                            size="large"
                            style={{borderColor: 'rgb(189, 54, 47)', width: "100%", margin: "auto", marginTop: "2%"}}
                            onChange={handleFilterTextChange}
                        />
                    </Col>
                    <Col className="gutter-row" span={4}>
                        <Button style={{ width: "60%", margin: "auto", marginTop:"10%",borderColor: 'rgb(189, 54, 47)', backgroundColor: 'rgb(189, 54, 47)'}} size={'large'} onClick={onPress}>
                            search
                        </Button>
                    </Col>
                </Row>

                <Content style={{padding: '0 50px', backgroundColor:"white", marginTop:'2%', marginLeft:"10%", marginRight:"10%", minHeight:"400px"}}>
                    <SearchingList books={this.state.books} />
                </Content>
                <MyFooter />
            </Layout>
        );
    }
}

export default withRouter(SearchingView);
