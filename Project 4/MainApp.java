

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainApp {

	public static void main(String[] args) throws Exception {
		{// Test Case 1
			try {
				MeetDay meetDay = MeetDay.Monday;
			} catch (Exception e) {
				System.out.println("Test failed: MeetDay meetDay = MeetDay.Monday;");
			}
		}

		{// Test Case 2
			try {
			Set<String> levels = new HashSet<String>();
			levels.add("Undergraduate");
			levels.add("Graduate");

			Set<MeetDay> meetDays = new HashSet<MeetDay>();
			meetDays.add(MeetDay.Friday);
			meetDays.add(MeetDay.Monday);

			List<String> gtas = new ArrayList<String>();
			gtas.add("Peter");
			gtas.add("Rose");

			Course c1 = new InPersonCourse("CRN1", "OOP12345678910111213", levels, "Mr John1", 5, meetDays, gtas);
			Course c2 = new InPersonCourse("CRN2", "Intro to OOP12345678910111213", levels, "Mr John2", 5, meetDays, gtas);

			System.out.println(c1);
			System.out.println(c2);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		{// Test Case 3
			try {
			Set<String> levels = new HashSet<String>();
			levels.add("Undergraduate");
			levels.add("Graduate");

			Set<MeetDay> meetDays = new HashSet<MeetDay>();
			meetDays.add(MeetDay.Monday);
			meetDays.add(MeetDay.Friday);

			List<String> gtas = new ArrayList<String>();
			gtas.add("Peter");
			gtas.add("Rose");

			Course c1 = new InPersonCourse("CRN1", "OOP12345678910111213", levels, "Mr John1", 5, meetDays, gtas);
			Course c2 = new InPersonCourse("CRN2", "Intro to OOP12345678910111213", levels, "Mr John2", 5, meetDays, gtas);
			Course c3 = new OnlineCourse("CRN3", "Algorithms12345678910111213", levels, "Mr John3", 5, meetDays, gtas);
			Course c4 = new HybridCourse("CRN4", "Data Structure12345678910111213", levels, "Mr John4", 5, meetDays, gtas);
			Course c5 = new HybridCourse("CRN5", "Data Analysis12345678910111213", levels, "Mr John5", 5, meetDays, gtas);

			Collection<String> searchTerms = new ArrayList<String>();
			searchTerms.add("Rose2");
			searchTerms.add("OOP");

			CourseSearcher searcher = new CourseSearcher(searchTerms);
			CourseCatalog courseCatalog = new CourseCatalog();
			courseCatalog.add("CRN1", c1);
			courseCatalog.add("CRN2", c2);
			courseCatalog.add("CRN3", c3);
			courseCatalog.add("CRN4", c4);
			courseCatalog.add("CRN5", c5);

			List<AvailableCourse<String, Course>> result = courseCatalog.search(searcher);

			System.out.println(Arrays.deepToString(result.toArray()));

			for (AvailableCourse<String, Course> item : result) {
				System.out.println("CRN = " + item.getValue().getCrn());
				System.out.println("Course = " + item.getValue().toString());

			}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}
}