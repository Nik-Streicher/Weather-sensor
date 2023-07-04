package cz.streicher.project3.project3.util.validators;

import cz.streicher.project3.project3.models.Sensor;
import cz.streicher.project3.project3.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {


    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensor.getName() == null)
            return;

        if (sensorService.get(sensor.getName()).isPresent())
            errors.rejectValue("name", "", "Sensor already exist");
    }
}
