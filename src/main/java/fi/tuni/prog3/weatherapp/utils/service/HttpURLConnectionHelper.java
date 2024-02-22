/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.utils.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * A class to perform HTTP GET and POST requests.
 * @author Junaid Sadiq
 */
public class HttpURLConnectionHelper implements iHttpURLConnectionHelper {

    private final Gson gson;

    /**
     * Constructor to initialize Gson object for JSON
     * serialization/deserialization.
     */
    public HttpURLConnectionHelper() {
        gson = new Gson();
    }

    /**
     * Perform a HTTP GET request and returns the response in the required
     * response type.
     * @param <T> Type of the response type required.
     * @param url The URL to send the GET request.
     * @param responseType The response type for deserialization.
     * @return the API response in required response type.
     * @throws Exception if the GET request fails or any error occurs.
     */
    @Override
    public <T> T get(String url, Type responseType) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println(response.toString());
                return gson.fromJson(response.toString(), responseType);
            }
        } else {
            throw new Exception(String.format("GET request failed: %d %s", responseCode, con.getResponseMessage()));
        }
    }

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
    @Override
    public <T> T post(String url, JsonObject requestData, Type responseType) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            String json = gson.toJson(requestData);
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return gson.fromJson(response.toString(), responseType);
            }
        } else {
            throw new Exception(String.format("POST request failed: %d %s", responseCode, con.getResponseMessage()));
        }
    }

}
