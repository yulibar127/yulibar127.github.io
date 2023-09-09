import React, { useState } from 'react';

function AddContact({ add, token }) {
  const [username, setUsername] = useState('');

  const handleChange = (e) => {
    setUsername(e.target.value);
  };

  const handleClick = () => {
    if (username !== '') {
      fetch('http://localhost:5000/api/Chats', {
        method: 'POST',
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username })
      })
        .then(response => {
          if (response.ok) {
            return response.json();
          } else if (response.status === 401) {
            throw new Error('Unauthorized');
          } else {
            alert('Unfound User.');
            throw new Error('Unfound User.');
          }
        })
        .then(data => {

          add(username);
          setUsername('');
        })
        .catch(error => {
          console.error('Error:', error);
          if (error.message === 'Unauthorized') {
            alert('Unauthorized');
          }
          // Handle any other errors that occur during the request
        });
    }
  };
  
  
  
  const buttonStyles = {
    border: 'none',
  };

  return (
    <>
      <button id="addContact" className="nav-icons" data-bs-toggle="modal" data-bs-target="#staticBackdrop" style={buttonStyles}>
        <i className="fa-solid fa-handshake-simple fa-lg" data-toggle="tooltip" data-placement="top" title="Add Contact"></i>
      </button>
      <div className="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" style={{ textAlign: 'center', color: 'white' }} id="staticBackdropLabel">Add New Contact</h1>
              <button type="button" className="btn-close btn-primary" data-bs-dismiss="modal" aria-label="Close" data-bs-toggle="tooltip" title="Tooltip"></button>
            </div>
            <div className="modal-body">
              <input type="text" id="userName" placeholder="Enter new username" onChange={handleChange} />
            </div>
            <div className="modal-footer">
              <button type="button" className="btn btn-danger" data-bs-dismiss="modal">Close</button>
              <button type="submit" className="btn btn-success" data-bs-dismiss="modal" onClick={handleClick}>Add</button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AddContact;
