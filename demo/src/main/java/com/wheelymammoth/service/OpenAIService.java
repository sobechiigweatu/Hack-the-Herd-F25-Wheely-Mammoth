package com.wheelymammoth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {
    
    @Value("${openai.api.key:}")
    private String apiKey;
    
    private final WebClient webClient;
    
    public OpenAIService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .build();
    }
    
    /**
     * Get price suggestion based on route and Uber/Lyft pricing
     */
    public Double suggestPrice(double pickupLat, double pickupLng, 
                              double dropoffLat, double dropoffLng,
                              String pickupAddress, String dropoffAddress) {
        
        if (apiKey == null || apiKey.isEmpty()) {
            // Fallback: Calculate estimated price based on distance
            return calculateEstimatedPrice(pickupLat, pickupLng, dropoffLat, dropoffLng);
        }
        
        try {
            // Calculate distance (Haversine formula)
            double distance = calculateDistance(pickupLat, pickupLng, dropoffLat, dropoffLng);
            
            String prompt = String.format(
                "Based on Uber and Lyft pricing models, suggest a fair price per seat for a rideshare trip:\n" +
                "From: %s (%.6f, %.6f)\n" +
                "To: %s (%.6f, %.6f)\n" +
                "Distance: approximately %.2f miles\n\n" +
                "Consider:\n" +
                "- Base fare: $2-3\n" +
                "- Per mile: $1-2\n" +
                "- Per minute: $0.15-0.30\n" +
                "- Typical Uber/Lyft rates for similar distances\n" +
                "- Student rideshare should be 20-30%% cheaper than commercial rideshare\n\n" +
                "Respond with ONLY a number (the suggested price per seat in USD, no currency symbol, no explanation).",
                pickupAddress, pickupLat, pickupLng,
                dropoffAddress, dropoffLat, dropoffLng,
                distance
            );
            
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gpt-3.5-turbo");
            requestBody.put("messages", new Object[]{
                Map.of("role", "user", "content", prompt)
            });
            requestBody.put("max_tokens", 50);
            requestBody.put("temperature", 0.3);
            
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + apiKey);
            headers.put("Content-Type", "application/json");
            
            // For now, use fallback calculation
            // In production, you would make the actual API call here
            return calculateEstimatedPrice(pickupLat, pickupLng, dropoffLat, dropoffLng);
            
        } catch (Exception e) {
            System.err.println("Error calling OpenAI API: " + e.getMessage());
            return calculateEstimatedPrice(pickupLat, pickupLng, dropoffLat, dropoffLng);
        }
    }
    
    /**
     * Fallback price calculation based on distance
     */
    private Double calculateEstimatedPrice(double pickupLat, double pickupLng, 
                                          double dropoffLat, double dropoffLng) {
        double distance = calculateDistance(pickupLat, pickupLng, dropoffLat, dropoffLng);
        
        // Base fare + distance-based pricing (student discount applied)
        double baseFare = 2.50;
        double perMile = 1.20; // Lower than Uber/Lyft
        double minimumFare = 5.00;
        double maximumFare = 50.00;
        
        double price = baseFare + (distance * perMile);
        price = Math.max(price, minimumFare);
        price = Math.min(price, maximumFare);
        
        return Math.round(price * 100.0) / 100.0; // Round to 2 decimal places
    }
    
    /**
     * Calculate distance between two points using Haversine formula (in miles)
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 3959; // Earth's radius in miles
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return R * c;
    }
}

