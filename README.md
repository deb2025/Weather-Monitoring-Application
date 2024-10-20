# Weather Monitoring Application

## Overview

This is a real-time weather monitoring application that allows users to fetch current weather data for specified cities using the OpenWeatherMap API. The application consists of a Spring Boot backend and a simple HTML/CSS/JavaScript frontend.

## Features

- Fetch current weather data by city name.
- Display temperature, perceived temperature, and weather conditions.
- Responsive design for user-friendly interaction.

## Technologies Used

- **Backend**: Java, Spring Boot, Maven, OpenWeatherMap API.
- **Frontend**: HTML, CSS, JavaScript.

## Getting Started

### Prerequisites

- Java JDK 17 or higher.
- Maven.
- Node.js (if using npm for any additional packages).

### Backend Setup

1. Navigate to the `backend` directory:
    ```bash
    cd backend
    ```

2. Build the project:
    ```bash
    mvn clean package
    ```

3. Run the application:
    ```bash
    java -jar target/weather-monitor-0.0.1-SNAPSHOT.jar --server.port=8080 --WEATHER_API_KEY=your_api_key_here 
    ```

### Frontend Setup

1. Open `index.html` in your web browser or serve it using a local server.

2. Ensure that the fetch URL in `script.js` points to your running backend:
    ```javascript
    fetch(`http://localhost:8080/api/weather/${city}`)
    ```

## Testing the Application

1. Ensure that both frontend and backend are running.
2. Open your browser and navigate to `index.html`.
3. Enter a city name and click "Get Weather" to fetch data.

## Deployment

- The backend can be deployed on any cloud platform supporting Java applications (e.g., AWS, Heroku).
- The frontend can be hosted on platforms like GitHub Pages, Netlify, or Vercel.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
