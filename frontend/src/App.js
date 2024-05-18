import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import React, { useEffect, useState } from 'react';

import LoginPage from './pages/LoginPage';
import MainPage from './pages/MainPage';
import HotelRoomsPage from './pages/HotelRoomsPage';
import ReservationsPage from './pages/ReservationsPage';

const App = () => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage setUser={setUser} />} />
        <Route path="/hotels" element={user ? <MainPage /> : <Navigate to="/login" />} />
        <Route path="/" element={<Navigate to={user ? "/hotels" : "/login"} />} />
        <Route path="/rooms/:hotelId" element={user ? <HotelRoomsPage /> : <Navigate to="/login" />} />
        <Route path="/reservations" element={user ? <ReservationsPage /> : <Navigate to="/login" />} />
      </Routes>
    </Router>
    
  );
}

export default App;
