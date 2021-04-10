import React, {Component} from "react";
import Rewievs from "./Rewievs";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import { Button } from 'antd';
import join from './main.png';
import Footer from '../Footer/Footer';
import './MainPage.css';

class MainPage extends Component {
    constructor(props, context) {
        super(props, context);
    }

    onclick () {
        window.location.assign('../signup');
    }

    render() {
        return(
            <div>
                <img className="main-image" src={join}/>
                
                <p className="title__text">Find neighbour</p>

                <p className="sub-text">The social network for your neighborhood.</p>

                <button className="join__us" block onClick={(e) => this.onclick(e)}>Join us</button>

                <Container>
                    <Row>
                        <div className="reviews">Reviews</div>
                    </Row>
                </Container>

               <Rewievs />
               <Footer/>
            </div>
           
        );
    }
}

export default MainPage