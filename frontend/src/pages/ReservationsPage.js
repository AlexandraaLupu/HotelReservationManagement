import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

// the reservations of the user
// the user can cancel a reservation

const ReservationsPage = () => {
  const [reservations, setReservations] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const userId = JSON.parse(localStorage.getItem('user')).id;
    fetchReservations(userId);
  }, []);

  const fetchReservations = async (userId) => {
    try {
      const response = await fetch(`http://localhost:8080/reservations/${userId}`);
      if (!response.ok) {
        throw new Error('Failed to fetch reservations');
      }
      const data = await response.json();
      setReservations(data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleNavigateBack = () => {
    navigate('/hotels');
  };

  const handleCancelReservation = async (reservationId) => {
    try {
      const response = await fetch(`http://localhost:8080/reservations/cancel/${reservationId}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to cancel reservation');
      }
      fetchReservations(JSON.parse(localStorage.getItem('user')).id);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>Reservations</h1>
      <button onClick={handleNavigateBack}>Back to Main</button>
      <ul>
        {reservations.map((reservation) => (
          <li key={reservation.id}>
            <p>Hotel: {reservation.room.id.hotelId}</p>
            <p>Room: {reservation.room.id.roomNumber}</p>
            <p>Check-in: {reservation.checkIn}</p>
            <button onClick={() => handleCancelReservation(reservation.id)}>Cancel</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ReservationsPage;
