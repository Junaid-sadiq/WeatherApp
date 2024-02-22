/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.weatherapp.utils.service;

import fi.tuni.prog3.weatherapp.model.LocationInfo;
import fi.tuni.prog3.weatherapp.utils.APIUtils;
import java.util.function.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for HttpURLConnectionHelper.java
 * @author Junaid Sadiq
 */
public class HttpURLConnectionHelperTest {

    private HttpURLConnectionHelper helper;

    public HttpURLConnectionHelperTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        helper = new HttpURLConnectionHelper();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testValidGETRequest() {
        try {
            APIUtils apiUtils = new APIUtils();
            Supplier<String> validUrl = () -> apiUtils.getLocationCoordinatesUrl("Tampere");
            LocationInfo[] response = helper.get(validUrl.get(), LocationInfo[].class);
            assertNotNull(response);
        } catch (Exception e) {
            fail("Exception not expected for valid GET request: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidGETRequest() {
        Supplier<String> invalidUrl = () -> "https://invalidurl";
        Exception exception = assertThrows(Exception.class, () -> helper.get(invalidUrl.get(), String.class));
        assertNotNull(exception);
    }

    @Test
    public void testMalformedJSONGETResponse() {
        String malformedJsonUrl = "https://api.test.com/malformedjson";
        Exception exception = assertThrows(Exception.class, () -> helper.get(malformedJsonUrl, String.class));
        assertNotNull(exception);
    }

}
