package cz.streicher.project3.project3.controllers;


import cz.streicher.project3.project3.dto.MeasurementDTO;
import cz.streicher.project3.project3.models.Measurement;
import cz.streicher.project3.project3.services.MeasurementService;
import cz.streicher.project3.project3.util.errors.MeasurementErrorResponse;
import cz.streicher.project3.project3.util.errors.MeasurementException;
import cz.streicher.project3.project3.util.validators.MeasurementValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;


    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }


    @GetMapping()
    public List<MeasurementDTO> index() {
        return measurementService.getAllDTO();
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        Measurement measurement = measurementService.convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            fieldErrors.forEach(x -> errorMsg
                    .append(x.getDefaultMessage())
                    .append(";"));

            throw new MeasurementException(errorMsg.toString());
        }

        measurementService.add(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount() {
        return measurementService.countRainyDays();
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
