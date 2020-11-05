import React, { Component } from "react";
import './FindNeighbor.css';
import {getUserAdditionalInfo, getUserProfile, userFiltration, checkUserAvailability} from "../util/APIUtils";
import ListOfNeighbor from "./ListOfNeighbor";
import { Card } from 'antd';
class FindNeighbor extends Component {
    constructor(props) {
        super(props);
        this.state = {
            fullAddress: true,
            sex: true,
            age: true,
            usernameInfo: this.props.currentUser,
            peoplesWhoFitCriteria: [{}],
            checkUser: true

        };

        this.filterRequest = this.filterRequest.bind(this);
        this.userBlock=this.userBlock.bind(this);
        this.checkAllInformationIsAvailable=this.checkAllInformationIsAvailable.bind(this);
    }


    userBlock = () =>  {
        return(/*<div className="site-card-border-less-wrapper">
            <Card title="Card title" bordered={false} style={{ width: 300 }}>*/
            <div className="userBlock">
            <p>{}</p>
            </div>)
           /* </Card>
        </div>)*/
    };

    getDocFinancialInfo = docId => {
        this.setState({ peoplesList: docId});
        console.log(docId);
        console.log(this.state.peoplesWhoFitCriteria);
    };


    checkAllInformationIsAvailable(event) {
        event.preventDefault();
        this.state.userId = this.state.usernameInfo.id;
        checkUserAvailability(this.state.userId)
            .then(response => {
                if(response.additionalInfoIsAvailable && response.addressIsAvailable && response.preferencesIsAvailable){
                    this.filterRequest(event);
                }
                else {
                    this.state.checkUser = false
                }
            });
    }




    filterRequest(event) {
        event.preventDefault();

        this.state.username = this.state.usernameInfo.name;
        console.log(this.state.usernameInfo.id, this.state.fullAddress, this.state.age, this.state.sex);
        userFiltration(this.state.usernameInfo.id, this.state.fullAddress, this.state.age, this.state.sex)
            .then(response => {
                if(response) {
                    console.log(response)
                    this.state.peoplesWhoFitCriteria = [...response];
                    console.log(this.state.peoplesWhoFitCriteria);
                    this.getDocFinancialInfo(response);
                }
            });

        this.userBlock();

    }

    toggleChangeFullAddress = () => {
        this.setState({
            fullAddress: !this.state.fullAddress
        });
    };
    toggleChangeSex = () => {
        this.setState({
            sex: !this.state.sex
        });
    };

    toggleChangeAge = () => {
        this.setState({
            age: !this.state.age
        });
    };

    render() {
        let name;
        console.log(this.state.peoplesWhoFitCriteria);
        if(!this.state.checkUser){
            name = <p>Sorry, you have not filled everything</p>
        }
        else {
            if (this.state.peoplesWhoFitCriteria.length < 1) {
                name = <p>Sorry, we dont find any user</p>
            } else {
                name = <ListOfNeighbor peoplesList={this.state.peoplesList} usernameInfo={this.state.usernameInfo}/>
            }
        }
        return (
            <div>
                <div className="find-wrap">
                    <label>
                        <input type="checkbox"
                               checked={this.state.fullAddress}
                               onChange={this.toggleChangeFullAddress}
                        />
                        Full address
                    </label>
                    <label>
                        <input type="checkbox"
                               checked={this.state.sex}
                               onChange={this.toggleChangeSex}
                        />
                        Gender
                    </label>
                    <label>
                        <input type="checkbox"
                               checked={this.state.age}
                               onChange={this.toggleChangeAge}
                        />
                        Age
                    </label>
                    <button className="find-button" onClick={this.checkAllInformationIsAvailable.bind(this)} >
                        Click to show modal
                    </button>
                </div>
                {name}
            </div>
        );
    }
}

export default FindNeighbor;