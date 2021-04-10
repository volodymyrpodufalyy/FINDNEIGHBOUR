import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { Empty } from "antd";

import { Message } from '../';


const Messages = ({ items }) => {
    
    return items ? (<div>
         <Message avatar="https://i.pinimg.com/236x/fc/cd/70/fccd700ceee8a7a7a7d99565369bdc71.jpg"
                    date="Tue Mar 09 2021 23:08:31" 
                    audio='https://notificationsounds.com/storage/sounds/file-39_martian-gun.mp3' />
                    <Message avatar="https://i.pinimg.com/236x/fc/cd/70/fccd700ceee8a7a7a7d99565369bdc71.jpg" text="Салам Брат!"
                    date="Tue Mar 09 2021 23:08:31" 
                    attachments={[
                        {
                            filename: 'imada.jpg',
                            url: 'https://source.unsplash.com/user/erondu/100x100',
                        },
                        {
                            filename: 'imada.jpg',
                            url: 'https://source.unsplash.com/collection/190727/100x100',
                        },
                        {
                            filename: 'imada.jpg',
                            url: 'https://source.unsplash.com/100x100/?nature,water',
                        }
                    ]}/>
                    <Message avatar="https://i.pinimg.com/736x/fe/36/fa/fe36fa00b61759e9d34a78814caf68dc.jpg" text="Салам Бродяга!"
                    date="Tue Mar 09 2021 23:10:31" 
                    isMe={true}
                    isReaded={false} 
                    />
                    <Message avatar="https://i.pinimg.com/236x/fc/cd/70/fccd700ceee8a7a7a7d99565369bdc71.jpg"
                    date="Tue Mar 09 2021 23:12:31" 
                    attachments={[
                        {
                            filename: 'imaga.jpg',
                            url: 'https://source.unsplash.com/user/erondu/150x150',
                        }
                    ]}/>
                     <Message avatar="https://i.pinimg.com/236x/fc/cd/70/fccd700ceee8a7a7a7d99565369bdc71.jpg"
                    date="Tue Mar 09 2021 23:12:31" 
                    attachments={[
                        {
                            filename: 'imaga.jpg',
                            url: 'https://source.unsplash.com/user/erondu/150x150',
                        }
                    ]}/>
    </div>) : ( <Empty  description="Відкрийте діалог,щоб почати спілкування" />) ;
};

Message.propTypes = {
    items: PropTypes.array,
};


export default Messages;