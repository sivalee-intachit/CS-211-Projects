/**
 * The LectureCourseException class is an exception that represents situations where the constructor
 * of a lecture course receives arguments that are null, empty collections, or collections with null values.
 * @author Sivalee Intachit
 */
public class LectureCourseException extends Exception {
	private String fieldName;
	
	// Getter
	public String getFieldName() {
		return fieldName;
	}
	
	/**
	 * Constructor which instantiates the error message for LectureCourseException.
	 * @param fieldName represents the field that contains an invalid value
	 */
	public LectureCourseException(String fieldName) {
		super("an argument has null or illegal value");
		this.fieldName = fieldName;
	}
}
