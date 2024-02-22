/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.view;

import fi.tuni.prog3.weatherapp.WeatherAppSettings;
import fi.tuni.prog3.weatherapp.model.AirPollutionData;
import fi.tuni.prog3.weatherapp.model.LocationInfo;
import fi.tuni.prog3.weatherapp.controller.WeatherController;
import fi.tuni.prog3.weatherapp.model.WeatherData;
import fi.tuni.prog3.weatherapp.model.WeatherData.Sys;
import fi.tuni.prog3.weatherapp.model.WeatherForecast;
import fi.tuni.prog3.weatherapp.utils.UnitConverter;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class WeatherView implements Initializable {

    private final WeatherController weatherController;

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, d MMM");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String formattedDate = now.format(dateFormatter);
    String formattedTime = now.format(timeFormatter);

    public WeatherView() {
        this.weatherController = new WeatherController();
    }
    

    private boolean isFahrenheit = false;
    private WeatherData currentWeatherData;
    private String currentCityName;
    private WeatherAppSettings settings;

    @FXML
    private VBox mainContainer;
    @FXML
    private Button homeButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button switchOffButton;
    @FXML
    private HBox searchButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private Label weatherDataLabel;
    @FXML
    private VBox weatherTileContainer;
    @FXML
    private Label dateLabel;
    @FXML
    private Label todayLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private ToggleButton temperatureToggle;
    @FXML
    private Label cityNameLabel;
    @FXML
    private Label currentWeatherLabel;
    @FXML
    private Label temperatureLabel;
    @FXML
    private Label minTemperatureLabel;
    @FXML
    private Label maxTemperatureLabel;
    @FXML
    private Label feelsLikeLabel;
    @FXML
    private Label windLabel;
    @FXML
    private Label humidityLabel;
    @FXML
    private Label precipitationLabel;
    @FXML
    private Label uvIndexLabel;
    @FXML
    private Label visibilityLabel;
    @FXML
    private Label lastSearchedCityLabel;
    @FXML
    private Label airQualityLabel;
    @FXML
    private HBox favouritesHBox;
    @FXML
    private ListView<String> favouritesList;
    @FXML
    private Button addButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button addFavoriteButton;
    @FXML
    private Button removeFavoriteButton;
    @FXML
    private Button loadFavoriteButton;
    @FXML
    private ImageView weatherIconImageView;
    @FXML
    private Label rainProbabilityLabel;
    @FXML
    private VBox favoriteCitiesContainer;
    @FXML
    private LineChart<String, Number> temperatureChart;
    private WeatherForecast currentForecastData;
    

   
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.settings = WeatherAppSettings.init(); // Initialize settings from JSON
            if (this.settings == null) {
                System.out.println("Settings initialization failed.");
                return;
            }
            // Check if lastSaved exists and has valid data
            if (this.settings.getLastSaved() != null && this.settings.getLastSaved().getCity() != null) {
                String lastSavedCity = this.settings.getLastSaved().getCity();
                fetchInitialWeatherData(lastSavedCity);
                lastSearchedCityLabel.setText("Last Searched: " + lastSavedCity);
               // Update forecast UI for the last saved city's coordinates
                double lastSavedLat = this.settings.getLastSaved().getLat();
                double lastSavedLon = this.settings.getLastSaved().getLon();
                updateForecastUI(lastSavedLat, lastSavedLon);
                populateFavoriteCitiesList();
            } else {
                // Fall back to a default city if no last saved city exists
                fetchInitialWeatherData("Tampere");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fetchInitialWeatherData(String cityName) {
        LocationInfo location = weatherController.lookUpLocation(cityName);
        if (location != null) {
            WeatherData weatherData = weatherController.getCurrentWeather(location.getLat(), location.getLon());
            if (weatherData != null) {
                currentCityName = cityName;
                currentWeatherData = weatherData;
                updateWeatherUI(cityName, weatherData);
                // Fetch and update forecast data
                fetchAndUpdateForecastData(location.getLat(), location.getLon());
            } else {
                System.out.println("Failed to get weather or air quality data.");
            }
        } else {
            System.out.println("Failed to get location data.");
        }
    }
    private void fetchAndUpdateForecastData(double lat, double lon) {
        WeatherForecast forecast = weatherController.getForecast(lat, lon);
        if (forecast != null && !forecast.getList().isEmpty()) {
            currentForecastData = forecast; // Store the forecast data
            updateTemperatureChart(forecast);
        } else {
            System.out.println("Failed to fetch forecast data.");
        }
    }

 
    public void updateWeatherUI(String cityName, WeatherData weatherData) {
        Platform.runLater(() -> {
            // Get the local date-time of the last update from the API response
            LocalDateTime apiLocalDateTime = weatherData.getLocalDateTime();
            // Format the date and time to display in the UI
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, d MMM uuuu"); // Updated pattern for date
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // Updated pattern for time without seconds
            String formattedApiDate = apiLocalDateTime.format(dateFormatter);
            String formattedApiTime = apiLocalDateTime.format(timeFormatter);
            // Set the formatted date and time in the UI components
            dateLabel.setText(formattedApiDate);
            timeLabel.setText(String.format("Updated at %s", formattedApiTime));
            double temperature = weatherData.getMain().getTemp();
            double feelsLike = weatherData.getMain().getFeels_like();
            
            double tempMin = weatherData.getMain().getTemp_min();  // Retrieve min temperature
            double tempMax = weatherData.getMain().getTemp_max(); 
             
            AirPollutionData airQualityData = weatherController.getCurrentAirPollutionData(weatherData.getCoord().getLat(), weatherData.getCoord().getLon());
            if (airQualityData != null) {
                airQualityLabel.setText("Air Quality: " + airQualityData.getAirQualityDescription());
                System.out.println("Air Quality: " + airQualityData.getAirQualityDescription());
            } else {
                airQualityLabel.setText("Air Quality data not available");
            }
            /* uvIndexLabel.setText(String.format("UV Index: %.1f", weatherData.getUVIndex()));
            System.out.println("UV Index: " + weatherData.getUVIndex()); */
            precipitationLabel.setText(String.format("Precipitation: %.1f mm", weatherData.getPrecipitation()));
            System.out.println("Precipitation: " + weatherData.getPercipitation());
            visibilityLabel.setText(String.format("Visibility: %d km", weatherData.getVisibility() / 1000));
            System.out.println("Visibility: " + weatherData.getVisibility() / 1000);
            humidityLabel.setText(String.format("Humidity: %d %%", weatherData.getMain().getHumidity()));
            System.out.println("Humidity: " + weatherData.getMain().getHumidity());
            windLabel.setText(String.format("Wind: %.1f m/s", weatherData.getWind().getSpeed()));
             
            // Fetch and update rain probability
            updateForecastUI(weatherData.getCoord().getLat(), weatherData.getCoord().getLon());
            
            // Update the city name label with the current city name
            cityNameLabel.setText(cityName);
            System.out.println("cityName: " + cityName);  
            if (isFahrenheit) {
                temperature = UnitConverter.kelvinToFahrenheit(temperature);
                feelsLike = UnitConverter.kelvinToFahrenheit(feelsLike);
                temperatureLabel.setText(String.format("%.1f°F", temperature));
                feelsLikeLabel.setText(String.format("Feels like: %.1f°F", feelsLike));
                tempMin = UnitConverter.kelvinToFahrenheit(tempMin);
                tempMax = UnitConverter.kelvinToFahrenheit(tempMax);
                
                // Update UI labels with formatted Fahrenheit temperatures
                minTemperatureLabel.setText(String.format("Min: %.1f°F", tempMin));
                maxTemperatureLabel.setText(String.format("Max: %.1f°F", tempMax));
                
            } else {
                temperature = UnitConverter.kelvinToCelsius(temperature);
                feelsLike = UnitConverter.kelvinToCelsius(feelsLike);
                temperatureLabel.setText(String.format("%.1f°C", temperature));
                feelsLikeLabel.setText(String.format("Feels like: %.1f°C", feelsLike));
               // Convert temperatures to Celsius
                tempMin = UnitConverter.kelvinToCelsius(tempMin);
                tempMax = UnitConverter.kelvinToCelsius(tempMax);

                // Update UI labels with formatted Celsius temperatures
                minTemperatureLabel.setText(String.format("Min: %.1f°C", tempMin));
                maxTemperatureLabel.setText(String.format("Max: %.1f°C", tempMax));
            }
             
            currentWeatherLabel.setText(weatherData.getWeather()[0].getDescription());
            String weatherCondition = weatherData.getWeather()[0].getMain();

            Image weatherIcon;
            switch (weatherCondition) {
                case "Clear":
                    weatherIcon = new Image("/assets/weathericons/Clouds/SunnyCloud/SunnyCloud.png");
                    weatherIconImageView.setImage(weatherIcon);
                    break;
                case "Clouds":
                    weatherIcon = new Image("/assets/weathericons/Clouds/DayCloudy/DayCloudy.png");
                    weatherIconImageView.setImage(weatherIcon);
                 case "Few Clouds":
                    weatherIcon = new Image("/assets/weathericons/PartlyCloudy/DayPartlyCloudy/DayPartlyCloudy.png");
                    weatherIconImageView.setImage(weatherIcon);    
                    break;
                case "Rain":
                    weatherIcon = new Image("/assets/weathericons/Clouds/DayCloudy/DayCloudy.png");
                    weatherIconImageView.setImage(weatherIcon);
                    break;
                case "Thunderstorm":
                    weatherIcon = new Image("assets/weathericons/Lightning/Lightning.png");
                    weatherIconImageView.setImage(weatherIcon);
                    break;
                case "Snow":
                    weatherIcon = new Image("/assets/weathericons/Snow/Snow.png");
                    weatherIconImageView.setImage(weatherIcon);
                    break;
                case "Mist":
                    weatherIcon = new Image("/assets/weathericons/Clouds/DayCloudy/DayCloudy.png");
                    weatherIconImageView.setImage(weatherIcon);
                    break;
                default:
                    weatherIcon = new Image("/assets/weathericons/Suns/Sun/Sun.png");
                    weatherIconImageView.setImage(weatherIcon);
                    break;
            }
        });
    }

    public void updateForecastUI(double lat, double lon) {
        WeatherForecast forecast = weatherController.getForecast(lat, lon);
        if (forecast != null && !forecast.getList().isEmpty()) {
            // Fetching rain probability from the first item in the forecast
            double rainProbability = forecast.getList().get(0).getPop() * 100; // Convert to percentage
    
            Platform.runLater(() -> {
                rainProbabilityLabel.setText(String.format("Rain Probability: %.0f%%", rainProbability));
                // Assuming currentCityName is a class member that holds the current city name
                temperatureChart.setTitle("Temperature Forecast for " + currentCityName);
                System.out.println("Rain Probability: " + rainProbability);
    
                // Don't forget to call the method to update the chart data
                updateTemperatureChart(forecast);
            });
        } else {
            System.out.println("Forecast data not available");
            Platform.runLater(() -> {
                rainProbabilityLabel.setText("Rain Probability: Data not available");
            });
        }
    }
    
  /*   public void updateForecastUI(double lat, double lon) {
    WeatherForecast forecast = weatherController.getForecast(lat, lon);
    if (forecast != null && !forecast.getList().isEmpty()) {
        // Fetching rain probability from the first item in the forecast
        double rainProbability = forecast.getList().get(0).getPop() * 100; // Convert to percentage

        Platform.runLater(() -> {
            rainProbabilityLabel.setText(String.format("Rain Probability: %.0f%%", rainProbability));
            System.out.println("Rain 3Probability: " + rainProbability);
        });
        temperatureChart.setTitle("Temperature Forecast for " + cityName);
    } else {
        System.out.println("Forecast data not available");
        Platform.runLater(() -> {
            rainProbabilityLabel.setText("Rain Probability: Data not available");
        });
    }
}
 */
    @FXML
    private void onSearch() {
        String cityName = searchTextField.getText().trim();
        if (!cityName.isEmpty()) {
            LocationInfo location = weatherController.lookUpLocation(cityName);
            if (location != null) {
                WeatherData weatherData = weatherController.getCurrentWeather(location.getLat(), location.getLon());
                if (weatherData != null) {
                    final String finalCityName = cityName; // Final variable to use inside lambda
                    Platform.runLater(() -> {
                        // Update last saved city
                        settings.setLastSaved(new WeatherAppSettings.LastSaved(
                                location.getLat(),
                                location.getLon(),
                                finalCityName,
                                weatherData.getSys().getCountry() 
                        ));
                        settings.updateJson();
                        lastSearchedCityLabel.setText("Last Searched: " + finalCityName);
                        // Update the UI with the new weather data
                        cityNameLabel.setText(finalCityName);
                        updateWeatherUI(finalCityName, weatherData);
    
                        currentCityName = finalCityName; // Update currentCityName with the searched city
                    });
                } else {
                    Platform.runLater(() -> {
                        weatherDataLabel.setText("Weather data is not available for " + cityName);
                    });
                }
            } else {
                Platform.runLater(() -> {
                    weatherDataLabel.setText("Location not found for " + cityName);
                });
            }
        } else {
            Platform.runLater(() -> {
                weatherDataLabel.setText("Please enter a city name.");
            });
        }
    }
    

    @FXML
    private void handleTemperatureToggle(ActionEvent event) {
        isFahrenheit = temperatureToggle.isSelected();
        temperatureToggle.setText(isFahrenheit ? "Fahrenheit" : "Celsius");
        
        // Refresh the weather UI and temperature chart
        refreshWeatherUI();
    }
    
    private void refreshWeatherUI() {
        if (currentWeatherData != null) {
            updateWeatherUI(currentCityName, currentWeatherData);
        }
        
        // Refresh the forecast chart using the stored forecast data
        if (currentForecastData != null) {
            updateTemperatureChart(currentForecastData);
        }
    }
    
    
    @FXML
    private void populateFavoriteCitiesList() {
        Platform.runLater(() -> {
            favouritesList.getItems().clear(); // Clear existing items
            for (WeatherAppSettings.Favourites.FavouriteCity city : settings.getFavouriteCities()) {
                favouritesList.getItems().add(city.getName()); // Add city name to the list
            }
        });
    }

    // Method to update the favorite cities display
    @FXML
    private void updateFavoriteCitiesDisplay() {
        favouritesHBox.getChildren().clear();
        for (WeatherAppSettings.Favourites.FavouriteCity city : settings.getFavouriteCities()) {
            Button cityButton = new Button(city.getName());
            cityButton.setOnAction(event -> {
                // Update the UI with the selected city's weather data
                WeatherData weatherData = weatherController.getCurrentWeather(city.getLat(), city.getLon());
                if (weatherData != null) {
                    currentCityName = city.getName();
                    currentWeatherData = weatherData;
                    updateWeatherUI(city.getName(), weatherData);
                } else {
                    System.out.println("Failed to get weather or air quality data.");
                }
            });
            favouritesHBox.getChildren().add(cityButton);
        }
    }
    @FXML
    public void onCitySelected() {
        String selectedCityName = favouritesList.getSelectionModel().getSelectedItem();
        if (selectedCityName != null) {
            // Load weather data for the selected city or just store the selection
            // For example:
            loadWeatherDataForCity(selectedCityName);
        }
    }
    @FXML
private void loadWeatherDataForCity(String cityName) {
    // Use the weatherController to fetch weather data for the given city name
    LocationInfo location = weatherController.lookUpLocation(cityName);
    if (location != null) {
        WeatherData weatherData = weatherController.getCurrentWeather(location.getLat(), location.getLon());
        if (weatherData != null) {
            currentCityName = cityName;
            currentWeatherData = weatherData;
            updateWeatherUI(cityName, weatherData);
            
            
            // Update last saved city in settings
            settings.setLastSaved(new WeatherAppSettings.LastSaved(
                    location.getLat(),
                    location.getLon(),
                    cityName,
                    weatherData.getSys().getCountry()
            ));
            settings.updateJson(); // Save the updated settings to config.json
            lastSearchedCityLabel.setText("Last Searched: " + cityName);

        } else {
            System.out.println("Failed to get weather data for " + cityName);
            showAlert("Loading Error", "Unable to load weather data for " + cityName);
        }
    } else {
        System.out.println("Location not found for " + cityName);
        showAlert("Location Error", "Location not found for " + cityName);
    }
}
    
/* @FXML
private void loadWeatherDataForCity(String cityName) {
    // Use the weatherController to fetch weather data for the given city name
    LocationInfo location = weatherController.lookUpLocation(cityName);
    if (location != null) {
        WeatherData weatherData = weatherController.getCurrentWeather(location.getLat(), location.getLon());
        if (weatherData != null) {
            currentCityName = cityName;
            currentWeatherData = weatherData;
            updateWeatherUI(cityName, weatherData);

            // Update last saved city in settings
            settings.setLastSaved(new WeatherAppSettings.LastSaved(
                    location.getLat(),
                    location.getLon(),
                    cityName,
                    weatherData.getSys().getCountry()
            ));
            settings.updateJson(); // Save the updated settings to config.json
            lastSearchedCityLabel.setText("Last Searched: " + cityName);

            // Fetch and update the forecast data for the new city
            fetchAndUpdateForecastData(location.getLat(), location.getLon());

        } else {
            System.out.println("Failed to get weather data for " + cityName);
            showAlert("Loading Error", "Unable to load weather data for " + cityName);
        }
    } else {
        System.out.println("Location not found for " + cityName);
        showAlert("Location Error", "Location not found for " + cityName);
    }
}
    */

    @FXML
    void handleAddFavorites() {
        if (currentCityName != null && !currentCityName.isEmpty()) {
            // Check if the city is already in favorites
            if (isCityInFavorites(currentCityName)) {
                // Show an alert or warning
                showAlert("City already in favorites", "The city " + currentCityName + " is already in your favorites list.");
            } else {
                // Add city to favorites
                WeatherAppSettings.Favourites.FavouriteCity newFavorite = new WeatherAppSettings.Favourites.FavouriteCity(
                    currentWeatherData.getCoord().getLat(),
                    currentWeatherData.getCoord().getLon(),
                    currentCityName,
                    currentWeatherData.getSys().getCountry()
                );
                settings.addFavouriteCity(newFavorite);
                // Update the JSON file
                settings.updateJson();
                populateFavoriteCitiesList();
                /* showAlert("Added to favorites:", "the city " +  currentCityName+ " is added to favorites" ); */
                System.out.println("Added to favorites: " + currentCityName);
            }
        } else {
            System.out.println("No city data available to add to favorites.");
        }
    } 

    private boolean isCityInFavorites(String cityName) {
        System.out.println("Checking if " + cityName + " is in favorites...");
        for (WeatherAppSettings.Favourites.FavouriteCity city : settings.getFavouriteCities()) {
            System.out.println("Comparing with favorite: " + city.getName());
            if (city.getName().equalsIgnoreCase(cityName)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void handleRemoveFavourites() {
        String selectedCityName = favouritesList.getSelectionModel().getSelectedItem();
        if (selectedCityName != null && !selectedCityName.isEmpty()) {
            settings.removeFavouriteCity(selectedCityName);
            System.out.println("Removed from favorites: " + selectedCityName);
            populateFavoriteCitiesList(); // Refresh the ListView
        } else {
            System.out.println("No city selected to remove from favorites.");
            showAlert("Remove Favorite", "Please select a city to remove.");
        }
    }
    
    @FXML 
    void handleLoadFavourites() {
        String selectedCityName = favouritesList.getSelectionModel().getSelectedItem();
        if (selectedCityName != null && !selectedCityName.isEmpty()) {
            loadWeatherDataForCity(selectedCityName);
        } else {
            System.out.println("No city selected to load.");
            showAlert("Load Favorite", "Please select a city to load.");
        }
    }
    
    private XYChart.Series<String, Number> prepareChartData(WeatherForecast forecast) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:dd:MM");
    
        for (WeatherForecast.WeatherInfo info : forecast.getList()) {
            String formattedDate = Instant.ofEpochSecond(info.getDt())
                                            .atZone(ZoneId.systemDefault())
                                            .format(formatter);
            double temperature = isFahrenheit ? UnitConverter.kelvinToFahrenheit(info.getMain().getTemp()) 
                                              : UnitConverter.kelvinToCelsius(info.getMain().getTemp());
            series.getData().add(new XYChart.Data<>(formattedDate, temperature));
        }
    
        return series;
    }

        @FXML
    private void updateTemperatureChart(WeatherForecast forecast) {
        XYChart.Series<String, Number> series = prepareChartData(forecast);
        temperatureChart.getData().clear();
        temperatureChart.getData().add(series);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    

    @FXML
    private void handleSwitchOff() {
        Platform.exit();
    }

}
