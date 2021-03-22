import React, {useState} from 'react';
import PropTypes from 'prop-types';
import './ChatInput.scss';
import { Input,Button,Icon } from 'antd';

const ChatInput = props => {
    const [value, setValue] = useState("");
    return (    
        <div className="chat-input">
        <div className="chat-input__smile-btn">
        <Button icon="smile" />
        </div>
        <Input onChange={ e =>  setValue(e.target.value) } size='large' placeholder="Введіть текст повідомлення" />
        <div className="chat-input__actions"> 
            <Button icon="camera"/>
            {value ? <Button icon="right-circle"/> :<Button icon="audio"/>}
        </div>
        </div>        
    );
};

ChatInput.propTypes = {
    className: PropTypes.string
};

export default ChatInput;