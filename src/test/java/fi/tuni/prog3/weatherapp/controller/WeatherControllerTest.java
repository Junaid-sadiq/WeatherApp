/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.weatherapp.controller;

import fi.tuni.prog3.weatherapp.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for WeatherController.java
 * @author Junaid Sadiq
 */
public class WeatherControllerTest {

    private WeatherController weatherController;

    public WeatherControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        weatherController = new WeatherController();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of lookUpLocation method, of class WeatherController.
     */
    @Test
    public void testLookUpLocation_ValidLocation() {
        String location = "Tampere";
        LocationInfo result = weatherController.lookUpLocation(location);
        assertNotNull(result);
        assertEquals("Tampere", result.getName());
    }

    /**
     * Test of lookUpLocation method, of class WeatherController.
     */
    @Test
    public void testLookUpLocation_InvalidLocation() {
        String location = "";
        LocationInfo expResult = null;
        LocationInfo result = weatherController.lookUpLocation(location);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentWeather method, of class WeatherController.
     */
    @Test
    public void testGetCurrentWeather_ValidCoordinates() {
        double lat = 61.498;
        double lon = 23.7619;
        WeatherData result = weatherController.getCurrentWeather(lat, lon);
        assertNotNull(result);
        assertEquals(lat, result.getCoord().getLat(), 0.1);
    }

    /**
     * Test of getCurrentWeather method, of class WeatherController.
     */
    @Test
    public void testGetCurrentWeather_InvalidCoordinates() {
        double lat = -1000;
        double lon = 2000;
        WeatherData result = weatherController.getCurrentWeather(lat, lon);
        WeatherData expResult = null;
        assertEquals(expResult, result);
    }

    /**
     * Test of getForecast method, of class WeatherController.
     */
    @Test
    public void testGetForecast_ValidCoordinates() {
        double lat = 61.498;
        double lon = 23.7619;
        WeatherForecast result = weatherController.getForecast(lat, lon);
        assertEquals(lat, result.getCity().getCoord().getLat(), 0.1);
    }

    /**
     * Test of getForecast method, of class WeatherController.
     */
    @Test
    public void testGetForecast_InvalidCoordinates() {
        double lat = -1000;
        double lon = 2000;
        String expResult = null;
        WeatherForecast result = weatherController.getForecast(lat, lon);
        assertEquals(expResult, result);
    }

    /**
     * Test of getForecast method, of class WeatherController.
     */
    @Test
    public void testGetCurrentAirPollutionData_ValidCoordinates() {
        double lat = 61.498;
        double lon = 23.7619;
        AirPollutionData result = weatherController.getCurrentAirPollutionData(lat, lon);
        assertEquals(lat, result.getCoord().getLat(), 0.1);
    }

    /**
     * Test of getForecast method, of class WeatherController.
     */
    @Test
    public void testGetCurrentAirPollutionData_InvalidCoordinates() {
        double lat = -1000;
        double lon = 2000;
        String expResult = null;
        AirPollutionData result = weatherController.getCurrentAirPollutionData(lat, lon);
        assertEquals(expResult, result);
    }

}
