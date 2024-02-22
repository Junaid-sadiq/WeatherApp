package fi.tuni.prog3.weatherapp;

import fi.tuni.prog3.weatherapp.model.AirPollutionData;
import fi.tuni.prog3.weatherapp.model.LocationInfo;
import fi.tuni.prog3.weatherapp.model.WeatherData;
import fi.tuni.prog3.weatherapp.model.WeatherForecast;

/**
 * Interface for extracting data from the OpenWeatherMap API.
 * @author Junaid Sadiq
 */
public interface iAPI {

    /**
     * Returns coordinates for a location.
     * @param location Name of the location for which coordinates should be
     * fetched.
     * @return LocationInfo object containing location details.
     */
    public LocationInfo lookUpLocation(String location);

    /**
     * Returns the current weather for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return WeatherData object containing current weather information.
     */
    public WeatherData getCurrentWeather(double lat, double lon);

    /**
     * Returns a forecast for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return WeatherForecast object containing details for 5 days with data
     * every 3 hours.
     */
    public WeatherForecast getForecast(double lat, double lon);

    /**
     * Returns the current air pollution data for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return AirPollutionData object containing details of the current air 
     * pollution data
     */
    public AirPollutionData getCurrentAirPollutionData(double lat, double lon);
    
}
