import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import './AppHeader.css';
import { Layout, Menu, Button } from 'antd';
import { DownloadOutlined } from '@ant-design/icons';

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
                <Menu.Item key="chat" className="newMenu">
                    <Link  className="menuLink" to="/im">Chat</Link>
                </Menu.Item>,
                // <Menu.Item key="" className="newMenu">
                //     <Link className="menuLink" to="">Settings</Link>
                // </Menu.Item>,
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
                <Menu.Item key="/signup" className="newMenu">
                    <div className="signup__btn">
                    <Link className="register-link" to="/signup">Join</Link>
                    </div>
                </Menu.Item>,
                <Menu.Item key="/login" className="newMenu">
                    <Link  className="login-link" to="/login">Have an account</Link>
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