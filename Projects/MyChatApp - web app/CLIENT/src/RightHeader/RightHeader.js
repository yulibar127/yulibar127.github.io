import React, { useEffect } from 'react';
import NavHeader from '../NavHeader/NavHeader';
import ImageText from '../ImageText/ImageText';

function RightHeader({ chatbox }) {
  useEffect(() => {
    // This effect will run whenever the chatbox prop changes
  }, [chatbox]);

  if (chatbox) {
    return (
      <div className="header">
        <ImageText photo={chatbox.props.photo} name={chatbox.props.head} status="Online" />
        <NavHeader />
      </div>
    );
  } else {
    return (
      <div className="header">
        <NavHeader />
      </div>
    );
  }
}

export default RightHeader;
