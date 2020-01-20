package net.cnam.inf330;

public class InvalidRoverPositionException extends Exception {
    private Rover rover;
    private String message;

    public InvalidRoverPositionException(Rover rover, String message) {
        this.rover = rover;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Rover " + this.rover + "'s position is invalid ! (" + this.message + ")";
    }
}
