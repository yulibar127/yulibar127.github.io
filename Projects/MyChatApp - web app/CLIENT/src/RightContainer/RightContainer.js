import React, { useState,useEffect } from 'react';
import RightHeader from "../RightHeader/RightHeader";
import ChatText from "../ChatText/ChatText";
import ChatContainer from "../ChatContainer/ChatContainer";

function RightContainer({chatbox,username,token}) {
  
  useEffect(() => {
    
    // This effect will run whenever the chatbox prop changes
  }, [chatbox]);

  if (chatbox) {
    return (
      <div className="right-container">
            <RightHeader chatbox={chatbox}/>
            <ChatContainer chatbox={chatbox} username={username} token={token}/>
            
        </div>
    );
  } else {
    return (
      <RightHeader chatbox={chatbox}/>
    );
  }}
 
    

export default RightContainer;