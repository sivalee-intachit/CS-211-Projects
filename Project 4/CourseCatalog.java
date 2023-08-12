import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * The CourseCatalog class represents the catalog of available courses.
 * @author Sivalee Intachit
 */
public class CourseCatalog {
	private List<AvailableCourse<String, Course>> catalog;

	// Getter
	public final List<AvailableCourse<String, Course>> getCatalog() {
		return catalog;
	}
	
	/**
	 * Constructor that instantiates catalog
	 */
	public CourseCatalog() {
		this.catalog = new ArrayList<AvailableCourse<String, Course>>();
	}
	
	/**
	 * Method which adds courses to the catalog.
	 * @param crn represents the Course Reference Number (CRN) for Course
	 * @param course represents a Course instance
	 */
	public void add(String crn, Course course) {
		// Check if course crn is already in catalog before adding
		for (AvailableCourse<String, Course> availCourse : catalog) {
			if (availCourse.getKey().equals(crn)) {
				throw new CourseCatalogException(crn, course);
			}
		}
		catalog.add(new AvailableCourse<String, Course>(crn, course));
	}
	
	/**
	 * Method which creates and returns a new list of available courses.
	 * @param searchable an instance of the Searchable interface
	 * @return an ArrayList containing all courses from the catalog for which the given searchable's matches method returns true
	 */
	public List<AvailableCourse<String, Course>> search(Searchable searchable) {
		// Instantiate return list and course searcher
		ArrayList<AvailableCourse<String, Course>> availCourses = new ArrayList<AvailableCourse<String, Course>>();
		CourseSearcher courseSearch = (CourseSearcher) searchable;
		// Loop through catalog and add each availCourse which match search terms in courseSearch to availCourses
		for (AvailableCourse<String, Course> availCourse : catalog) {
			if (courseSearch.matches(availCourse)) {
				availCourses.add(availCourse);
			}
		}
		return availCourses;
	}
	
	/**
	 * Method which sorts the field catalog according to the rules set in the compareTo method located in the LectureCourse class.
	 */
	public void sort() {
		Collections.sort(catalog);
	}
}
