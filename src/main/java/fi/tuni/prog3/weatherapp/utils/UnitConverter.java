package fi.tuni.prog3.weatherapp.utils;

/**
 * Helper class for unit conversions
 * @author Jouka Ahponen
 */

public class UnitConverter {
    
    // Temp units
    private static final double K_TO_C = -273.15;
    
    // Wind speed units
    private static final double MPS_TO_KMH = 3.6;
    private static final double MPS_TO_FPS = 3.280840;
    private static final double MPS_TO_MPH = 2.236936;
    private static final double MPS_TO_KTS = 1.943844;
    
    // Pressure units
    private static final double HPA_TO_INHG = 0.02953;
    private static final double HPA_TO_BAR = 0.001;
    
    /**
     * Converts Temperature unit from Kelvin to Celsius
     * @param tempK temperature in Kelvin degrees
     * @return temperature in Celsius degrees
     */
    public static double kelvinToCelsius(double tempK) {
        return tempK + K_TO_C;
    }
    
    /**
     * Converts Temperature unit from Kelvin to Fahrenheit
     * @param tempK temperature in Kelvin
     * @return temperature in Fahrenheit
     */
    public static double kelvinToFahrenheit(double tempK) {
        return (tempK + K_TO_C) * 9/5 + 32;
    }
    
    
    /**
     * Converts windSpeed unit from meters per second to kilometers per hour
     * @param mps wind speed in meters per second
     * @return wind speed in kilometers per hour
     */
    public static double mpsToKmh(double mps) {
        return mps * MPS_TO_KMH;
    }
    
    /**
     * Converts windSpeed unit from meters per second to feet per second
     * @param mps wind speed in meters per second
     * @return wind speed in feet per second
     */
    public static double mpsToFps(double mps) {
        return mps * MPS_TO_FPS;
    }
    
    /**
     * Converts windSpeed unit from meters per second to miles per hour
     * @param mps wind speed in meters per second
     * @return wind speed in miles per hour
     */
    public static double mpsToMph(double mps) {
        return mps * MPS_TO_MPH;
    }
    
    /**
     * Converts windSpeed unit from meters per second to knots (used in aviation)
     * @param mps wind speed in meters per second
     * @return wind speed in knots
     */
    public static double mpsToKts(double mps) {
        return mps * MPS_TO_KTS;
    }
    
    /**
     * Converts the pressure unit from hectoPascals to inches mercury
     * @param hpa pressure in hectoPascals
     * @return pressure in inches mercury
     */
    public static double hpaToInhg(double hpa) {
        return hpa * HPA_TO_INHG;
    }   
    
    /**
     * Converts the pressure unit from hectoPascals to bars
     * @param hpa pressure in hectoPascals
     * @return pressure in bars
     */
    public static double hpaToBar(double hpa) {
        return hpa * HPA_TO_BAR;
    }
}

