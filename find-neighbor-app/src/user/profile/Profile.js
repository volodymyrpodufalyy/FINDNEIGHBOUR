import React, { Component } from 'react';
// import PollList from '../../poll/PollList';
import { getCurrentUser, getUserProfile } from '../../util/APIUtils';
import { Avatar, Tabs } from 'antd';
// import { getAvatarColor } from '../../util/Colors';
import { formatDate } from '../../util/Helpers';
import LoadingIndicator  from '../../common/LoadingIndicator';
import './Profile.css';
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';

const TabPane = Tabs.TabPane;

let info = React.createRef();

const varimg = "http://ocalafarms.com/wp-content/uploads/2016/09/default-user-img.jpg"

class Profile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            isLoading: false
        }
        this.loadUserProfile = this.loadUserProfile.bind(this);
    }

    loadUserProfile(username) {
        this.setState({
            isLoading: true
        });

        getCurrentUser()
        .then(response => {
            this.setState({
                user: response,
                isLoading: false
            });
        }).catch(error => {
            if(error.status === 404) {
                this.setState({
                    notFound: true,
                    isLoading: false
                });
            } else {
                this.setState({
                    serverError: true,
                    isLoading: false
                });
            }
        });
    }

    componentDidMount() {
        const username = this.props.match.params.username;
        this.loadUserProfile(username);
    }

    componentDidUpdate(nextProps) {
        if(this.props.match.params.username !== nextProps.match.params.username) {
            this.loadUserProfile(nextProps.match.params.username);
        }
    }

    render() {
        if(this.state.isLoading) {
            return <LoadingIndicator />;
        }

        if(this.state.notFound) {
            return <NotFound />;
        }

        if(this.state.serverError) {
            return <ServerError />;
        }

        const tabBarStyle = {
            textAlign: 'center'
        };

        return (
            <div className="Profile-main-page">
                {
                    this.state.user ? (
                        <div className="Profile-div1">
                        <div className='profile-user-img'><p><a href={varimg}><img src={varimg}/></a></p></div>
                        <div className='profile-user-name'><p>{this.state.user.name}</p></div>
                        {/* <div><NavLink to={"./Settings"} className='settings' >Settings</NavLink></div> */}
                        <div className="profile-user-mail"><p>{this.state.user.id}</p></div>
                        <div className='kind_of_activity'><p>Стать: {this.state.user.sex}</p></div>
                        <div >
                            <div><p className='About_p'>About:</p></div>
                            <textarea className='profile-user-textarea' ref={info} readOnly maxLength={300}>{"this.state.user.id"}</textarea>
                        </div>
                    </div>
                    ): null
                }
            </div>
        );
    }
}

export default Profile;
