/**
 * The Searchable interface contains a method which returns true if a given course matches the criteria of this search.
 * @author Sivalee Intachit
 */
public interface Searchable {	
	public boolean matches(AvailableCourse <String, Course> availCourse);
	
}
