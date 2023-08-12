import java.util.Set;
import java.util.Arrays;
/**
 * Course is an abstract class for representing different kinds of courses.
 * All courses in course catalog are descendants of this class.
 * @author Sivalee Intachit
 */
public abstract class Course implements Comparable<Course> {
	private String crn;
	private String title;
	private Set<String> levels;
	
	// Getters
	public final String getCrn() {
		return crn;
	}
	public final String getTitle() {
		return title;
	}
	public final Set<String> getLevels() {
		return levels;
	}
	
	/**
	 * Constructor initializing and validating all Course private fields.
	 * @param crn represents the Course Reference Number (CRN) for Course
	 * @param title represents the Course title
	 * @param levels represents the Course's level(s)
	 * @throws LectureCourseException is thrown if parameters are invalid
	 */
	public Course(String crn, String title, Set<String> levels) throws LectureCourseException {
		// Validate crn
		if (crn == null || crn.equals("")) {
			throw new LectureCourseException("crn");
		}
		else {
			this.crn = crn;
		}
		// Validate title and check to see if it's within the 15-40 character range
		if (title == null || title.length() < 15 || title.length() > 40) {
			throw new LectureCourseException("title");
		}
		else {
			this.title = title;
		}

		// Check if levels is empty
		if (levels == null || levels.isEmpty() || levels.contains(null) || levels.size() == 0) {
			throw new LectureCourseException("levels");
		}
		else {
			// Validate levels to see if the collection contains any invalid strings
			for (String level : levels) {
				if (level.equals(null)) {
					throw new LectureCourseException("levels");
				}
				else if (level.equals("Graduate") || level.equals("Non-Degree") || level.equals("Undergraduate")) {
					continue;
				}
				else {
					throw new LectureCourseException("levels");
				}
			}
			// If no exceptions thrown, set levels
			this.levels = levels;
		}
	}
	
	/**
	 * Abstract method which retrieves the Course type.
	 * Implemented in each Course type class (InPersonCourse, HybridCourse, OnlineCourse)
	 * @return String value representing the Course type
	 */
	public abstract String getType();
	
	/**
	 * Method which compares the current instance of course to another object.
	 * @return boolean value representing whether the current instance is the same as the other instance based on crn
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Course) {
			if (this.crn == ((Course) obj).crn) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "type: " + getType() + ", CRN: " + crn + ", title: " + getTitle() + ", levels: " + Arrays.deepToString(levels.toArray());
	}
	
	/**
	 * Abstract method which compares the current instance of course to another course.
	 * Determines the ordering of each course depending on the course type. Implemented in the LectureCourse class.
	 * @return integer value representing the order of the current instance
	 */
	@Override
	public abstract int compareTo(Course argument);
	
}
