document.getElementById('fetchWeatherBtn').addEventListener('click', function() {
    const city = document.getElementById('cityInput').value;

    if (!city) {
        alert("Please enter a city name.");
        return;
    }

    fetch(`http://localhost:8001/api/weather/${city}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            const resultDiv = document.getElementById('weatherResult');
            resultDiv.innerHTML = `
                <h2>Weather in ${data.city}</h2>
                <p>Temperature: ${data.temperature.toFixed(2)} °C</p>
                <p>Feels Like: ${data.feelsLike.toFixed(2)} °C</p>
                <p>Condition: ${data.condition}</p>
                <p>Timestamp: ${new Date(data.timestamp).toLocaleString()}</p>
            `;
        })
        .catch(error => {
            const resultDiv = document.getElementById('weatherResult');
            resultDiv.innerHTML = `<p>Error fetching weather data: ${error.message}</p>`;
        });
});