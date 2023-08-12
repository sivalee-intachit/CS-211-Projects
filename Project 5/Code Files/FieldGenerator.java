import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * FieldGenerator is a utility class that provides two methods for generating a Field.
 * @author Sivalee Intachit
 *
 */
public class FieldGenerator {
	/**
	 * Method that generates a Field based on the data that is stored in a text file.
	 * @param filename
	 * @return a new Field
	 * @throws FileNotFoundException
	 */
	public static Field<Block> loadDataFromFile(String filename) {
		File file = new File(filename);
		Field<Block> field = new Field<>(0, 0);
		
		try {
			// Retrieve the dimensions of the field
			int rowCount = 0;
			int colCount = 0;
			Scanner rowCounter = new Scanner(file);
			while(rowCounter.hasNextLine()) {
				rowCount++;
				rowCounter.nextLine();
			}
			rowCounter.close();
			Scanner colCounter = new Scanner(file);
			colCount = colCounter.nextLine().split(" ").length;		
			colCounter.close();
			
			// Instantiate the field using the dimensions and populate the elements according to the file
			field = new Field<>(rowCount, colCount);
			Scanner scnr = new Scanner(file);
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					String element = scnr.next();
					try {
						field.setElement(i, j, new Passage(Integer.parseInt(element)));
					}
					catch (Exception e) {
						field.setElement(i, j, new Obstacle(element));
					}
				}
			}
			scnr.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("File not found");
			System.exit(0);
		}
		
		return field;
	}
	/**
	 * Method that generates a Field using random integers.
	 * @param h is the height of the Field
	 * @param w is the width of the Field
	 * @param l is the lowest random number of points that a Passage can have
	 * @param m is the largest random number of points that a Passage can have
	 * @param n is the number of Obstacle objects in the Field
	 * @return a new Field
	 */
	public static Field<Block> randomIntegers(int h, int w, int l, int m, int n) {
		// Instantiate new field using height and width parameters
		Field<Block> field = new Field<>(h, w);
		
		// Populate field with passages of random values
		Random random = new Random();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				field.setElement(i, j, new Passage(random.nextInt(m - l + 1) + l));
			}
		}
		
		// Populate field with randomly placed obstacles
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < field.getHeight() * field.getWidth(); j++) {
				int row = random.nextInt(h - 1);
				int col = random.nextInt(w - 1);
				// Check if the randomly assigned index is already set to an obstacle
				if (field.getElement(row, col).getClass().getName().equals("Obstacle")) {
					continue;
				}
				else {
					field.setElement(row, col, new Obstacle("-"));
					break;
				}
			}
		}
		return field;
	}
}
