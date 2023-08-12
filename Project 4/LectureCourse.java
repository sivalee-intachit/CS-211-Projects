import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

/**
 * LectureCourse is an abstract class that represents different types of Lecture courses. Is a child class of the Course class.
 * All courses in course catalog are descendants of this class.
 * @author Sivalee Intachit
 */
public abstract class LectureCourse extends Course {
	private String instructor;
	private int credit;
	private Set<MeetDay> meetDays;
	private Collection<String> gtas;
	
	// Getters
	public final String getInstructor() {
		return instructor;
	}
	public final int getCredit() {
		return credit;
	}
	public final Collection<MeetDay> getMeetDays() {
		return meetDays;
	}
	public final Collection<String> getGtas() {
		return gtas;
	}
	
	/**
	 * Constructor initializing and validating all private fields of LectureCourse and the parent class Course.
	 * @param crn represents the Course Reference Number (CRN) for LectureCourse
	 * @param title represents the LectureCourse title
	 * @param levels represents the LectureCourse's level(s)
	 * @param instructor represents the instructor assigned to the LectureCourse instance
	 * @param credit represents the number of credits assigned to the LectureCourse instance
	 * @param meetDays represents the meeting days assigned to the LectureCourse instance
	 * @param gtas represents the gtas assigned to the LectureCourse instance
	 * @throws LectureCourseException is thrown if given parameters are invalid
	 */
	public LectureCourse(String crn, String title, Set<String> levels, String instructor, int credit, Set<MeetDay> meetDays, Collection<String> gtas) throws LectureCourseException {
		super(crn, title, levels);
		
		// Validate instructor
		if (instructor == null) {
			throw new LectureCourseException("instructor");
		}
		else {
			this.instructor = instructor;
		}
		
		// Validate credit
		if (this.credit < 0) {
			throw new LectureCourseException("credit");
		}
		else {
			this.credit = credit;
		}
		
		// Validate meetDays
		if (meetDays == null || meetDays.isEmpty() || meetDays.contains(null) || meetDays.size() == 0) {
			throw new LectureCourseException("meetDays");
		}
		// If no exceptions thrown, set meetDays
		else {
			this.meetDays = meetDays;
		}
		
		// Validate gtas
		if (gtas == null || gtas.contains(null)) {
			throw new LectureCourseException("gtas");
		}
		else {
			this.gtas = gtas;
		}
	}
	
	@Override
	public String toString() {
		return "instructor: " + instructor + ", credit: " + credit + ", meetDays: " + Arrays.deepToString(meetDays.toArray()) + ", gtas: " + Arrays.deepToString(gtas.toArray()) + ", " + super.toString();
	}
	
	/**
	 * Method that compares the current instance of LectureCourse to another Course.
	 * Determines the ordering of each course depending on the course type.
	 * @return integer value representing the order of the current instance
	 */
	@Override
	public int compareTo(Course argument) {
		if (this instanceof InPersonCourse) {
			// If both the current instance and the argument are instances of InPersonCourse, do the following
			if (argument instanceof InPersonCourse) {
				InPersonCourse otherCourse = (InPersonCourse) argument;
				// Sort by credit
				if (this.getCredit() - otherCourse.getCredit() != 0) {
					return this.getCredit() - otherCourse.getCredit();
				}
				// Sort by title
				else if (this.getTitle().compareTo(otherCourse.getTitle()) != 0) {
					return this.getTitle().compareTo(otherCourse.getTitle());
				}
			}
			// Sort before argument
			else if (argument instanceof HybridCourse || argument instanceof OnlineCourse) {
				return -1;
			}
		}
		else if (this instanceof HybridCourse) {
			// If both the current instance and the argument are instances of HybridCourse, do the following
			if (argument instanceof HybridCourse) {
				HybridCourse otherCourse = (HybridCourse) argument;
				// Sort by title
				if (this.getTitle().compareTo(otherCourse.getTitle()) != 0) {
					return this.getTitle().compareTo(otherCourse.getTitle());
				}
				// Sort by credit
				else if (this.getCredit() - otherCourse.getCredit() != 0) {
					return this.getCredit() - otherCourse.getCredit();
				}
			}
			// Sort after argument
			else if (argument instanceof InPersonCourse) {
				return 1;
			}
			// Sort before argument
			else if (argument instanceof OnlineCourse) {
				return -1;
			}
		}
		else if (this instanceof OnlineCourse) {
			// If both the current instance and the argument are instances of OnlineCourse, do the following
			if (argument instanceof OnlineCourse) {
				OnlineCourse otherCourse = (OnlineCourse) argument;
				// Sort by title
				if (this.getTitle().compareTo(otherCourse.getTitle()) != 0) {
					return this.getTitle().compareTo(otherCourse.getTitle());
				}
				// Sort by num meetDays
				else if (this.getMeetDays().size() - otherCourse.getMeetDays().size() != 0) {
					return this.getMeetDays().size() - otherCourse.getMeetDays().size();
				}
			}
			// Sort after argument
			else if (argument instanceof InPersonCourse || argument instanceof HybridCourse) {
				return 1;
			}
		}
		// Same ordering
		return 0;
	}
	
}
