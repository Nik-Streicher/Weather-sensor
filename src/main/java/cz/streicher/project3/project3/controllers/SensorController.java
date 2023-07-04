package cz.streicher.project3.project3.controllers;


import cz.streicher.project3.project3.dto.SensorDTO;
import cz.streicher.project3.project3.services.SensorService;
import cz.streicher.project3.project3.util.errors.SensorErrorResponse;
import cz.streicher.project3.project3.util.errors.SensorException;
import cz.streicher.project3.project3.util.validators.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {


    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerNewSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        sensorValidator.validate(sensorService.convertToSensor(sensorDTO), bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            fieldErrors.forEach(x -> errorMsg
                    .append(x.getDefaultMessage())
                    .append(";"));

            throw new SensorException(errorMsg.toString());
        }


        sensorService.add(sensorService.convertToSensor(sensorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }




    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorException s){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(s.getMessage()) ;

        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);

    }



}
