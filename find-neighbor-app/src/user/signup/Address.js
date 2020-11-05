import React, {Component} from "react";
import {addressRequest} from "../../util/APIUtils";
import {Button, Form, Input, notification} from "antd";
import FormItem from "antd/lib/form/FormItem";

import './AddressStyles.css';

class Address extends Component {
    constructor(props) {

        super(props);
        this.state = {
          country: '',
            city: '',
            area: ''

        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeCountry = this.handleChangeCountry.bind(this);
        this.handleChangeCity = this.handleChangeCity.bind(this);
        this.handleChangeArea = this.handleChangeArea.bind(this);
    }

    handleChangeCountry(event) {
        this.setState({
            country: event.target.value
        });
    }

    handleChangeCity(event) {
        this.setState({
            city: event.target.value
        });
    }

    handleChangeArea(event) {
        this.setState({
            area: event.target.value
        });
    }

    handleSubmit(event) {
        event.preventDefault();

        const addressRequestInfo = {
            country: this.state.country,
            city: this.state.city,
            area: this.state.area
        };

        addressRequest(addressRequestInfo, this.props.match.params)
            .then(response => {
                console.log(this.props.match.params);
                let username = this.props.match.params;
                console.log(username);
                notification.success({
                    message: 'Find Neighbor App',
                    description: "Thank you!",
                });

                this.props.onLogin();
            }).catch(error => {
            notification.error({
                message: 'Find Neighbor App',
                description: error.message || 'Sorry! Something went wrong. Please try again!'
            });
        });

    }

    render() {
        return (
            <div className="address-wrap">
                <h1 className="page-title">Address</h1>
                <div className="filter-content">
                    <Form onSubmit={this.handleSubmit} className="filter-form">
                        <FormItem label="Country">
                            <Input
                                size="large"
                                name="country"
                                autoComplete="off"
                                value={this.state.country}
                                onChange={this.handleChangeCountry} />
                        </FormItem>
                        <FormItem label="City">
                            <Input
                                size="large"
                                name="city"
                                autoComplete="off"
                                value={this.state.city}
                                onChange={this.handleChangeCity} />
                        </FormItem>
                        <FormItem label="Area">
                            <Input
                                size="large"
                                name="area"
                                autoComplete="off"
                                value={this.state.area}
                                onChange={this.handleChangeArea} />
                        </FormItem>
                        <FormItem>
                            <Button type="primary"
                                    htmlType="submit"
                                    size="large"
                                    className="signup-form-button"
                            >Add address</Button>

                        </FormItem>
                    </Form>
                </div>
            </div>
        );
    }
}

export default Address;