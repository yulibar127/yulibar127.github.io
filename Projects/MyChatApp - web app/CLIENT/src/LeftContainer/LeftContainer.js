import ChatList from "../ChatList/ChatList";
import LeftHeader from "../LeftHeader/LeftHeader";
import SearchContainer from "../SearchContainer/SearchContainer";
import ChatBox from "../ChatBox/ChatBox";
import React, {useState} from 'react';

function LeftContainer({ chatboxes ,add,username,token})
{
    return(
        <div className="left-container">
            <LeftHeader add={add} username={username} token={token}/>
            
            <SearchContainer/>
            <ChatList chatboxes={chatboxes}/>
        </div>
    );
    
    
}
export default LeftContainer;