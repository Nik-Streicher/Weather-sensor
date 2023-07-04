package cz.streicher.project3.project3.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull(message = "Value should be not empty")
    @Range(min = -100, max = 100, message = "Value should be in range between -100 and 100")
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Parameter raining should be not empty")
    private Boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull(message = "value 'sensor' should be not empty")
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Measurement() {
    }

    public Measurement(Double value, Boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAd) {
        this.createdAt = createdAd;
    }
}
