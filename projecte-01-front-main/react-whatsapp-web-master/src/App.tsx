import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Homepage from "./feature/pages/homepage/Homepage";
import LoginPage from "./feature/pages/loginPage/LoginPage";

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [conversations, setConversations] = useState([]);

  useEffect(() => {
    if (isAuthenticated) {
      const token = localStorage.getItem('token');
      fetch(`${process.env.REACT_APP_API_URL}/api/conversations/my-conversations`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      })
        .then(response => response.json())
        .then(data => setConversations(data))
        .catch(error => console.error('Error:', error));
    }
  }, [isAuthenticated]);

  const handleLogin = () => {
    setIsAuthenticated(true);
  };

  return (
    <Router>
      <Routes>
        <Route
          path="/login"
          element={<LoginPage onLogin={handleLogin} />}
        />
        <Route
          path="/"
          element={
            isAuthenticated ? <Homepage conversations={conversations} /> : <Navigate to="/login" />
          }
        />
      </Routes>
    </Router>
  );
}

export default App;