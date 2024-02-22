/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.model;

import java.util.List;

/**
 * Model class to represent weather forecast details.
 * @author Junaid Sadiq
 */
public class WeatherForecast {

    /**
     * List of weather details.
     */
    private List<WeatherInfo> list;
    /**
     * City details.
     */
    private CityInfo city;

    /**
     * Returns the weather details.
     * @return the weather details.
     */
    public List<WeatherInfo> getList() {
        return list;
    }

    /**
     * Returns city details.
     * @return city details.
     */
    public CityInfo getCity() {
        return city;
    }

    /**
     * Inner class representing weather details.
     */
    public static class WeatherInfo {

        /**
         * Time of data forecast, unix, UTC.
         */
        private long dt;
        /**
         * Main weather details.
         */
        private MainInfo main;
        /**
         * Weather forecast list.
         */
        private List<Weather> weather;
        /**
         * Cloud details.
         */
        private Clouds clouds;
        /**
         * Wind details.
         */
        private Wind wind;
        /**
         * Visibility, meter.
         */
        private int visibility;
        /**
         * Probability of precipitation.
         */
        private double pop;
        /**
         * Rain details.
         */
        private Rain rain;
        /**
         * Part of the day (n - night, d - day).
         */
        private Sys sys;
        /**
         * Time of data forecast.
         */
        private String dt_txt;

        /**
         * Returns the time of data forecast.
         * @return the time of data forecast.
         */
        public long getDt() {
            return dt;
        }

        /**
         * Returns the main weather details.
         * @return the main weather details.
         */
        public MainInfo getMain() {
            return main;
        }

        /**
         * Return weather forecast list.
         * @return the weather forecast list.
         */
        public List<Weather> getWeather() {
            return weather;
        }

        /**
         * Return the cloud details.
         * @return the cloud details.
         */
        public Clouds getClouds() {
            return clouds;
        }

        /**
         * Return the wind details.
         * @return the wind details.
         */
        public Wind getWind() {
            return wind;
        }

        /**
         * Returns the visibility.
         * @return the visibility.
         */
        public int getVisibility() {
            return visibility;
        }

        /**
         * Returns the probability of precipitation.
         * @return the probability of precipitation.
         */
        public double getPop() {
            return pop;
        }

        /**
         * Return the rain details.
         * @return the rain details.
         */
        public Rain getRain() {
            return rain;
        }

        /**
         * Returns the part of the day.
         * @return the part of the day.
         */
        public Sys getSys() {
            return sys;
        }

        /**
         * Returns the time of data forecast.
         * @return the time of data forecast.
         */
        public String getDt_txt() {
            return dt_txt;
        }

    }

    /**
     * Inner class representing main weather details.
     */
    public static class MainInfo {

        /**
         * Temperature: Unit Default: Kelvin, Metric: Celsius, Imperial:
         * Fahrenheit.
         */
        private double temp;
        /**
         * Human perception of weather.
         */
        private double feels_like;
        /**
         * Minimum current temperature.
         */
        private double temp_min;
        /**
         * Maximum current temperature.
         */
        private double temp_max;
        /**
         * Atmospheric pressure on the sea level, hPa.
         */
        private int pressure;
        /**
         * Humidity percentage.
         */
        private int humidity;
        /**
         * Atmospheric pressure on the sea level, hPa.
         */
        private int sea_level;
        /**
         * Atmospheric pressure on the ground level, hPa.
         */
        private int grnd_level;

        /**
         * Returns the temperature.
         * @return the temperature.
         */
        public double getTemp() {
            return temp;
        }

        /**
         * Returns the human perception of weather.
         * @return the human perception of weather.
         */
        public double getFeels_like() {
            return feels_like;
        }

        /**
         * Returns the minimum current temperature.
         * @return the minimum current temperature.
         */
        public double getTemp_min() {
            return temp_min;
        }

        /**
         * Returns the maximum current temperature.
         * @return the maximum current temperature.
         */
        public double getTemp_max() {
            return temp_max;
        }

        /**
         * Returns the atmospheric pressure on the sea level.
         * @return the atmospheric pressure.
         */
        public int getPressure() {
            return pressure;
        }

        /**
         * Returns the humidity percentage.
         * @return the humidity %.
         */
        public int getHumidity() {
            return humidity;
        }

