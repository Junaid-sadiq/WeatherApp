package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import java.util.List;

/**
 *@author: Jouka Ahponen
 *
 * The WeatherAppSettings class represents the settings for the weather application
 * It is intented to be used so that it stores the settings from config.json file when
 * the application is launched and keeps that config.json file up to date if there are any
 * changes done to the settings.
 *
 * Settings are loaded by calling WeatherAppSettings.init() which parses the settings
 * automatically from the json structure.

 * The settings include information about the last saved location, favourite citires
 *and unit preferences.
 */


public class WeatherAppSettings {
    
    /**
     * last saved location lat, lon, cityName and country
     */
    public LastSaved lastSaved;
    /**
     *list of favourite cities
     */
    public List<Favourites.FavouriteCity> favourites;
    
    /**
     * units for temperature, windSpeed and pressure
     */
    public Units units;
    
    private static final Gson gson = new Gson();
    private static final String SETTINGS_FILE = "config.json";
    /* src/main/resources/config.json */
    
    public WeatherAppSettings() {
        // Default constructor
    }
    
    /**
     * Initialises the WeatherAppSettings from a SERTTINGS_FILE
     * @return all the settings structured as per this class
     * @throws Exception if not able to initialise the WeatherAppSettings from Json file
     */
    public static WeatherAppSettings init() throws Exception {
        iReadAndWriteToFile jsonHandler = new JsonReadAndWrite();
        String jsonString = jsonHandler.readFromFile(SETTINGS_FILE);
        WeatherAppSettings settings = gson.fromJson(jsonString, WeatherAppSettings.class);
        if (settings != null) {
            return settings;
        }
        throw new Exception("Failed to intitialise WeatherAppSettings from JSON file");
    }
    
