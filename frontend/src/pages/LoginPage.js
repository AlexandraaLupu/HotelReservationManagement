import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

// register and login page

const LoginPage= ({ setUser }) => {
  const [isLogin, setIsLogin] = useState(true);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [id, setId] = useState(-1);
  const [email, setEmail] = useState('');
  const navigate = useNavigate();

  const handleToggle = () => {
    setIsLogin(!isLogin);
  };

  const handleAuth = async () => {
    const url = isLogin ? 'http://localhost:8080/users/login' : 'http://localhost:8080/users/register';
    const payload = isLogin ? { id, username, password } : { username, password };

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        throw new Error('Failed to authenticate');
      }

      const user = await response.json();
      setUser(user);

      localStorage.setItem('user', JSON.stringify(user));

      if (isLogin) {
        navigateToHotels();
      } else {
        setIsLogin(true);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const navigateToHotels = () => {
    navigate('/hotels');
  };

  return (
    <div>
      <h1>{isLogin ? 'Login' : 'Register'}</h1>
      <div>
        <button onClick={handleToggle}>
          {isLogin ? 'Switch to Register' : 'Switch to Login'}
        </button>
      </div>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleAuth}>{isLogin ? 'Login' : 'Register'}</button>
    </div>
  );
};

export default LoginPage;
