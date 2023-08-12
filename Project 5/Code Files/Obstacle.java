/**
 * The Obstacle class represents a single cell of the field that is specifically an obstacle.
 * @author Sivalee Intachit
 *
 */
public class Obstacle extends Block {
	private String label;
	
	public Obstacle(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return label;
	}
	
	/**
	 * @return an integer representing 0 points (impossible to go through an obstacle)
	 */
	public int getValue() {
		return 0;
	}
}
	