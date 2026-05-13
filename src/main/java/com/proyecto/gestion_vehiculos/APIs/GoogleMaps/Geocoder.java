package com.proyecto.gestion_vehiculos.APIs.GoogleMaps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class Geocoder {

    private static final String URL =
            "https://maps.googleapis.com/maps/api/geocode/json?address=";

    @Value("${google.maps.api.key}")
    private String apiKey;

    public Double[] getLatLng(String query)
            throws Exception {

        HttpClient client =
                HttpClient.newHttpClient();

        String encodedQuery =
                URLEncoder.encode(query, "UTF-8");

        String requestUrl =
                URL
                + encodedQuery
                + "&key="
                + apiKey;

        HttpRequest request =
                HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(requestUrl))
                        .timeout(Duration.ofSeconds(5))
                        .build();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        ObjectMapper mapper =
                new ObjectMapper();

        JsonNode json =
                mapper.readTree(response.body());

        JsonNode results =
                json.get("results");

        if(results == null || results.isEmpty()){

            return null;
        }

        Double latitud =
                results.get(0)
                        .get("geometry")
                        .get("location")
                        .get("lat")
                        .asDouble();

        Double longitud =
                results.get(0)
                        .get("geometry")
                        .get("location")
                        .get("lng")
                        .asDouble();

        return new Double[]{
                latitud,
                longitud
        };
    }
}