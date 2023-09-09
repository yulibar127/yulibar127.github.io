import ImageBox from "../ImageBox/ImageBox";
import ChatDetails from "../ChatDetails/ChatDetails";
import React, {useState,useEffect} from 'react';

function ChatBox({photo,head,time,message,number,id,onChatboxClick})
{
    const [ChatBoxUnreadMessages,setChatBoxUnreadMessages] = useState(number);
    
      const handleClick = () => {
        onChatboxClick(id);
        setChatBoxUnreadMessages(0);
      };
      
    

    return(
        <div className="chat-box" onClick={handleClick}>
         <ImageBox imageUrl={photo} />
         <ChatDetails textHead={head} textTime={time} textMessage={message} textNumber={ChatBoxUnreadMessages} /> 
        </div>
    );
    
}
export default ChatBox;