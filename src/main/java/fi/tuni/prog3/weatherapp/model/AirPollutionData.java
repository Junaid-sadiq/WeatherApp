/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.model;

import java.util.List;

/**
 * Model class to represent air pollution details.
 * @author Junaid Sadiq
 */
public class AirPollutionData {

    /**
     * Coordinates object containing longitude and latitude.
     */
    private Coord coord;
    /**
     * List of data containing main weather details, timestamp and components.
     */
    private List<DataList> list;

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
     * Inner class representing weather data list items.
     */
    public static class DataList {

        /**
         * Main weather details (AQI).
         */
        private Main main;
        /**
         * Air components details.
         */
        private Components components;
        /**
         * Timestamp.
         */
        private long dt;

        /**
         * Returns the main air pollution details.
         * @return the main air pollution details.
         */
        public Main getMain() {
            return main;
        }

        /**
         * Returns the air component details.
         * @return the air component details.
         */
        public Components getComponents() {
            return components;
        }

        /**
         * Returns the date time in UTC format.
         * @return the date time in UTC format.
         */
        public long getDt() {
            return dt;
        }
    }

     /**
     * Inner class representing main weather details (AQI).
     */
    public static class Main {

        /**
         * Air Quality Index.
         */
        private int aqi;

        /**
         * Returns the air quality index. Possible values: 1, 2, 3, 4, 5. Where
         * 1 = Good, 2 = Fair, 3 = Moderate, 4 = Poor, 5 = Very Poor.
         * @returns the air quality index.
         */
        public int getAqi() {
            return aqi;
        }
    }

    /**
     * Inner class representing weather components.
     */
    public static class Components {

        /**
         * Concentration of CO (Carbon monoxide).
         */
        private double co;
        /**
         * Concentration of NO (Nitrogen monoxide).
         */
        private double no;
        /**
         * Concentration of NO2 (Nitrogen dioxide).
         */
        private double no2;
        /**
         * Concentration of O3 (Ozone).
         */
        private double o3;
        /**
         * Concentration of SO2 (Sulphur dioxide).
         */
        private double so2;
        /**
         * Concentration of PM2.5 (Fine particles matter).
         */
        private double pm2_5;
        /**
         * Concentration of PM10 (Coarse particulate matter).
         */
        private double pm10;
        /**
         * Concentration of NH3 (Ammonia).
         */
        private double nh3;

        /**
         * Returns the concentration of CO (Carbon monoxide), μg/m3.
         * @return the concentration of CO.
         */
        public double getCo() {
            return co;
        }

        /**
         * Returns the concentration of NO (Nitrogen monoxide), μg/m3.
         * @return the concentration of NO.
         */
        public double getNo() {
            return no;
        }

        /**
         * Returns the concentration of NO2 (Nitrogen dioxide), μg/m3.
         * @return the concentration of NO2.
         */
        public double getNo2() {
            return no2;
        }

        /**
         * Returns the concentration of O3 (Ozone), μg/m3.
         * @return the concentration of O3.
         */
        public double getO3() {
            return o3;
        }

        /**
         * Returns the concentration of SO2 (Sulphur dioxide), μg/m3.
         * @return the concentration of SO2.
         */
        public double getSo2() {
            return so2;
        }

        /**
         * Returns the concentration of PM2.5 (Fine particles matter), μg/m3.
         * @return the concentration of PM2.5.
         */
        public double getPm2_5() {
            return pm2_5;
        }

        /**
         * Returns the concentration of PM10 (Coarse particulate matter), μg/m3.
         * @return the concentration of PM10.
         */
        public double getPm10() {
            return pm10;
        }

        /**
         * Returns the concentration of NH3 (Ammonia), μg/m3.
         * @return the concentration of NH3.
         */
        public double getNh3() {
            return nh3;
        }
    }

    /**
     * Returns the coordinates of the location.
     * @return the location coordinates.
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * Returns the air pollution details list.
     * @return the air pollution details as a list.
     */
    public List<DataList> getList() {
        return list;
    }

    /**
     * Returns the air quality index.
     * @return the air quality index.
     */
    public String getAirQualityDescription() {
        if (this.list != null && !this.list.isEmpty()) {
            int aqi = this.list.get(0).getMain().getAqi();
            switch (aqi) {
                case 1:
                    return "Good";
                case 2:
                    return "Fair";
                case 3:
                    return "Moderate";
                case 4:
                    return "Poor";
                case 5:
                    return "Very Poor";
                default:
                    return "Unknown";
            }
        }
        return "Data not available";
    }

}
