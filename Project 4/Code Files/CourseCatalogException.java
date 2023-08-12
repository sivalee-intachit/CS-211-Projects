/**
 * The CourseCatalogException class is an exception that represents situations where a course that's
 * trying to be added has the same CRN as a course that's already in the catalog.
 * @author Sivalee Intachit
 */
public class CourseCatalogException extends IllegalStateException {
	private String crn;
	private Course course;
	
	// Getters
	public String getCrn() {
		return crn;
	}
	public Course getCourse() {
		return course;
	}
	
	/**
	 * Constructor which instantiates the error message for CourseCatalogException.
	 * @param fieldName represents the field that contains an invalid value
	 */
	public CourseCatalogException(String crn, Course course) {
		super("This course's CRN is already in the catalog.");
		this.crn = crn;
		this.course = course;
	}
}
