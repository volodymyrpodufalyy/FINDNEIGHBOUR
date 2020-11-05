import React, { Component } from 'react';
import {
    Link,
    withRouter
} from 'react-router-dom';
import './AppHeader.css';

import { Layout, Menu, Dropdown, Icon } from 'antd';
import Profile from "../user/profile/Profile";
const Header = Layout.Header;

class AppHeader extends Component {
    constructor(props) {
        super(props);
        this.handleMenuClick = this.handleMenuClick.bind(this);
    }


    handleMenuClick({ key }) {
        if (key === "logout") this.props.onLogout();
    }


    render() {
        let menuItems;
        console.log(this.props.currentUser);
        if(this.props.currentUser) {
            menuItems = [
                <Menu.Item key="" className="newMenu">
                    <Link  className="menuLink" to="">Chat</Link>
                </Menu.Item>,
                <Menu.Item key="" className="newMenu">
                    <Link className="menuLink" to="">Settings</Link>
                </Menu.Item>,
                <Menu.Item key="" className="newMenu">
                    <Link className="menuLink" to="/findNeighbor">Find Neighbor</Link>
                 </Menu.Item>,
                <Menu.Item key="/profile" className="newMenu">
                    <Link className="menuLink" to={`/users/${this.props.currentUser.username}`}>Profile</Link>
                </Menu.Item>,
                <Menu.Item key="logout" className="newMenu">
                    <Link className="menuLink" onClick={this.props.onLogout} to="/login">Logout</Link>
                </Menu.Item>
            ];
        } else {
            menuItems = [
                <Menu.Item key="/login" className="newMenu">
                    <Link  className="menuLink" to="/login">Login</Link>
                </Menu.Item>,
                <Menu.Item key="/signup" className="newMenu">
                    <Link className="menuLink" to="/signup">Signup</Link>
                </Menu.Item>
            ];
        }

        return (
            <div className="app-header">
                <Link to="/" className="app-title" >Find Neighbor App</Link>
                <div className="newMenu">
                    <Menu
                        selectedKeys={[this.props.location.pathname]}>
                        {menuItems}
                    </Menu>
                </div>
            </div>
        );
    }
}

export default withRouter(AppHeader);