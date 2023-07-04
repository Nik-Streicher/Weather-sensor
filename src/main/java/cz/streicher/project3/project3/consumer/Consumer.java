package cz.streicher.project3.project3.consumer;

import cz.streicher.project3.project3.models.Measurement;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Consumer {
    private final RestTemplate restTemplate;
    private final String defaultURL;


    public static void runExample() {
        Random random = new Random();
        Consumer consumer = new Consumer(new RestTemplate(), "http://localhost:8080");
        String sensorName = String.valueOf((random.nextInt() * 450));
        System.out.println("Server registration : " + consumer.registerNewSensor(sensorName));
        for (int x = 0; x <= 1000; x++)
            System.out.println("Data sending : " + consumer.sendData(random.nextDouble() * 100, random.nextBoolean(), sensorName));

        Arrays.stream(consumer.getMeasurements()).forEach(measurement ->
                System.out.printf("value: %.2f\nraining: %b\nsensor: %s\n\n", measurement.getValue(), measurement.getRaining(), measurement.getSensor().getName()));

    }


    public Consumer(RestTemplate restTemplate, String defaultURL) {
        this.defaultURL = defaultURL;
        this.restTemplate = restTemplate;
    }


    public String sendData(double value, boolean raining, String sensorsName) {
        Map<String, Object> json = new HashMap<>();
        json.put("value", value);
        json.put("raining", raining);
        json.put("sensor", Map.of("name", sensorsName));

        return postJson("/measurements/add", json);

    }

    public String registerNewSensor(String name) {
        Map<String, Object> json = new HashMap<>();
        json.put("name", name);

        return postJson( "/sensors/registration", json);
    }


    public Measurement[] getMeasurements() {
        Measurement[] measurements = restTemplate.getForObject((defaultURL + "/measurements"), Measurement[].class);
        return measurements;
    }


    private String postJson(String url, Map<String, Object> json) {
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(json);
        return restTemplate.postForObject((defaultURL + url), request, String.class);
    }


}
