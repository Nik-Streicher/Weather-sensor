package cz.streicher.project3.project3.util.validators;

import cz.streicher.project3.project3.models.Measurement;
import cz.streicher.project3.project3.services.MeasurementService;
import cz.streicher.project3.project3.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (measurement.getSensor() == null)
            return;


        if (sensorService.get(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "", "The sensor is not registered");


    }
}