        /**
         * Returns the atmospheric pressure on the sea level.
         * @return the atmospheric pressure on the sea level.
         */
        public int getSea_level() {
            return sea_level;
        }

        /**
         * Returns the atmospheric pressure on the ground level.
         * @return the atmospheric pressure on the ground level.
         */
        public int getGrnd_level() {
            return grnd_level;
        }

    }

    public static class Weather {

        /**
         * Weather condition id.
         */
        private int id;
        /**
         * Group of weather parameters (Rain, Snow, Clouds etc.).
         */
        private String main;
        /**
         * Weather condition within the group.
         */
        private String description;
        /**
         * Weather icon id.
         */
        private String icon;

        /**
         * Returns the weather condition id.
         * @return the weather condition id.
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the main weather group.
         * @return the main weather group.
         */
        public String getMain() {
            return main;
        }

        /**
         * Returns the weather condition within the group.
         * @return the weather condition within the group.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Returns the weather icon id.
         * @return the weather icon id.
         */
        public String getIcon() {
            return icon;
        }

    }

    /**
     * Inner class representing current cloud details.
     */
    public static class Clouds {

        /**
         * Cloudiness, %.
         */
        private int all;

        /**
         * Returns the cloudiness.
         * @return the cloudiness.
         */
        public int getAll() {
            return all;
        }

    }

    /**
     * Inner class representing current wind details.
     */
    public static class Wind {

        /**
         * Wind speed.
         */
        private double speed;
        /**
         * Wind direction.
         */
        private int deg;
        /**
         * Wind gust.
         */
        private double gust;

        /**
         * Returns the wind speed.
         * @return the wind speed.
         */
        public double getSpeed() {
            return speed;
        }

        /**
         * Returns the wind direction.
         * @return the wind direction.
         */
        public int getDeg() {
            return deg;
        }

        /**
         * Returns the wind gust.
         * @return the wind gust.
         */
        public double getGust() {
            return gust;
        }

    }

    /**
     * Inner class representing current rain details.
     */
    public static class Rain {

        /**
         * Rain volume for the last 1 hour, mm.
         */
        private double h1;

        /**
         * Returns the rain volume.
         * @return the rain volume.
         */
        public double getH1() {
            return h1;
        }

    }

    /**
     * Inner class representing to get the part of the day.
     */
    public static class Sys {

        /**
         * Part of the day (n - night, d - day).
         */
        private String pod;

        /**
         * Returns the part of the day.
         * @return the part of the day.
         */
        public String getPod() {
            return pod;
        }

    }

    /**
     * Inner class representing city details.
     */
    public static class CityInfo {

        /**
         * City ID.
         */
        private int id;
        /**
         * City name.
         */
        private String name;
        /**
         * Coordinate details.
         */
        private Coord coord;
        /**
         * Country code.
         */
        private String country;
        /**
         * City population
         */
        private int population;
        /**
         * Shift in seconds from UTC
         */
        private int timezone;
        /**
         * Sunrise time, Unix, UTC
         */
        private long sunrise;
        /**
         * Sunset time, Unix, UTC
         */
        private long sunset;

        /**
         * Return the city id.
         * @return the city id.
         */
        public int getId() {
            return id;
        }

        /**
         * Return the city name.
         * @return the city name.
         */
        public String getName() {
            return name;
        }

        /**
         * Returns coordinate details.
         * @return the coordinate details.
         */
        public Coord getCoord() {
            return coord;
        }

        /**
         * Returns the country code.
         * @return the country code.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Returns the population.
         * @return the population.
         */
        public int getPopulation() {
            return population;
        }

        /**
         * Returns the timezone.
         * @return the timezone.
         */
        public int getTimezone() {
            return timezone;
        }

        /**
         * Returns the sunrise time
         * @return the sunrise time
         */
        public long getSunrise() {
            return sunrise;
        }

        /**
         * Returns the sunset time
         * @return the sunset time
         */
        public long getSunset() {
            return sunset;
        }

    }

    /**
     * Inner class representing location coordinates.
     */
    public static class Coord {

        /**
         * Latitude coordinate.
         */
        private double lat;
        /**
         * Longitude coordinate.
         */
        private double lon;

        /**
         * Returns the latitude of the location.
         * @return the latitude of the location.
         */
        public double getLat() {
            return lat;
        }

        /**
         * Returns the longitude of the location.
         * @return the longitude of the location.
         */
        public double getLon() {
            return lon;
        }

    }

}
