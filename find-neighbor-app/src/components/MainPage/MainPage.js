import { Button } from 'antd';
import './MainPage.css';
import join from './main.png';
import React, {Component} from "react";
import Rewievs from "./Rewievs";
import Container from 'react-bootstrap/Container';
import black from './black.png';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import people from './people.png';

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
                <p className="text">Find neighbor</p>

                <p className="sub-text">The social network for your neighborhood.</p>

                <Button className="join-us" block onClick={(e) => this.onclick(e)}>Join us</Button>

                <Container>
                    <Row>
                        <div className="reviews">Reviews</div>
                    </Row>
                </Container>

               <Rewievs />
            </div>
        );
    }
}

export default MainPage