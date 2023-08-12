import java.util.Collection;

/**
 * CourseSearcher is a concrete class that implements the searchable interface.
 * @author Sivalee Intachit
 */
public class CourseSearcher implements Searchable {

	private Collection<String> searchTerms;
	
	/**
	 * Constructor instantiating searchTerms.
	 * @param searchTerms represents a collection of strings to search for in each available course
	 */
	public CourseSearcher(Collection<String> searchTerms) {
		this.searchTerms = searchTerms;
	}
	
	/**
	 * Method which checks to see if any criteria in an AvailableCourse instance matches any of the
	 * terms in search terms.
	 * @return a boolean value representing whether a match has been found
	 */
	@Override
	public boolean matches(AvailableCourse <String, Course> availCourse) {
		for (String term : searchTerms) {
			// Check if the crn matches any terms in searchTerms
			if (term.equals(availCourse.getKey())) {
				return true;
			}
			// Check if any terms in courseInfo match any terms in searchTerms
			if (availCourse.getValue().toString().contains(term)) {
				return true;
			}
		}
		return false;
	}
	
}
