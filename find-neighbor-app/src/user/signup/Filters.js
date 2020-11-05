import React, {Component} from "react";
import {additionalInfo, filterRequest, signup} from "../../util/APIUtils";
import {Button, Form, Input, notification} from "antd";
import FormItem from "antd/lib/form/FormItem";
import './Filters.css';

class Filters extends Component {
    constructor(props) {

        super(props);
        this.state = {
            startAge: '',
            endAge: '',
            sex: '',
            pets: '',
            badHabits: '',
            startPrice: '',
            endPrice: '',
            userId: this.props.match.params
        };

        this.handleSubmit=this.handleSubmit.bind(this);
        this.handleChangeBadHabits = this.handleChangeBadHabits.bind(this);
        this.handleChangePets = this.handleChangePets.bind(this);
        this.handleChangeSex = this.handleChangeSex.bind(this);
        this.validateStartAge=this.validateStartAge.bind(this);
        this.validateEndAge=this.validateEndAge.bind(this);
        this.handleInputChange=this.handleInputChange.bind(this);
        this.handleEndAge=this.handleEndAge.bind(this);
        this.handleStartPrice=this.handleStartPrice.bind(this);
        this.handleEndPrice=this.handleEndPrice.bind(this);
        this.isFormInvalid=this.isFormInvalid.bind(this);

    }


    handleChangeSex(event) {
        this.setState({
            sex: event.target.value
        });
    }

    handleStartPrice(event) {
        this.setState({
            startPrice: event.target.value
        });
    }

    handleEndPrice(event) {
        this.setState({
            endPrice: event.target.value
        });
    }

    handleChangeBadHabits(event) {
        this.setState({
            badHabits: event.target.value
        });
    }

    handleChangePets(event) {
        this.setState({
            pets: event.target.value
        });
    }

    handleEndAge(event) {
        this.setState({
            endAge: event.target.value
        });
    }



    handleSubmit(event) {
        event.preventDefault();
        console.log(this.state.userId);
        let username = this.state.userId;
        console.log(Object.values(username));
        const filtersInfoRequest = {
            badHabits: this.state.badHabits,
            pets: this.state.pets,
            sex: this.state.sex,
            startAge: this.state.startAge,
            endAge: this.state.endAge,
            startPrice: this.state.startPrice,
            endPrice: this.state.endPrice,
            userId: this.state.userId
        };

        filterRequest(filtersInfoRequest, this.props.match.params)
            .then(response => {
                console.log(this.props.match.params);
                let username = this.props.match.params;
                console.log(username);
                notification.success({
                    message: 'Find Neighbor App',
                    description: "Thank you!",
                });

                this.props.history.push("/"+Object.values(username)+"/address");
            }).catch(error => {
            notification.error({
                message: 'Find Neighbor App',
                description: error.message || 'Sorry! Something went wrong. Please try again!'
            });
        });

    }

    isFormInvalid() {
        return !(this.state.startAge.validateStatus === 'success'&&this.state.endAge.validateStatus === 'success'
        );
    }

    handleInputChange(event, validationFun) {
        const target = event.target;
        const inputName = target.name;
        const inputValue = target.value;

        this.setState({
            [inputName]: inputValue,
            ...validationFun(inputValue)
        });
    }
    validateStartAge = (startAge) => {
        if(startAge < 14) {
            return {
                validateStatus: 'error',
                errorMsg: 'Start age may not be less than 14'
            }
        }
        if(startAge > 120){
            return {
                validateStatus: 'error',
                errorMsg: 'Start age may not be more than 120'
            }
        }
        else {
            return {
                validateStatus: "success",
                errorMsg: null
            }
        }
    }
    validateEndAge = (endAge) => {
            if(endAge < this.state.startAge) {
                return {
                    validateStatus: 'error',
                    errorMsg: 'End age may not be less than start age'
                }
            }
            if (endAge > 200) {
                return {
                    validateStatus: 'error',
                    errorMsg: 'End age may not be more than 200'
                }
            }else {
                return {
                    validateStatus: "success",
                    errorMsg: null
                }
            }
        }




