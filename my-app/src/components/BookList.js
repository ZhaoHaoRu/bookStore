import React from 'react';
import {List, Divider} from 'antd';
import {Book} from './Book';
import BookFilter from './BookFilter';
import SearchBar from "./SearchBar";
import AdminForm from "./AdminForm";
import AddBookForm from "./AddBookForm";
import DeleteBookForm from "./DeleteBookForm";
import BanUserForm from "./BanUserForm";
import {getBooks} from "../services/bookService";


class BookList extends React.Component{
    constructor(props) {
        super(props);
        this.state = {books:[],
            filterText: '',
            isAdmin:1,
        };
        this.handleFilterTextChange = this.handleFilterTextChange.bind(this);
    }

    componentDidMount() {
        let user = localStorage.getItem("user");
        let user_json = JSON.parse(user);
        this.setState({isAdmin:user_json.isAdministrators});
        const callback =  (data) => {
            this.setState({books:data});
        };

        getBooks({"search":null}, callback);

    }

    handleFilterTextChange(filterText) {
        this.setState({
            filterText: filterText
        });
    }
    render() {
        if(this.state.isAdmin)
            return(
                <div>
                    <div className="site-layout-content" style={{marginTop:"5%", marginBottom:"2%"}} >
                        <Divider plain style={{color:"rgb(189, 54, 47)"}}><a style={{fontSize:"30px", color:"rgb(189, 54, 47)", fontWeight:"bold"}}>管理员表单</a></Divider>
                        <AdminForm/>
                        <AddBookForm/>
                        <DeleteBookForm/>
                        <BanUserForm/>
                    </div>
                    <SearchBar
                        filterText={this.state.filterText}
                        onFilterTextChange={this.handleFilterTextChange}/>
                    <div className="site-layout-content">
                        <BookFilter books={this.state.books}
                                    filterText={this.state.filterText}/>
                    </div>
                </div>
            );
        return(
            <div>
                <SearchBar
                    filterText={this.state.filterText}
                    onFilterTextChange={this.handleFilterTextChange}/>
                <div className="site-layout-content">
                    <BookFilter books={this.state.books}
                                filterText={this.state.filterText}/>
                </div>
            </div>
        );
    }
}

export default BookList;
