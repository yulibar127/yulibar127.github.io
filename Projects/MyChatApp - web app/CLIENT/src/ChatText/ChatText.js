import React, {useEffect, useState} from 'react';

function ChatText({chatbox,token,increaseMessagesSent,username}) {
    
    const [message, setMessage] = useState('');
    const [socket, setSocket] = useState(null);

    useEffect(() => {
    const newSocket = new WebSocket('ws://localhost:5000');
    newSocket.onmessage = (event) => {
      const blobData = event.data;
      const reader = new FileReader();
      reader.onload = () => {
        const textData = reader.result;

        if(textData == username)
        {
          increaseMessagesSent();
          
        }

      };
      reader.readAsText(blobData);

      
    };
    setSocket(newSocket);

    /*
    return () => {
      newSocket.close();

    };
    */
    
  }, [increaseMessagesSent]);
    const handleClick = () => {
        const id=chatbox.props.id;
        if (message !== '' && socket && socket.readyState === WebSocket.OPEN) {
          socket.send(
            chatbox.props.head
          );
        }
        if (message !== '') {
            const postData = { msg: message };
            fetch(`http://localhost:5000/api/Chats/${id}/Messages`, {
            method: 'POST',
            headers: {
            'accept' : 'application/json',
            'Authorization' : `Bearer ${token}`,
            'Content-Type' : 'application/json'
         },
         body: JSON.stringify(postData)
        })
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Unable to send message.');
          }
        })
        .then(data => {
          increaseMessagesSent();
          
        })
        .catch(error => {
          console.error('Error:', error);
        });
            setMessage('')
            
            
        }
        
    }

    const handleChange = (e) => {
        setMessage(e.target.value)
    }
    
    return (
        <div className="chatbox-input">
            <i className="fa-regular fa-face-grin-tongue" />
            <input type="text" value={message} onChange={handleChange} placeholder="Type a message" spellCheck="true" data-ms-editor="true" />
            <i className="fa-solid fa-hand-point-right" onClick={handleClick} />
        </div>
    );
}
export default ChatText;