import React from 'react';
import '../css/footer.css'
import { Link } from 'react-router-dom';



class MyFooter extends React.Component
{

    render(){
        return (
            <div style={{marginTop:"35px"}}>
                <myfooter  style={{fontSize:"15px"}}>
                    <div className="footer--col">
                        <a href="#" className="col--a">关于我们</a>
                        <a href="#" className="col--a">服务条款</a>
                        <a href="#" className="col--a">隐私政策</a>
                    </div>
                    <div className="footer--col">
                        <span className="col--heading" style={{color:"#FFFFFF"}}>烟海书店</span>
                        <div className="col--a">中国，上海</div>
                        <div className="col--item">copyright ©2022</div>
                    </div>
                </myfooter>
            </div>
        )
    }

}

export default MyFooter;
