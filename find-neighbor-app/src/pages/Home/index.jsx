import React from 'react';
import './Home.scss';
import { Messages, Status, ChatInput } from '../../components';
import { Button, Icon } from 'antd';
import {  Dialogs } from '../../containers';
import dialogsJSON from "../../dialogs.json";

const index = () => {
    return (
    <section className="home">
        <div className="chat">
            <div className="chat__sidebar">

                <div className="chat__sidebar-header">
                    <div>
                        <Icon type="team" />
                        <span>Список діалогів</span>
                    </div>
                    <Button icon={<Icon type="form" />}/>
                </div>
                
                <div className="chat__sidebar-dialogs">
                <Dialogs
                        userId={0}
                        items={dialogsJSON}
                    />
                </div>
            </div>
            <div className="chat__dialog">
                <div className="chat__dialog-header">
                    <div />
                    <div className="chat__dialog-header-center">
                    <b className="chat__dialog-header-username">Аня Хімія</b>
                    <div className="chat__dialog-header-status">
                            <Status online/>
                    </div>
                    </div>
                     <Button icon={<Icon type="ellipsis" style={{ fontSize : '22px' }}  />}/>
                </div>
                <div className="chat__dialog-messages">
                   <Messages items />                   
                </div>
                    <div className="chat__dialog-input">
                        <ChatInput />
                    </div>
            </div>
        </div>
     </section>
    );
};

export default index;