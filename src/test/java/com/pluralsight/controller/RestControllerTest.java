package com.pluralsight.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;


public class RestControllerTest {

	@Test(timeout = 3000)
	public void testCreateRide(){
		RestTemplate restTemplate = new RestTemplate();
		Ride ride = new Ride();
		ride.setName("Bobsled Ride");
		ride.setDuration(35);

		restTemplate.put("http://localhost:8080/ride_tracker_war_exploded/ride", ride);
	}

	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/ride_tracker_war_exploded/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
}
