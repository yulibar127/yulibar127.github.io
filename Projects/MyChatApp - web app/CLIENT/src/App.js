import './App.css';
import MainContainer from './MainContainer/MainContainer';
import React, { useState } from 'react';
import RegisterItem from './Register/RegisterItem';
import { BrowserRouter as Router, Route, Navigate, Routes } from 'react-router-dom';
import LoginItem from './Login/LoginItem';

function App() {
  const [userData, setUserData] = useState(null);
  const [loginError, setLoginError] = useState('');
  const [loggedUser, setLoggedUser] = useState('');


  const handleLogin = (userData,username) => {
    
    setUserData(userData);
    setLoggedUser(username);
    setLoginError('');
  };

  const handleLogout = () => {
    setUserData(null);
  };

  return (
    <Router>
      <Routes>
        <Route path="/login" element={!userData ? <LoginItem handleLogin={handleLogin} loginError={loginError}/> : <Navigate replace to="/main" />} />
        <Route path="/register" element={!userData ? <RegisterItem handleLogin={handleLogin}/> : <Navigate replace to="/main" />} />
        <Route path="/main" element={userData ? <MainContainer userData={userData} username={loggedUser} logout={handleLogout}/> : <Navigate replace to="/login" />} />
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </Router>
  )
}

export default App;