import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

// here i have the list of all hotels
// the user can input the radius in km and the list will update accordingly
// there is a button for navigating to the reservations of the user
// when clicking a hotel the user will be redirected to the page HotelRoomsPage where the available room for the hotel are

const MainPage = () => {
  const [hotels, setHotels] = useState([]);
  const [radiusKm, setRadiusKm] = useState(0);
  const [latitude, setLatitude] = useState(0);
  const [longitude, setLongitude] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    fetchHotels();
  }, [radiusKm]);

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(success, error);
  } else {
    console.log("Geolocation not supported");
  }
  
  function success(position) {
    setLatitude(position.coords.latitude);
    console.log(latitude);
    setLongitude(position.coords.longitude);
    console.log(longitude);
    console.log(`Latitude: ${latitude}, Longitude: ${longitude}`);
  }
  
  function error() {
    console.log("Unable to retrieve your location");
  }

  const fetchHotels = async () => {
    try {
      const response = await fetch(`http://localhost:8080/hotels?latitude=${latitude}&longitude=${longitude}&radius=${radiusKm  || 0}`);
      if (!response.ok) {
        throw new Error('Failed to fetch hotels');
      }
      const data = await response.json();
      setHotels(data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleRadiusChange = (event) => {
    setRadiusKm(event.target.value);
  };

  const handleHotelClick = (hotelId) => {
    navigate(`/rooms/${hotelId}`);
  };

  const handleSeeReservations = () => {
    navigate('/reservations');
  };

  return (
    <div>
      <h1>Hotels</h1>
      <div>
        <label htmlFor="radius">Radius (km):</label>
        <input
          type="number"
          id="radius"
          name="radius"
          value={radiusKm}
          onChange={handleRadiusChange}
        />
      </div>
      <ul>
        {hotels.map((hotel) => (
          <li key={hotel.id} onClick={() => handleHotelClick(hotel.id)}>
            <h2>{hotel.name}</h2>
            <p>Latitude: {hotel.latitude}</p>
            <p>Longitude: {hotel.longitude}</p>
          </li>
        ))}
      </ul>
      <button onClick={handleSeeReservations}>See Reservations</button>

    </div>
  );
};

export default MainPage;
