import React from "react";
import orderBy from "lodash/orderBy";
import { Input,Empty } from "antd";
import { DialogItem } from "../";
import "./Dialogs.scss";

const { Search } = Input;

const Dialogs = ({ items, userId,onSearch, inputValue }) => {
    return (<div className="dialogs">
    <div className="dialogs__search">
      <Search
        placeholder="Пошук серед контактів"
        onChange={e => onSearch(e.target.value)}
        value={inputValue}
      />
      </div>
           {items.length ? (orderBy(items,['created_at'],['desc']).map(item => ( 
            < DialogItem key={item._id} isMe={item.user._id === userId} {...item} />
            )) ) : (
                <Empty image={Empty.PRESENTED_IMAGE_DEFAULT} description="Нічого не знайдено" />
            )}        
      </div>);

};

export default Dialogs;
