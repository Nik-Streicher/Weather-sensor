package cz.streicher.project3.project3.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Name should be in range between 3 and 30")
    @NotEmpty(message = "Name should be not empty")
    private String name;


    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;


    public Sensor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
