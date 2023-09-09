function FriendMessage(props)
{


    return (

      <div className="message-box friend-message">
      <p >
        {props.text}<br/>
        <span>
        {props.time}
        </span>
        </p>
      </div>
    );


}
export default FriendMessage;