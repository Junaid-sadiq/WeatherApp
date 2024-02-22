/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.model;

/**
 * Model class to represent location information.
 * @author Junaid Sadiq
 */
public class LocationInfo {

    /**
     * Name of the location.
     */
    private String name;
    /**
     * Longitude coordinate.
     */
    private double lat;
    /**
     * Longitude coordinate.
     */
    private double lon;
    /**
     * Name of the country.
     */
    private String country;
    /**
     * State of the country.
     */
    private String state;

    /**
     * Returns the name of the location.
     * @return the name of the location.
     */
    public String getName() {
        return name;
    }

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

    /**
     * Returns the country of the location.
     * @return the country of the location.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the state of the location.
     * @return the state of the location.
     */
    public String getState() {
        return state;
    }

}
