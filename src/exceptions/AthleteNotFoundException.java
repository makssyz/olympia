package exceptions;

public class AthleteNotFoundException extends RuntimeException {
    public AthleteNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
