package com.springservices.springserv.service;

import org.springframework.stereotype.Service;

@Service
public class LocationService {

    public Double[] getNetworkLocation() {
        return new Double[]{40.7128, -74.0060};
    }


    public String getLocationName() {
        return "New York City";
    }
}
