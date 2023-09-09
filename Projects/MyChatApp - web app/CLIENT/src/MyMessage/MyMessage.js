function MyMessage(props)
{
    return (

        <div className="message-box my-message">
          <p>{props.text}<br /><span>{props.time}</span></p>
        </div>
      );


}
export default MyMessage;