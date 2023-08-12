import java.util.Collection;
import java.util.Set;

/**
 * Concrete class that inherits from LectureCourse and represents in person courses.
 * @author Sivalee Intachit
 */
public class InPersonCourse extends LectureCourse {
	/**
	 * Constructor that initializes all private fields of the parent class (LectureCourse).
	 * @param crn represents the Course Reference Number (CRN) for LectureCourse
	 * @param title represents the LectureCourse title
	 * @param levels represents the LectureCourse's level(s)
	 * @param instructor represents the instructor assigned to the LectureCourse instance
	 * @param credit represents the number of credits assigned to the LectureCourse instance
	 * @param meetDays represents the meeting days assigned to the LectureCourse instance
	 * @param gtas represents the gtas assigned to the LectureCourse instance
	 * @throws LectureCourseException is thrown if given parameters are invalid
	 */
	public InPersonCourse(String crn, String title, Set<String> levels, String instructor, int credit,
			Set<MeetDay> meetDays, Collection<String> gtas) throws LectureCourseException {
		super(crn, title, levels, instructor, credit, meetDays, gtas);
	}
	
	@Override
	public String getType() {
		return "In-Person";
	}
	
}
