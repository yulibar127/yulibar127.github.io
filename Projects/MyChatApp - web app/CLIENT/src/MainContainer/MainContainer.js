import LeftContainer from "../LeftContainer/LeftContainer";
import RightContainer from "../RightContainer/RightContainer";
import React, {useState, useEffect} from 'react';
import ChatBox from "../ChatBox/ChatBox";

function MainContainer(props) {
  const time = new Date().toLocaleTimeString();


    const userdata = props.userData;
    const logout = props.logout;
    const username = props.username;  // Assuming you pass username as a prop.
    const [CurrentChatboxIndex, setCurrentChatboxIndex] = useState(0);

  
    const [isLoading, setIsLoading] = useState(true);
    const [ChatCount,increase] = useState(0);
    const [chatboxes, setchatbox] = useState([]);
    const handleChatboxClick = (id) => {

      const index = chatboxes.findIndex((chatbox) => chatbox.props.id === id);
      setCurrentChatboxIndex(index);
    };
    
    
    useEffect(() => {

       // Fetch chats from the server
     
  const fetchChats = (token) => {
    fetch('http://localhost:5000/api/chats', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error retrieving chats.');
        }
      })
      .then(data => {
        
        const time = new Date().toLocaleTimeString();

        // Update the chatboxes state with the fetched chats
        const fetchedChatboxes = data.map(chat => (
          <ChatBox
            photo={chat.user.profilePic}
            head={chat.user.displayName}
            time={time}
            message={chat.lastMessage}
            number='new'
            id={chat.id}
            onChatboxClick={handleChatboxClick}
          />
          
        ));
        setchatbox(fetchedChatboxes);
        const chatboxLength = fetchedChatboxes.length; // Calculate the length of fetchedChatboxes
        increase(chatboxLength);
        setIsLoading(false); // Set the loading state to false
      })
      .catch(error => {
        console.error('Error:', error);
        setIsLoading(false); // Set the loading state to false even in case of an error
      });
  };
      
      fetchChats(userdata); // Assuming you have a function called fetchChats to retrieve the chats
    }, [userdata,ChatCount,CurrentChatboxIndex]);
    const handleIncrease = () => {
        increase(ChatCount + 1);
    };

    const handleLogout = () => {
        logout();
    };

    const fetchChatss = (token) => {
      return fetch(`http://localhost:5000/api/Chats/`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
          'accept': 'application/json',
        }
      })
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Error retrieving chats.');
          }
        })
        .catch(error => {
          console.error('Error:', error);
          throw error;
        });
    };
    



    const handleNewChatBox = (name) => {
        const time = new Date().toLocaleTimeString(); // generate a timestamp
        const id = ChatCount + 1;
        fetchChatss(userdata)
        .then(data => {
        // Data is available here
        data.forEach(item => {
        // Process each item in the array
        if(item.user.username == name)
        {

          const newChatbox = <ChatBox photo={item.user.profilePic} head={name} time={time} message={item.lastMessage} number="New" id={item.id} onChatboxClick={handleChatboxClick}/>;
          setchatbox([...chatboxes, newChatbox]);
          handleIncrease();
        }
      });
    })
    .catch(error => {
    // Handle any errors that occurred during the fetch request
    console.error('Error:', error);
  });

        
    };
    if(chatboxes.length != 0)
    {
      
      return (
        <>
            <div className="main-container">
                <LeftContainer chatboxes={chatboxes} add={handleNewChatBox} username={username} token={userdata}/>
                <RightContainer chatbox={chatboxes[CurrentChatboxIndex]} username={username} token={userdata}/>
            </div>
            <button type="button" className="btn btn-danger" onClick={handleLogout}>Logout</button>
        </>
    );
    }
    else{
      return (
        <>
            <div className="main-container">
                <LeftContainer add={handleNewChatBox} username={username} token={userdata}/>
                
            </div>
            <button type="button" className="btn btn-danger" onClick={handleLogout}>Logout</button>
        </>
    );

    }
    
}

export default MainContainer;