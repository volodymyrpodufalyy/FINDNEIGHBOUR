import React from 'react';
import { IconReaded } from '../';
import classNames from 'classnames';
import format from 'date-fns/format';
import isToday from 'date-fns/isToday';
import { Avatar } from '../';


const getMessageTime = created_at  =>{
    if (isToday(new Date(created_at))) {
        return format(new Date(created_at), "HH:mm:ss");
      } else {
        return format(new Date(created_at), "dd.MM.yyyy");
      }
};

const DialogItem = ({ user, created_at, text, unreaded, isMe }) => ( 
    <div className={classNames("dialogs__item", {"dialogs__item--online": user.isOnline})}>
         <div className="dialogs__item-avatar"> <Avatar user={user} /> </div>
        <div className="dialogs__item-info">
        <div className="dialogs__item-info-top">
                <b>{user.fullname}</b>
                <span> {getMessageTime(created_at)} </span>
            </div>
            <div className="dialogs__item-info-buttom">
                <p>{text}</p>
               {isMe && <IconReaded isMe={true} isReaded={false} />}
                {unreaded > 0 && (<div className="dialogs__item-info-buttom-count">{unreaded > 9 ? '+9' : unreaded}</div>)}
            </div>
        </div>
    </div>
);

export default DialogItem;