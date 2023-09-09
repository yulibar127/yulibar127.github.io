import React, { useEffect, useState } from 'react';
import UserImage from '../UserImage/UserImage';
import AddContact from '../AddContact/AddContact';

function LeftHeader({ add, username, token }) {
  const [pic, setPic] = useState('');
  const fetchUser = (username) => {
    if (username !== '') {
      fetch(`http://localhost:5000/api/Users/${username}`, {
        method: 'GET',
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Unfound User.');
          }
        })
        .then(data => {
          
          setPic(data.profilePic);
        })
        .catch(error => {
          console.error('Error:', error);
        });
    }
  };

  useEffect(() => {
    fetchUser(username);
  }, [username]);

  return (
    <div className="header">
      <UserImage pic={pic} />
      <h5>{username}</h5>
      <AddContact add={add} token={token} />
    </div>
  );
}

export default LeftHeader;
