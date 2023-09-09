import React, { useState } from 'react';
import { Link } from 'react-router-dom';

function LoginItem({handleLogin}) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loginError, setLoginError] = useState('');

  const handleChangeUsername = (e) => {
    setUsername(e.target.value);
  }

  const handleChangePassword = (e) => {
    setPassword(e.target.value);
  }

  const handleOnClick = async () => {
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    };

    const response = await fetch('http://localhost:5000/api/Tokens', requestOptions);
    const data = await response.json(); // Assuming the response is in JSON format
    if(response.ok)
    {
      handleLogin(data,username);
    }
    else{
      setUsername('');
      setPassword('');
      setLoginError(data.error); // Display the error message from backend
    }

  }


  return(
      <div id="page-wrap1" className="webStyle">
        <div className="container">
          <h1 id="first">Login</h1>
          {loginError && <p className="login-error" style={{color: 'red'}}>{loginError}</p>}
          <div className="row">
            <div className="col-md-6">
              <label className="textLog" htmlFor="username">
                Username:
              </label>
              <input
                  className="input form-control"
                  value={username}
                  placeholder="Insert Username"
                  type="text"
                  id="username"
                  name="username"
                  onChange={handleChangeUsername}
                  style={{ width: '200px' }}
              />
              <br />
              <label className="textLog" htmlFor="password">
                Password:
              </label>
              <input
                  className="input form-control"
                  value={password}
                  placeholder="Insert Password"
                  type="password"
                  id="password"
                  name="password"
                  onChange={handleChangePassword}
                  style={{ width: '200px' }}
              />
              <br />
              <br />
              <button type="button" className="btn btn-primary" style={{ marginBottom: '20px'}} onClick={handleOnClick}>
                Login
              </button>
              <br />
              <p>
                <a style={{ whiteSpace: 'nowrap'}}>don't have an account?</a>

                <button className="btn btn-secondary" style={{width: '150px' ,marginTop: '10px'}}>
                  <Link to="/register" className="link-primary">Register here</Link>
                </button>
              </p>
              <br />
            </div>
          </div>
        </div>
      </div>
  );
}

export default LoginItem;