    render() {
        return (
            <form onSubmit={this.handleSubmit} className="filters-wrap">
                <h2 className="center">Here you can set your preferences</h2>
                <FormItem
                    className="center filters-container"
                    hasFeedback
                    validateStatus={this.state.validateStatus}
                    help={this.state.errorMsg}>
                    <h4>Please set your target age</h4>
                    From <Input
                        size="small"
                        name="startAge"
                        type="number"
                        autoComplete="off"
                        value={this.state.startAge}
                        onBlur={this.validateStartAge}
                        onChange={(event) =>
                            this.handleInputChange(event, this.validateStartAge)} /> to  <Input
                    size="small"
                    name="endAge"
                    type="number"
                    autoComplete="off"
                    value={this.state.endAge}
                    onBlur={this.validateEndAge}
                    onChange={(event) =>
                        this.handleInputChange(event, this.validateEndAge)} />
                </FormItem>
                <FormItem className="center filters-container">
                    <h4 className="center">Please set the desired price for the apartment</h4>
                    From <Input
                    size="small"
                    name="startPrice"
                    type="number"
                    autoComplete="off"
                    value={this.state.startPrice}
                    onChange={this.handleStartPrice} /> to  <Input
                    size="small"
                    name="endPrice"
                    type="number"
                    autoComplete="off"
                    value={this.state.endPrice}
                    onChange={
                        this.handleEndPrice} />
                </FormItem>
                <FormItem className="center filters-container">
                    <h4>Gender of your future neighbor: is it important for you?</h4>
                    <ul className="filters-list">
                        <li>
                            <label>
                                <p>Yes, I want to live with a man</p>
                                <input
                                    type="radio"
                                    value="man"
                                    checked={this.state.sex === "man"}
                                    onChange={this.handleChangeSex}
                                />
                            </label>
                        </li>

                        <li>
                            <label>
                                <p>Yes, I want to live with a woman</p>
                                <input
                                    type="radio"
                                    value="woman"
                                    checked={this.state.sex === "woman"}
                                    onChange={this.handleChangeSex}
                                />
                            </label>
                        </li>
                        <li>
                            <label>
                                <p>No difference</p>
                                <input
                                    type="radio"
                                    value="-"
                                    checked={this.state.sex === "-"}
                                    onChange={this.handleChangeSex}
                                />
                            </label>
                        </li>
                    </ul>
                </FormItem>
                <FormItem className="center filters-container">
                    <h4>How about pets?</h4>
                    <ul className="filters-list">
                        <li>
                            <label>
                                <p>Yes</p>
                                <input
                                    type="radio"
                                    value="yes"
                                    checked={this.state.pets === "yes"}
                                    onChange={this.handleChangePets}
                                />
                            </label>
                        </li>

                        <li>
                            <label>
                                <p>No</p>
                                <input
                                    type="radio"
                                    value="no"
                                    checked={this.state.pets === "no"}
                                    onChange={this.handleChangePets}
                                />
                            </label>
                        </li>

                        <li>
                            <label>
                                <p>I Don`t know</p>
                                <input
                                    type="radio"
                                    value="-"
                                    checked={this.state.pets === "-"}
                                    onChange={this.handleChangePets}
                                />
                            </label>
                        </li>
                    </ul>
                </FormItem>

                <FormItem className="center filters-container">
                    <h4>Are you against bad habits?</h4>
                    <ul className="filters-list">
                        <li>
                            <label>
                                <p>Yes</p>
                                <input
                                    type="radio"
                                    value="yes"
                                    checked={this.state.badHabits === "yes"}
                                    onChange={this.handleChangeBadHabits}
                                />
                            </label>
                        </li>

                        <li>
                            <label>
                                <p>No</p>
                                <input
                                    type="radio"
                                    value="no"
                                    checked={this.state.badHabits === "no"}
                                    onChange={this.handleChangeBadHabits}
                                />
                            </label>
                        </li>

                        <li>
                            <label>
                                <p>Don`t know</p>
                                <input
                                    type="radio"
                                    value="-"
                                    checked={this.state.badHabits === "-"}
                                    onChange={this.handleChangeBadHabits}
                                />
                            </label>
                        </li>
                    </ul>
                </FormItem>

                <button className="filters-form-button" type="submit">Ok</button>
            </form>

        );
    }
}


export default  Filters
