package cz.streicher.project3.project3.services;


import cz.streicher.project3.project3.dto.SensorDTO;
import cz.streicher.project3.project3.models.Sensor;
import cz.streicher.project3.project3.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SensorService {


    private final SensorRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void add(Sensor sensor) {
        repository.save(sensor);
    }

    public Optional<Sensor> get(int id) {
        return repository.findById(id);
    }

    public Optional<Sensor> get(String name){
        return repository.findByName(name);
    }
    public List<Sensor> getAll(){
        return repository.findAll();
    }

    public List<SensorDTO> getAllDTO(){
        return repository.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
    }


    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }


    public SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

}
