package cz.streicher.project3.project3.util.errors;

public class SensorErrorResponse {
    private String massage;

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public SensorErrorResponse(String massage) {
        this.massage = massage;
    }
}
