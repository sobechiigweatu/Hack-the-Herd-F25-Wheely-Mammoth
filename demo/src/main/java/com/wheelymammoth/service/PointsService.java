package com.wheelymammoth.service;

import com.wheelymammoth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointsService {
    
    @Autowired
    private UserService userService;
    
    // Points earned per completed ride
    private static final int POINTS_PER_RIDE = 10;
    
    // Points required for $1 credit
    private static final int POINTS_PER_DOLLAR = 100;
    
    /**
     * Award points to a driver after completing a ride
     */
    public void awardRidePoints(String driverId) {
        User driver = userService.getUserById(driverId);
        if (driver != null && driver.isDriver()) {
            driver.addPoints(POINTS_PER_RIDE);
            userService.updateUser(driver);
        }
    }
    
    /**
     * Redeem points for credit
     * @param userId User ID
     * @param pointsToRedeem Points to redeem
     * @return Credit amount in dollars, or -1 if insufficient points
     */
    public double redeemPointsForCredit(String userId, int pointsToRedeem) {
        User user = userService.getUserById(userId);
        if (user != null && user.getPoints() >= pointsToRedeem) {
            user.redeemPoints(pointsToRedeem);
            userService.updateUser(user);
            return (double) pointsToRedeem / POINTS_PER_DOLLAR;
        }
        return -1;
    }
    
    public int getPointsPerRide() {
        return POINTS_PER_RIDE;
    }
    
    public int getPointsPerDollar() {
        return POINTS_PER_DOLLAR;
    }
}

