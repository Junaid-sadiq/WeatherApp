/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.utils.service;

import com.google.gson.JsonObject;
import java.lang.reflect.Type;

/**
 * Interface for HTTP requests.
 * @author Junaid Sadiq
 */
public interface iHttpURLConnectionHelper {

    /**
     * Perform a HTTP GET request and returns the response in the required
     * response type.
     * @param <T> Type of the response type required.
     * @param url The URL to send the GET request.
     * @param responseType The response type for deserialization.
     * @return the API response in required response type.
     * @throws Exception if the GET request fails or any error occurs.
     */
    public <T> T get(String url, Type responseType) throws Exception;

    /**
     * Perform a HTTP POST request and returns the response in the required
     * response type.
     * @param <T> Type of the response type required.
     * @param url The URL to send the POST request.
     * @param requestData The request data to be passed to the POST request
     * @param responseType The response type for deserialization.
     * @return the API response in required response type.
     * @throws Exception if the POST request fails or any error occurs.
     */
    public <T> T post(String url, JsonObject requestData, Type responseType) throws Exception;

}
