/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A helper class to access OpenWeather API URLs and the API key
 * @author Junaid Sadiq
 */
public class APIUtils {

    /**
     * Base URL of the OpenWeather API.
     */
    private static final String BASE_URL = "https://api.openweathermap.org";
    /**
     * File path of file containing the API key.
     */
    private static final String CONFIG_FILE_PATH = "config.properties";

    /**
     * URL to look up a location and get the coordinates.
     */
    private static final String LOCATION_COORDINATES_URL = "%s/geo/1.0/direct?q=%s&limit=1&appid=%s";
    /**
     * URL to get current weather details.
     */
    private static final String CURRENT_WEATHER_URL = "%s/data/2.5/weather?lat=%s&lon=%s&appid=%s";
    /**
     * URL to get hourly forecast.
     */
    private static final String HOURLY_FORECASST_URL = "%s/data/2.5/forecast?lat=%s&lon=%s&appid=%s";
    /**
     * URL to get air pollution details.
     */
    private static final String AIR_POLLUTION_URL = "%s/data/2.5/air_pollution?lat=%s&lon=%s&appid=%s";

    /**
     * Returns the API key.
     * @return the API key stored in the properties file.
     */
    public String getApiKey() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
            return properties.getProperty("api.key");
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Returns the string URL to get lookup a location.
     * @param location The location name.
     * @return the string URL to lookup a location.
     */
    public String getLocationCoordinatesUrl(String location) {
        return String.format(LOCATION_COORDINATES_URL, BASE_URL, location, getApiKey());
    }

    /**
     * Returns the string URL to get the current weather.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return the String URL to get the current weather.
     */
    public String getCurrentWeatherUrl(double lat, double lon) {
        return String.format(CURRENT_WEATHER_URL, BASE_URL, String.format("%.2f", lat).replace(',', '.'), String.format("%.2f", lon).replace(',', '.'), getApiKey());
    }

    /**
     * Returns the string URL to get the weather forecast.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return the string URL to get the weather forecast.
     */
    public String getForecastUrl(double lat, double lon) {
        return String.format(HOURLY_FORECASST_URL, BASE_URL, String.format("%.2f", lat).replace(',', '.'), String.format("%.2f", lon).replace(',', '.'), getApiKey());
    }

    /**
     * Returns the string URL to get the air pollution details.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return the string URL to get the air pollution details.
     */
    public String getAirPollutionUrl(double lat, double lon) {
        return String.format(AIR_POLLUTION_URL, BASE_URL, String.format("%.2f", lat).replace(',', '.'), String.format("%.2f", lon).replace(',', '.'), getApiKey());
    }
}
