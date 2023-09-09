
function ChatDetails({textHead,textTime,textMessage,textNumber})
{
  if(textNumber == "0")
  {
    return(
      <div className="chat-details">
          <div className="text-head">
            <h4>{textHead}</h4>
            <p className="time unread">{textTime}</p>
          </div>
          <div className="text-message">
            <p>{textMessage}</p>
            
          </div>
        </div>
    );
  }
    

    return(
        <div className="chat-details">
            <div className="text-head">
              <h4>{textHead}</h4>
              <p className="time unread">{textTime}</p>
            </div>
            <div className="text-message">
              <p>{textMessage}</p>
              <b>{textNumber}</b>
            </div>
          </div>
    );
    
}
export default ChatDetails;