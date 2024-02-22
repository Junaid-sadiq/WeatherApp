/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.weatherapp.utils;

import fi.tuni.prog3.weatherapp.utils.APIUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for APIUtils.java
 * @author Junaid Sadiq
 */
public class APIUtilsTest {

    private APIUtils apiUtils;

    public APIUtilsTest() {
        apiUtils = new APIUtils();
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of getLocationCoordinatesUrl method, of class APIUtils.
     */
    @Test
    public void testGetLocationCoordinatesUrl() {
        String expectedUrl = "https://api.openweathermap.org/geo/1.0/direct?q=testLocation&limit=1&appid=" + apiUtils.getApiKey();
        String generatedUrl = apiUtils.getLocationCoordinatesUrl("testLocation");
        assertEquals(expectedUrl, generatedUrl);
    }

    /**
     * Test of getCurrentWeatherUrl method, of class APIUtils.
     */
    @Test
    public void testGetCurrentWeatherUrl() {
        String expectedUrl = "https://api.openweathermap.org/data/2.5/weather?lat=40.71&lon=74.01&appid=" + apiUtils.getApiKey();
        String generatedUrl = apiUtils.getCurrentWeatherUrl(40.71, 74.01);
        assertEquals(expectedUrl, generatedUrl);
    }

    /**
     * Test of getAirQualityUrl method, of class APIUtils.
     */
    @Test
    public void testGetAirQualityUrl() {
        String expectedUrl = "https://api.openweathermap.org/data/2.5/air_pollution?lat=40.71&lon=74.01&appid=" + apiUtils.getApiKey();
        String generatedUrl = apiUtils.getAirPollutionUrl(40.71, 74.01);
        assertEquals(expectedUrl, generatedUrl);
    }

    /**
     * Test of getHourlyForecastUrl method, of class APIUtils.
     */
    @Test
    public void testGetHourlyForecastUrl() {
        String expectedUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=40.71&lon=74.01&appid=" + apiUtils.getApiKey();
        String generatedUrl = apiUtils.getForecastUrl(40.71, 74.01);
        assertEquals(expectedUrl, generatedUrl);
    }

}