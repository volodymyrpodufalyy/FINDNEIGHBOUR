import React, { Component } from 'react';
import './App.css';
import {
    Route,
    withRouter,
    Switch
} from 'react-router-dom';
import { ACCESS_TOKEN } from '../constants';
import Signup from '../user/signup/Signup';
import Address from '../user/signup/Address';
import Profile from '../user/profile/Profile';
import AppHeader from '../common/AppHeader';
import NotFound from '../common/NotFound';
import LoadingIndicator from '../common/LoadingIndicator';
import PrivateRoute from '../common/PrivateRoute';

import { Layout, notification } from 'antd';
import Login from "../user/login/Login";
import {getCurrentUser} from "../util/APIUtils";
import ServerError from "../common/ServerError";
import Main from "../components/Main";
import Filters from "../user/signup/Filters";
import AdditionalInfo from "../user/signup/AdditionalInfo";
import FindNeighbor from "../components/FindNeighbor";
import Footer from '../components/Footer/Footer';
import UserImage from "../user/signup/UserImage";
import { Home } from '../pages';
import classNames from 'classnames';

const { Content } = Layout;

function disableScrolling(){
    var x=window.scrollX;
    var y=window.scrollY;
    window.onscroll=function(){window.scrollTo(x, y);};
}


class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: null,
            isAuthenticated: false,
            isLoading: false,
            isChat: false
        }
        this.handleLogout = this.handleLogout.bind(this);
        this.loadCurrentUser = this.loadCurrentUser.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
        this.handleChat = this.handleChat.bind(this);

        notification.config({
            placement: 'topRight',
            top: 70,
            duration: 3,
        });
    }

    loadCurrentUser() {
        this.setState({
            isLoading: true
        });
        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    isAuthenticated: true,
                    isLoading: false
                });
            }).catch(error => {
            this.setState({
                isLoading: false
            });
        });

    }

    componentDidMount() {
        this.loadCurrentUser();
    }

    handleLogout(redirectTo="/", notificationType="success", description="You're successfully logged out.") {
        localStorage.removeItem(ACCESS_TOKEN);

        this.setState({
            currentUser: null,
            isAuthenticated: false
        });

        this.props.history.push(redirectTo);

        notification[notificationType]({
            message: 'Find Neighbor App',
            description: description,
        });
    }

    handleLogin() {
        notification.success({
            message: 'Find Neighbor App',
            description: "You're successfully logged in.",
        });

        this.loadCurrentUser();
        this.props.history.push("/");
    }

    handleChat() {
        this.setState({isChat: true});
      }

    render() {
        if(this.state.isLoading) {
            return <LoadingIndicator />
        }
        return (
            <Layout style={{height:"100vh"}} className="app-container">
                 <div className="AppHeader">
                                    <AppHeader isAuthenticated={this.state.isAuthenticated}
                                            currentUser={this.state.currentUser}
                                            onLogout={this.handleLogout}>
                                    </AppHeader>
                                </div>

                
                    <Content className="app-content">
                        
                           
                            <Switch>
                                <Route exact path="/"
                                       render={(props) => <Main isAuthenticated={this.state.isAuthenticated}
                                                                currentUser={this.state.currentUser} handleLogout={this.handleLogout} {...props} />}>
                                </Route>
                                <Route path="/login"
                                       render={(props) => <Login onLogin={this.handleLogin} {...props} />}></Route>
                                <Route path="/signup"
                                       render={(props) => <Signup onLogin={this.handleLogin} {...props} isAuthenticated={this.state.isAuthenticated}/>}></Route>
                               
                                <Route path="/:username/additionalInfo" component={AdditionalInfo}></Route>
                                
                                <Route path="/:username/userImage" component={UserImage}></Route>
                                
                                <Route path="/:username/filters" component={Filters}></Route>
                                
                                {/*<Route*/}
                                {/*    //  currentUser={this.state.currentUser}*/}
                               
                                {/*    path="/:username/address" component={Address}></Route>*/}
                                  
                                   <Route                  
                                    path="/:username/address"  render = {(props) => <Address {...props} onLogin={this.handleLogin} isAuthenticated={this.isAuthenticated}
                                                                       currentUser={this.currentUser}/>}>
                                    </Route>
                                <Route path="/users/:username"
                                       render={(props) => <Profile isAuthenticated={this.state.isAuthenticated} currentUser={this.state.currentUser} {...props}  />}>
                                </Route>
                                <Route path="/findNeighbor/"
                                       render={(props) => <FindNeighbor authenticated={this.state.isAuthenticated}
                                                                        handleLogout={this.handleLogout} currentUser={this.state.currentUser}{...props} /> }></Route>
                               
                                
                                <Route path="/im" 
                                       render={(props) => <Home isAuthenticated={this.state.isAuthenticated} currentUser={this.state.currentUser} {...props}  />}>
                                </Route>
                                
                                
                                {/*<PrivateRoute authenticated={this.state.isAuthenticated} path="/findNeighbor/" component={NotFound} handleLogout={this.handleLogout}></PrivateRoute>*/}


                                <Route path="/:username/main" component={Main}></Route>
                                <Route component={NotFound}></Route>

                            </Switch>
                          
                        
                    </Content>
                
                
            </Layout>
        );
    }
}

export default withRouter(App);
