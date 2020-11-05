import React, {Component} from "react";
import people from "./people.png";
import {Button} from "antd";
import boy from "./boy.png";
import girlF from "./gilrF.png";
import girlS from "./girlS.png";


class Rewievs extends Component {

    onclick () {
        window.location.assign('../login');
    }


    render() {
        return (
            <div>
                <div className="reviews-container">
                    <div>
                        <img  src={girlF}/>
                        <div className="first">Yulia</div>
                    </div>
                    <div>
                        <img src={girlS}/>
                        <div className="first">Albertovna</div>
                    </div>
                    <div>
                        <img src={boy}/>
                        <div className="first">Valera</div>
                    </div>
                </div>
                <div className="about-project">
                        {/*<div className="about-title">*/}
                        {/*    About our project*/}
                        {/*</div>*/}
                        {/*<div className="about-text">*/}
                        {/*    Our project wants to help each of you who is looking for the perfect*/}
                        {/*    roommate to live in a shared apartment.*/}
                        {/*    <Button className="about-button" block onClick={(e) => this.onclick(e)}>Join</Button>*/}
                        {/*</div>*/}

                        {/*<img className="people" src={people}/>*/}
                    <div className="item1">
                        <p className="about-title">About our project</p>
                        <p className="about-text">
                            Our project wants to help each of you who is looking for the perfect
                            roommate to live in a shared apartment.
                        </p>
                    </div>
                    <div className="item2">
                        <img className="people" src={people}/>
                    </div>
                    <div className="item3">
                        <button className="about-button" block onClick={(e) => this.onclick(e)}>Join</button>
                    </div>

                </div>
            </div>
        )
    }
}

export default Rewievs