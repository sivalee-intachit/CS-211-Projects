/**
 * Passage represents a single cell of the field that is specifically a passage.
 * @author Sivalee Intachit
 *
 */
public class Passage extends Block {
	private int value;
	
	public Passage(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	/**
	 * @return an integer representing the number of points assigned
	 */
	public int getValue() {
		return value;
	}

}
