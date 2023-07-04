package cz.streicher.project3.project3.services;


import cz.streicher.project3.project3.dto.MeasurementDTO;
import cz.streicher.project3.project3.models.Measurement;
import cz.streicher.project3.project3.repositories.MeasurementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository repository;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;


    @Autowired
    public MeasurementService(MeasurementRepository repository, ModelMapper modelMapper, SensorService sensorService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }


    @Transactional
    public void add(Measurement measurement) {
        enrichMeasurement(measurement);
        repository.save(measurement);
    }


    public List<Measurement> getAll(){
        return repository.findAll();
    }

    public List<MeasurementDTO> getAllDTO(){
        return repository.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }

    public void enrichMeasurement(Measurement measurement){
        measurement.setSensor(sensorService.get(measurement.getSensor().getName()).get());

        measurement.setCreatedAt(LocalDateTime.now());
    }

    public int countRainyDays(){
        return repository.countRainyDays();
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }


    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
