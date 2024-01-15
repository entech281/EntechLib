package entechlib.swerve.exceptions;

/**
 * For when the user gives a type that is not supported or the value is null.
 * 
 * 
 * @author <a href="https://github.com/WhyDoesGodDoThis">Andrew Heitkamp</a>
 */
public class InvalidTypeException extends RuntimeException {
    /**
     * Creates a new {@code InvalidTypeException} in the output format of: {@code Invalid fieldType type. Given: given}
     * 
     * 
     * @param fieldType
     * @param given
     */
    public InvalidTypeException(String fieldType, String given) {
        super("Invalid " + fieldType + " type. Given: " + given);
    }
}
