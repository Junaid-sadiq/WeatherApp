package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

/**
 * Helper class to read from and write to json file. Implements iReadAndWriteToFile
 * @author jahpo
 */

public class JsonReadAndWrite implements iReadAndWriteToFile {
    
    private Gson gson;
    
    /**
     * Constructor for the JsonReadAndWrite
     */
    public JsonReadAndWrite() {
        this.gson = new Gson();
    }
    
    /**
     * Opens the file, reads it's contents and passes it forward as a string
     * @param fileName name of the file to be read
     * @return file contents as a string
     * @throws Exception if the file could not be opened or read
     */
    @Override
    public String readFromFile(String fileName) throws Exception {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }
            
            InputStreamReader reader = new InputStreamReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                stringBuilder.append((char) character);
            }
            //return gson.fromJson(stringBuilder.toString(), String.class);
            return stringBuilder.toString();
        }
        catch (IOException e) {
            throw new Exception("Error reading from file: " + e.getMessage());
        }
    }
    
    /**
     * Writes the content into a file
     * @param fileName name of the file to write into
     * @param content content string to write into the file
     * @return Boolean value if write was successful
     * @throws Exception if writing into the file was not successful
     */
    @Override
    public boolean writeToFile(String fileName, String content) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter("src/main/resources/" + fileName)) {
            JsonElement jsonElement = JsonParser.parseString(content);
            gson.toJson(jsonElement, writer);
            return true;
        }
        catch (IOException e) {
            throw new Exception("Error writing to file: " + e.getMessage());
        }
    }
}
