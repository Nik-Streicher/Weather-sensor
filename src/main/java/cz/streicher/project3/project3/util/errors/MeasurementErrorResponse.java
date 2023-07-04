package cz.streicher.project3.project3.util.errors;

public class MeasurementErrorResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MeasurementErrorResponse(String message) {
        this.message = message;
    }
}
