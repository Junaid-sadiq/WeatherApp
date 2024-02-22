/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.model;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Model class to represent current weather details.
 * @author Junaid Sadiq
 */
public class WeatherData {

    /**
     * Coordinate details.
     */
    private Coord coord;
    /**
     * Weather condition details list.
     */
    private Weather[] weather;
    /**
     * Main current weather details.
     */
    private Main main;
    /**
     * Visibility, meter.
     */
    private int visibility;
    /**
     * Wind details.
     */
    private Wind wind;
    /**
     * Rain details.
     */
    private Rain rain;
    /**
     * Snow details.
     */
    private Snow snow;
    /**
     * Cloud details.
     */
    private Clouds clouds;
    /**
     * Time of data calculation, unix, UTC.
     */
    private long dt;
    /**
     * Common details.
     */
    private Sys sys;
    /**
     * Shift in seconds from UTC.
     */
    private int timezone;
    /**
     * City ID.
     */
    private int id;
    /**
     * City name.
     */
    private String name;

    /**
     * Inner class representing location coordinates.
     */
    public static class Coord {

        /**
         * Longitude coordinate.
         */
        private double lon;
        /**
         * Latitude coordinate.
         */
        private double lat;

        /**
         * Returns the longitude of the location.
         * @return the longitude of the location.
         */
        public double getLon() {
            return lon;
        }

        /**
         * Returns the latitude of the location.
         * @return the latitude of the location.
         */
        public double getLat() {
            return lat;
        }

    }

    /**
     * Inner class representing weather condition details.
     */
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
     * Inner class representing main current weather details.
     */
    public static class Main {

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
     * Inner class representing current snow details.
     */
    public static class Snow {

        /**
         * Snow volume for the last 1 hour, mm.
         */
        private double h1;

        /**
         * Returns the snow volume.
         * @return the snow volume.
         */
        public double getH1() {
            return h1;
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
     * Inner class representing more weather details.
     */
    public static class Sys {

        /**
         * Country code.
         */
        private String country;
        /**
         * Sunrise time.
         */
        private long sunrise;
        /**
         * Sunset time.
         */
        private long sunset;

        /**
         * Returns the country code.
         * @return the country code.
         */
        public String getCountry() {
            return country;
        }

        /**
         * Returns the sunrise time.
         * @return the sunrise time.
         */
        public long getSunrise() {
            return sunrise;
        }

        /**
         * Returns the sunset time.
         * @return the sunset time.
         */
        public long getSunset() {
            return sunset;
        }

    }

    /**
     * Returns coordinate details.
     * @return the coordinate details.
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * Returns weather condition details.
     * @return the weather condition details.
     */
    public Weather[] getWeather() {
        return weather;
    }

    /**
     * Returns the main current weather details.
     * @return the main current weather details.
     */
    public Main getMain() {
        return main;
    }

    /**
     * Returns the visibility.
     * @return the visibility.
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * Return the wind details.
     * @return the wind details.
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Return the rain details.
     * @return the rain details.
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * Return the snow details.
     * @return the snow details.
     */
    public Snow getSnow() {
        return snow;
    }

    /**
     * Return the cloud details.
     * @return the cloud details.
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * Return the time of data calculation.
     * @return the time of data calculation.
     */
    public long getDt() {
        return dt;
    }

    /**
     * Return common details.
     * @return common details.
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * Return the time zone shift.
     * @return the timezone shift.
     */
    public int getTimezone() {
        return timezone;
    }
     /**
     * Converts the UNIX timestamp to LocalDateTime considering the timezone offset.
     * @return LocalDateTime representing the time of the API response in the location's timezone.
     */
    public LocalDateTime getLocalDateTime() {
        // dt is in seconds, so we need to convert it to milliseconds
        Instant instant = Instant.ofEpochSecond(this.dt);
        // Apply the timezone offset to the Instant to get the local date-time
        ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofTotalSeconds(this.timezone));
        return LocalDateTime.ofInstant(instant, zoneId);
    }

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

    public Object getHumidity() {
        return null;
    }

    public Object getPercipitation() {
        return null;
    }

    public double getPrecipitation() {
        double rainVolume = (this.rain != null) ? this.rain.getH1() : 0.0;
        double snowVolume = (this.snow != null) ? this.snow.getH1() : 0.0;
        return rainVolume + snowVolume; // This will be 0 if both rain and snow data are missing
    }

}
