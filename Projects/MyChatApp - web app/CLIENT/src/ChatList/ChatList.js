import ChatBox from "../ChatBox/ChatBox";

function ChatList({ chatboxes }) {
  if (chatboxes && chatboxes.length !== 0) {
    const chatboxElements = chatboxes.map((chatbox, index) => {
      
      const { head, id, message, onChatboxClick, photo } = chatbox.props;
      const time = message ? message.created : new Date().toISOString();
      const content = message ? message.content : "";

      return (
        <ChatBox
          key={index}
          head={head}
          id={id}
          time={time}
          message={content}
          number="new"
          onChatboxClick={onChatboxClick}
          photo={photo}
        />
      );
    });

    return <div className="chat-list">{chatboxElements}</div>;
  } else {
    return <div className="chat-list"></div>;
  }
}

export default ChatList;
