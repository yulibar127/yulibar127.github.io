import React, { useEffect, useState } from 'react';
import MyMessage from '../MyMessage/MyMessage';
import FriendMessage from '../FriendMessage/FriendMessage';
import ChatText from '../ChatText/ChatText';
function ChatContainer({ chatbox, username, token}) {
  const [messages, setMessages] = useState([]);
  const [chatId, setChatId] = useState(chatbox.props.id);
  const [MessagesSent,setMessagesSent]=useState(0);
  const increaseMessagesSent = () => {

    setMessagesSent(prevMessagesSent=>prevMessagesSent + 1);
  }
    useEffect(() => {
    if (chatbox) {
      setChatId(chatbox.props.id);
    }
  }, [chatbox]);
  
  // ...

  useEffect(() => {

    if (chatId) {

        fetch(`http://localhost:5000/api/chats/${chatId}/messages`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
          'accept': 'application/json'
        }
      })
        .then(response => {
          if (response.ok) {
            
            return response.json();
          } else {
            throw new Error('Request failed');
          }
        })
        .then(data => {
          // Handle the data returned from the server
          setMessages(data);

        })
        .catch(error => {
          console.error(error);
        });
    }
  }, [chatbox,MessagesSent,increaseMessagesSent]);

// ...


  return (
    <>
  <div className="chat-container">
    {[...messages].reverse().map((message, index) => {
      
      if (message.sender.username !== username) {
        return <FriendMessage key={message.id} text={message.content} time={message.created} />;
      } else {
        return <MyMessage key={message.id} text={message.content} time={message.created} />;
      }
    })}
  </div>
  <ChatText chatbox={chatbox} token={token} increaseMessagesSent={increaseMessagesSent} username={username}/>
  </>
);

}

export default ChatContainer;