    /**
     * Updates the Json file from the properties of this class
     */
    public void updateJson() {
        JsonReadAndWrite jsonHandler = new JsonReadAndWrite();
        try {
            jsonHandler.writeToFile(SETTINGS_FILE, gson.toJson(this));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * gets the current LastSaved object in the class
     * @return lastSaved object
     */
    public LastSaved getLastSaved() {
        return lastSaved;
    }
    
    /**
     * Updates the lastSaved object
     * @param lastSaved 
     */
    public void setLastSaved(LastSaved lastSaved) {
        setLastSaved(lastSaved, true);
        
    }
    
   /**
    * Overloaded method for lastSaved in case we don't want to update the json file
    * Used mainly for testing
    * @param lastSaved
    * @param update 
    */
    public void setLastSaved(LastSaved lastSaved, Boolean update) {
        this.lastSaved = lastSaved;
        if (update)
            updateJson();
    }
    
    /**
     * gets the list of current favourites object
     * @return  favourites which contains a list of FavouriteCities
     */
    public List<Favourites.FavouriteCity> getFavouriteCities() {
        return favourites;
    }
    
    /**
     * Adds a new favouriteCity into the favourites list
     * @param city 
     */
    public void addFavouriteCity(Favourites.FavouriteCity city) {
        addFavouriteCity(city, true);
    }
    
    /**
     * Overloaded method for favouriteCity in case we don't want to update the json file
     * Used mainly for testing
     * @param city
     * @param update 
     */
    public void addFavouriteCity(Favourites.FavouriteCity city, Boolean update) {
        favourites.add(city);
        if (update)
            updateJson();
    }
    
    /**
     * Removes a favourite city from the list based on it's name string
     * @param city 
     */
    public void removeFavouriteCity(String city) {
        removeFavouriteCity(city, true);
    }
    
    /**
     * Overloaded method to remove a favouriteCity in case we don't want to update the json file
     * Used mainly for testing
     * @param cityName
     * @param update 
     */
    public void removeFavouriteCity(String cityName, Boolean update) {
        Favourites.FavouriteCity cityToRemove = null;
        // Find if the city name is actually in the favourite cities
        for (Favourites.FavouriteCity city : favourites) {
            if (city.name.equals(cityName)) {
                cityToRemove = city;
                break;
            }
        }
        // Remove if the city is found. If not found, do nothing.
        if (cityToRemove != null) {
            favourites.remove(cityToRemove);
            if (update)
                updateJson();
        }
    }
    
    /**
     * gets the content of current units object
     * @return 
     */
    public Units getUnits() {
        return units;
    }
    
    /**
     * Updates the units object
     * @param units 
     */
    public void setUnit(Units units) {
        setUnits(units, true);
    }
    
    /**
     * Overloaded method to update the units object in case we don't want to update the json file
     * Used mainly for testing
     * @param units
     * @param update 
     */
    public void setUnits(Units units, Boolean update) {
        this.units = units;
        if (update) 
            updateJson();
        
    }

    /**
     * Nested class for LastSaved object to parse the json file correctly
     */
    public static class LastSaved {
        public double lat;
        public double lon;
        public String city;
        public String country;
        
        /**
         * Constructor for the LastSaved object
         * @param lat latitude in decimal coordinates
         * @param lon longitude in decimal coordinates
         * @param city city name
         * @param country Country suffix
         */
        public LastSaved(double lat, double lon, String city, String country) {
            update(lat, lon, city, country);
        }
        
        /**
         * updates the lastSaved object
         * @param lat latitude in decimal coordinates
         * @param lon longitude in decimal coordinates
         * @param city city name
         * @param country country suffix
         */
        public void update(double lat, double lon, String city, String country) {
            this.lat = lat;
            this.lon = lon;
            this.city = city;
            this.country = country;
        }
        
        /**
         * gets the latitude of the lastSaved city
         * @return latitude in decimal coordinates
         */
        public double getLat() {
            return lat;
        }
        
        /**
         * gets the longitude of the lastSaved city
         * @return longitude in decimal coordinates
         */
        public double getLon() {
            return lon;
        }
        
        /**
         * gets the city name
         * @return city name
         */
        public String getCity() {
            return city;
        }
        
        /**
         * returns the country where the city is located
         * @return country suffix
         */
        public String getCountry() {
            return country;
        }
    }
    
    /**
     * Nested class for the Favourites list object to parse the json file correctly
     */
    public static class Favourites {
        public List<FavouriteCity> favourites;
        
        /**
         * Constructor for the Favourites list object
         * @param favourites 
         */
        public Favourites(List<FavouriteCity> favourites) {
            this.favourites = favourites;
        }
        
        /**
         * gets the current favourites list object
         * @return 
         */
        public List<FavouriteCity> getFavourites() {
            return favourites;
        }
        
        /**
         * adds a new favourite city in the the favourites list object
         * @param city 
         */
        public void addFavourite(FavouriteCity city) {
            favourites.add(city);
        }
        
        /**
         * Nested class for the Favourites List for each item in the list
         */
        public static class FavouriteCity {
            public double lat;
            public double lon;
            public String name;
            public String country;
        
            /**
             * Constructor for a single city in the list
             * @param lat latitude in decimal coordinates
             * @param lon longitude in decimal coordinates
             * @param name city name
             * @param country Country suffix
             */
            public FavouriteCity(double lat, double lon, String name, String country) {
                this.lat = lat;
                this.lon = lon;
                this.name = name;
                this.country = country;
            }
            
            /**
             * gets the latitude of the city
             * @return latitude in decimal coordinates
             */
            public double getLat() {
                return lat;
            }
            
            /**
             * gets the longitude of the city
             * @return longitude in decimal coordinates
             */
            public double getLon() {
                return lon;
            }
            
            /**
             * gets the city name
             * @return city name
             */
            public String getName() {
                return name;
            }
            
            /**
             * gets the country where the city is located
             * @return country suffix
             */
            public String getCountry() {
                return country;
            }
        }
    }
    
    /**
     * Nested class for the units to parse the json file correctly
     */
    public static class Units {
        public String tempUnit;
        public String windSpeedUnit;
        public String pressUnit;
        
        /**
         * Constructor for the units
         * @param tempUnit temperature unit string
         * @param windSpeedUnit wind speed unit string
         * @param pressUnit pressure unit string
         */
        public Units(String tempUnit, String windSpeedUnit, String pressUnit) {
            this.tempUnit = tempUnit;
            this.windSpeedUnit = windSpeedUnit;
            this.pressUnit = pressUnit;
        }
        
        /**
         * gets the current temperature unit
         * @return temperature unit string
         */
        public String getTempUnit() {
            return tempUnit;
        }
        
        /**
         * sets a new temperature unit
         * @param tempUnit 
         */
        public void setTempUnit(String tempUnit) {
            this.tempUnit = tempUnit;
        }
        
        /**
         * gets the current wind speed unit
         * @return wind speed unit string
         */
        public String getWindSpeedUnit() {
            return windSpeedUnit;
        }
        
        /**
         * sets a new wind speed unit
         * @param windSpeedUnit 
         */
        public void setWindSpeedUnit(String windSpeedUnit) {
            this.windSpeedUnit = windSpeedUnit;
        }
        
        /**
         * gets the current pressure unit
         * @return pressure unit string
         */
        public String getPressUnit() {
            return pressUnit;
        }
        
        /**
         * sets a new pressure unit
         * @param pressUnit 
         */
        public void setPressUnit(String pressUnit) {
            this.pressUnit = pressUnit;
        }
    }
}
