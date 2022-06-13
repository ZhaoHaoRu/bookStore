import RegisterForm from "../components/RegisterForm";
import React from "react";
import '../css/login.css';
import {withRouter} from "react-router-dom";

class RegisterView extends React.Component{


    render(){
        return(
            <div className="login-page">
                <div className="register-container">
                    <div className="login-box">
                        <h1 className="page-title">注册</h1>
                        <div className="login-content" >
                            <RegisterForm />
                        </div>
                    </div>
                </div>
            </div>
        );

    }
}

export default withRouter(RegisterView);
