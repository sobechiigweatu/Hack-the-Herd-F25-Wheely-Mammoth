package com.wheelymammoth.controller;

import com.wheelymammoth.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {
    
    @Autowired
    private OpenAIService openAIService;
    
    @PostMapping("/price-suggestion")
    public ResponseEntity<Map<String, Object>> getPriceSuggestion(
            @RequestBody Map<String, Object> request) {
        
        try {
            double pickupLat = Double.parseDouble(request.get("pickupLat").toString());
            double pickupLng = Double.parseDouble(request.get("pickupLng").toString());
            double dropoffLat = Double.parseDouble(request.get("dropoffLat").toString());
            double dropoffLng = Double.parseDouble(request.get("dropoffLng").toString());
            String pickupAddress = request.get("pickupAddress").toString();
            String dropoffAddress = request.get("dropoffAddress").toString();
            
            Double suggestedPrice = openAIService.suggestPrice(
                pickupLat, pickupLng, dropoffLat, dropoffLng,
                pickupAddress, dropoffAddress
            );
            
            Map<String, Object> response = new HashMap<>();
            response.put("suggestedPrice", suggestedPrice);
            response.put("success", true);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
}

