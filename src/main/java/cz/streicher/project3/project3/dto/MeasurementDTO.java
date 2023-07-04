package cz.streicher.project3.project3.dto;


import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class MeasurementDTO {

    @Range(min = -100, max = 100, message = "Value should be in range between -100 and 100")
    @NotNull(message = "Value should be not empty")
    private Double value;

    @NotNull(message = "Parameter raining should be not empty")
    private Boolean raining;

    @NotNull(message = "value 'sensor' should be not empty")
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensorDTO) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensorDTO;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}