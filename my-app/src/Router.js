import React from 'react';
import { Router, Route, Switch, Redirect} from 'react-router-dom';
import PrivateRoute from './PrivateRoute';
import LoginRoute from  './LoginRoute';
import BrowseView from "./view/BrowseView";
import LoginView from './view/LoginView';
import {history} from "./utils/history";
import DetailView from "./view/DetailView";
import CartView from "./view/CartView";
import RegisterView from "./view/RegisterView";
import OrderView from "./view/OrderView";
import PayView from "./view/PayView";
import StatisticsView from "./view/StatisticsView";

class BasicRoute extends React.Component{

    constructor(props) {
        super(props);

        history.listen((location, action) => {
            // clear alert on location change
            console.log(location,action);
        });
    }


    render(){
        return(
            <Router history={history}>
                <Switch>
                    <Route exact path="/statistics" component={StatisticsView}/>
                    <LoginRoute  exact path="/" component={LoginView} />
                    <Route exact path="/register" component={RegisterView}/>
                    <PrivateRoute exact path="/bookDetails" component={DetailView}/>
                    <PrivateRoute exact path="/home" component={BrowseView}/>
                    <PrivateRoute exact path="/cart" component={CartView}/>
                    <PrivateRoute exact path="/order" component={OrderView}/>
                    <Redirect from="/*" to="/" />
                </Switch>

            </Router>
        )
    }


}

export default BasicRoute;
