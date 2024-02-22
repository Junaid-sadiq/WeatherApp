package fi.tuni.prog3.weatherapp;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import fi.tuni.prog3.weatherapp.WeatherAppSettings.LastSaved;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherAppSettingsTest {
    
    private WeatherAppSettings settings;
    private static String originalJson;
    
    @BeforeAll
    public static void SetUpBeforeAll() throws Exception {
        originalJson = new String(Files.readAllBytes(Paths.get("src/main/resources/config.json")));
    }
    
    @BeforeEach
    public void setUpBeforeEach() throws Exception {
        settings = WeatherAppSettings.init();
    }
     
    // Ensures that any modifications done to the config.json are restored
    @AfterEach
    public void setUpAfterEach() throws Exception {
        Files.write(Paths.get("src/main/resources/config.json"), originalJson.getBytes());
    }

    // Tests to check that the json file has initialised correctly
    @Test
    public void testGetTempUnits() throws Exception {
        System.out.println("getTempUnits");
        
        String tempUnit = settings.getUnits().getTempUnit();
        assertEquals("Celsius", tempUnit);
        
        String windUnit = settings.getUnits().getWindSpeedUnit();
        assertEquals("metersPerSecond", windUnit);
        
        String pressUnit = settings.getUnits().getPressUnit();
        assertEquals("hectoPascal", pressUnit);
    }
    
    @Test
    public void testGetFavouriteCitiesIsEmpty() throws Exception {
        System.out.println("getFavourites returns empty array");
        
        List<WeatherAppSettings.Favourites.FavouriteCity> favouriteCities = settings.getFavouriteCities();
        assertTrue(favouriteCities.isEmpty());
    }  
    
    @Test 
    public void testGetLastSavedName() throws Exception {
        System.out.println("GetLastSavedCityName is Tampere");
        
        String cityName = settings.getLastSaved().getCity();
        assertEquals("Tampere", cityName);    
    }
    
    // Test to check that object modifications to settings class work correctly
    @Test
    public void testSetUnits() throws Exception {
        System.out.println("setUnits to fahrenheit, mph and inHg");
        
        settings.getUnits().setTempUnit("Fahrenheit");
        String tempUnit = settings.getUnits().getTempUnit();
        assertEquals("Fahrenheit", tempUnit);
        
        settings.getUnits().setWindSpeedUnit("mph");
        String windUnit = settings.getUnits().getWindSpeedUnit();
        assertEquals("mph", windUnit);
        settings.getUnits().setPressUnit("inHg");
        String pressUnit = settings.getUnits().getPressUnit();
        assertEquals("inHg", pressUnit);
    }
    
    @Test
    public void testAddNewFavourite() throws Exception {
        System.out.println("add new favourite city");
        
        WeatherAppSettings.Favourites.FavouriteCity tampere = new WeatherAppSettings.Favourites.FavouriteCity(61.4980214, 23.7603118, "Tampere", "FI");
        settings.addFavouriteCity(tampere, false);
        
        List<WeatherAppSettings.Favourites.FavouriteCity> favouriteCities = settings.getFavouriteCities();
        WeatherAppSettings.Favourites.FavouriteCity lastAdded = favouriteCities.get(favouriteCities.size()-1);
        assertEquals(61.4980214, lastAdded.getLat(), 0.000001);
        assertEquals(23.7603118, lastAdded.getLon(), 0.000001);
        assertEquals("Tampere", lastAdded.getName());
        assertEquals("FI", lastAdded.getCountry());
    }
    
    @Test
    public void testRemoveNewFavourite() throws Exception {
        System.out.println("add new favourite city and then remove it");
        
        WeatherAppSettings.Favourites.FavouriteCity tampere = new WeatherAppSettings.Favourites.FavouriteCity(61.4980214, 23.7603118, "Tampere", "FI");
        settings.addFavouriteCity(tampere, false);
        
        List<WeatherAppSettings.Favourites.FavouriteCity> favouriteCities = settings.getFavouriteCities();
        WeatherAppSettings.Favourites.FavouriteCity lastAdded = favouriteCities.get(favouriteCities.size()-1);
        assertEquals(61.4980214, lastAdded.getLat(), 0.000001);
        assertEquals(23.7603118, lastAdded.getLon(), 0.000001);
        assertEquals("Tampere", lastAdded.getName());
        assertEquals("FI", lastAdded.getCountry());
        
        settings.removeFavouriteCity("Tampere", false);
    }
    
    @Test
    public void testSetLastSaved() throws Exception {
        System.out.println("Set last saved location to Helsinki");
        
        LastSaved helsinki = new LastSaved(60.192059, 24.945831, "Helsinki", "FI"); 
        settings.setLastSaved(helsinki, false);
        
        LastSaved lastSaved = settings.getLastSaved();
        assertEquals(60.192059, lastSaved.getLat(), 0.000001);
        assertEquals(24.945831, lastSaved.getLon(), 0.000001);
        assertEquals("Helsinki", lastSaved.getCity());
        assertEquals("FI", lastSaved.getCountry());
    }
    
    
}
