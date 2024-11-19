package com.springservices.springserv.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class GeoCodingService {
 //%f is a placeholder for the latitude and longitude values   %s is a placeholder for the string value
    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/reverse?format=json&lat=%s&lon=%s";
    public String getLocationNameFromCordinates(Double latitude, Double longitude){
        try{
            String  url = String.format(NOMINATIM_URL, latitude, longitude);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url,String.class);

            //parse the response
            JSONObject json  = new JSONObject(response);
            return json.getString("display_name");
        } catch (Exception e){
            return "Unknown Location caught at catch block"; //return this if there is an error
        }
    }
}
