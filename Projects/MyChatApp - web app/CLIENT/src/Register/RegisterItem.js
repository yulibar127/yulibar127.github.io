import React, {useEffect, useState} from 'react';
import {useNavigate , Link } from 'react-router-dom';
import users from "../Users";
function RegisterItem()
{
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [displayName, setDisplayName] = useState('');
  const [profilePic, setPicture] = useState(null);
  const [isFormValid, setIsFormValid] = useState(false);
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState(null);



  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleConfirmPasswordChange = (event) => {
    setConfirmPassword(event.target.value);
  };

  const handleDisplayNameChange = (event) => {
    setDisplayName(event.target.value);
  };

  const handlePictureChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = async (event) => {
        const base64String = event.target.result;
        setPicture(base64String);
       // Call handleRegister once the picture has loaded.
      };
      reader.readAsDataURL(file);
    } else {
      setPicture(null);
    }
  };
  

  useEffect(() => {
    const usernameValid = username.length > 0 && username.length <= 20 && /^[a-zA-Z0-9_]+$/.test(username);
    const passwordValid = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/.test(password) && password.length >= 8 && password.length <= 20;
    const confirmPasswordValid = confirmPassword === password;
    const displayNameValid = displayName.length > 0 && displayName.length <= 20 && /^[a-zA-Z0-9_]+$/.test(displayName);

    setIsFormValid(usernameValid && passwordValid && confirmPasswordValid && displayNameValid);
  }, [username, password, confirmPassword, displayName]);


  //the updated register code starting from here
  const handleRegister = () => {
    if (isFormValid) {
      const newUser = {
        username: username,
        password: password,
        displayName: displayName,
        profilePic: profilePic
      };

      fetch('http://localhost:5000/api/Users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newUser),
      })
          .then(response => {
            if(response.ok){
              navigate('/login');
              return response.text().then(text => text.length ? JSON.parse(text) : {})
            } else if(response.status === 409){
              throw new Error('Username already exists.');
            } else {
              throw new Error('Network response was not ok');
            }
          })
          .then(data => {
            navigate('/login');
          })
          .catch((error) => {
            setErrorMessage(error.message);
          });
    }
  }



  return (

  
    <div id="page-wrap1" className="webStyle">
    <div className="container">
          <h1 id="first">Register</h1>
          <form style={{ marginLeft: '60px' }} className="row needs-validation" noValidate>

            <div className="col-md-12">
              <label htmlFor="validationServer01" className="form-label">
                Username
              </label>
              <input
                  style={{ width: '600px' }}
                  type="text"
                  placeholder="Insert Username"
                  className="form-control"
                  id="validationServer01"
                  value={username}
                  onChange={handleUsernameChange}
                  required
              />
              <span id="usernameHelpInline" className="form-text">
            insert the username.
          </span>
              {users.some(user => user.username === username) &&
                  (<div style={{ color: 'red' }} className="is-invalid">Username is already taken, try again</div>)}
              {(username.length > 20  || !/^[a-zA-Z0-9_]+$/.test(username)) && ( username.length > 0) &&
                  (<div style={{ color: 'red' }} className="is-invalid">Username is invalid</div> )
              }
              {!(users.some(user => user.username === username)) && username.length > 0 && username.length <= 20 && /^[a-zA-Z0-9_]+$/.test(username) && (
                  <div style={{ color: 'green' }} className="is-valid">looks good!</div>)
              }

            </div>

            <div className="col-md-12">
              <label htmlFor="validationServer02" className="form-label">Password</label>
              <input style={{ width: '600px' }}
                     type="password"
                     placeholder="Insert Password"
                     className="form-control"
                     id="validationServer02"
                     value={password}
                     onChange={handlePasswordChange}
                     required />
              <span id="passwordHelpInline" className="form-text">
              Must be 8-20 characters long, and contain one digit and one letter.
            </span>
              {/* Render validation message based on the password input */}
              { /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/.test(password) &&
                  password.length >= 8 &&
                  password.length <= 20 && (
                      <div style={{ color: 'green' }}
                           className="is-valid">looks good!</div>)}
              {!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/.test(password) && password.length > 0 && (
                  <div style={{ color: 'red' }} className="is-invalid">Password is invalid</div> )
              }
            </div>
            <div className="col-md-12">
              <label htmlFor="validationServer03" className="form-label">Confirm Password</label>
              <input style={{ width: '600px' }}
                     type="password"
                     placeholder="Confirm Password"
                     className="form-control"
                     id="validationServer03"
                     value={confirmPassword}
                     onChange={handleConfirmPasswordChange}
                     required />
              <span id="confirmPasswordHelpInline" className="form-text">
              Insert the password again.
            </span>
              { confirmPassword !== password && confirmPassword.length > 0 && (
                  <div style={{ color: 'red' }} className="is-invalid">Passwords do not match</div> )
              }
              { confirmPassword === password && confirmPassword.length > 0 && (
                  <div style={{ color: 'green' }} className="is-valid">Passwords match!</div> )
              }
            </div>

            <div className="col-md-12">
              <label htmlFor="validationServer04" className="form-label">Display Name</label>
              <input style={{ width: '600px' }}
                     type="text"
                     placeholder="Insert Display Name"
                     className="form-control"
                     id="validationServer04"
                     value={displayName}
                     onChange={handleDisplayNameChange}
                     required />
              <span id="displayNameHelpInline" className="form-text">
              Insert the display name.
            </span>
              {displayName.length > 20 && displayName.length > 0 && !/^[a-zA-Z0-9_]+$/.test(displayName) && (
                  <div style={{ color: 'red' }} className="is-invalid">Display name is invalid</div> )
              }
              {displayName.length > 0 && displayName.length <= 20 && /^[a-zA-Z0-9_]+$/.test(displayName) && (
                  <div style={{ color: 'green' }} className="is-valid">looks good!</div>)
              }

            </div>

            <div className="col-md-12">
              <label className="form-label">Picture</label>
              <br />
              <input style={{ width: '600px', border: '1px solid black' }}
                     type="file"
                     placeholder="Insert Picture"
                     className="form-control"
                     id="picture"
                     name="picture"
                     onChange={handlePictureChange}
                     required />
              <span id="pictureHelpInline" className="form-text">
              Insert picture.
            </span>
              <br />
              {profilePic && <img id="picture-preview" src={profilePic} alt="" style={{ maxWidth: '200px', maxHeight: '200px' }}/>}
            </div>

            <div className="col-12">
              <br />
              {errorMessage && <div style={{ color: 'red' }}>{errorMessage}</div>}
              <button type="button" className="btn btn-primary" onClick={handleRegister} disabled={!isFormValid}>Register</button>
              <br/>
              <span>{' '}Already registered?{' '}</span>
              <button className="btn btn-secondary" >
              <Link to="/login" className="link-primary">
              login
              </Link>
      </button>
    </div>
    </form>
  </div>
  </div>

    );
    
}
export default RegisterItem;