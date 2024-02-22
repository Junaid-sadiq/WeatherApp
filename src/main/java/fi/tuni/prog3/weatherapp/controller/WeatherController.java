/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.controller;

import fi.tuni.prog3.weatherapp.iAPI;
import fi.tuni.prog3.weatherapp.model.AirPollutionData;
import fi.tuni.prog3.weatherapp.model.LocationInfo;
import fi.tuni.prog3.weatherapp.model.WeatherData;
import fi.tuni.prog3.weatherapp.model.WeatherForecast;
import fi.tuni.prog3.weatherapp.utils.service.HttpURLConnectionHelper;
import fi.tuni.prog3.weatherapp.utils.APIUtils;

/**
 * Controller class implementing the iAPI interface to extract data from the
 * OpenWeatherMap API.
 * @author Junaid Sadiq
 */
public class WeatherController implements iAPI {

    private final HttpURLConnectionHelper apiHelper;
    private final APIUtils apiUtils;

    /**
     * Constructs a WeatherController object initializing
     * HttpURLConnectionHelper and APIUtils helper classes.
     */
    public WeatherController() {
        this.apiHelper = new HttpURLConnectionHelper();
        this.apiUtils = new APIUtils();
    }

    /**
     * Returns location information based on the provided location name.
     * @param location The name of the location to look up.
     * @return LocationInfo object containing location details, or null if not
     * found.
     */
    @Override
    public LocationInfo lookUpLocation(String location) {
        try {
            LocationInfo[] locations = apiHelper.get(apiUtils.getLocationCoordinatesUrl(location), LocationInfo[].class);
            if (locations != null && locations.length > 0) {
                return locations[0];
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Returns the current weather data for the provided latitude and longitude.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return WeatherData object containing current weather information, or
     * null if not found.
     */
    @Override
    public WeatherData getCurrentWeather(double lat, double lon) {
        try {
            WeatherData currentWeather = apiHelper.get(apiUtils.getCurrentWeatherUrl(lat, lon), WeatherData.class);
            return currentWeather;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Returns the weather forecast data for a provided latitude and longitude.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return WeatherForecast object containing weather forecast information.
     */
    @Override
    public WeatherForecast getForecast(double lat, double lon) {
        try {
            WeatherForecast weatherForecast = apiHelper.get(apiUtils.getForecastUrl(lat, lon), WeatherForecast.class);
            return weatherForecast;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Returns the current air pollution data for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return AirPollutionData containing details of the current air pollution
     * data.
     */
    @Override
    public AirPollutionData getCurrentAirPollutionData(double lat, double lon) {
        try {
            AirPollutionData airPollutionData = apiHelper.get(apiUtils.getAirPollutionUrl(lat, lon), AirPollutionData.class);
            return airPollutionData;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
    }

}
