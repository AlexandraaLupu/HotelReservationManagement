import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

// a page for the available rooms based on hotel
// the user can book one or more rooms
// the user can leave here a feedback for the hotel

const HotelRoomsPage = () => {
  const { hotelId } = useParams();
  const { roomId, setRoomId } = useParams();

  const [rooms, setRooms] = useState([]);
  const [feedback, setFeedback] = useState('');
  const userId = JSON.parse(localStorage.getItem('user')).id;

  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const response = await fetch(`http://localhost:8080/rooms/${hotelId}`);
        if (!response.ok) {
          throw new Error('Failed to fetch rooms');
        }
        const data = await response.json();
        setRooms(data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchRooms();
  }, [hotelId]);

  const handleBookRoom = async (roomId) => {
    const reservationData = {
      user: { id: userId },
      room: { hotelId: parseInt(hotelId), roomNumber: roomId },
    };
    try {
      const response = await fetch(`http://localhost:8080/reservations/book`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ reservationData }),
      });
      if (!response.ok) {
        throw new Error('Failed to book room');
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleLeaveFeedback = async () => {
    try {
      const response = await fetch(`http://localhost:8080/feedbacks/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          hotel: { id: parseInt(hotelId) },
          user: { id: userId },
          feedback: feedback,
        }),
      });
      if (!response.ok) {
        throw new Error('Failed to leave feedback');
      }
      setFeedback('');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>Available Rooms</h1>
      <ul>
        {rooms.map((room) => (
          <li key={room.id}>
            <h2>Room Number: {room.id.roomNumber}</h2>
            <p>Type: {room.type}</p>
            <p>Price: ${room.price}</p>
            <p>Available: {room.available ? 'Yes' : 'No'}</p>
            {room.available && <button onClick={() => handleBookRoom(room.id.roomNumber)}>Book</button>}
          </li>
        ))}
      </ul>
      <div>
        <h2>Leave Feedback</h2>
        <input type="text" value={feedback} onChange={(e) => setFeedback(e.target.value)} />
        <button onClick={handleLeaveFeedback}>Submit Feedback</button>
      </div>
    </div>
  );
};

export default HotelRoomsPage;
