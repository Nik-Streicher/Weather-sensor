package cz.streicher.project3.project3.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @Size(min = 3, max = 30, message = "Name should be in range between 3 and 30")
    @NotEmpty(message = "Name should be not empty")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
