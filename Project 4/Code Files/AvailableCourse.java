/**
 * AvailableCourse is a generic class that represents an available course.
 * @author Sivalee Intachit
 *
 * @param <K> represents an object of any type
 * @param <V> represents an object of any type, implements the Comparable interface
 */
public class AvailableCourse <K, V extends Comparable<V>> implements Comparable<AvailableCourse <K, V>> {
	private K key;
	private V value;
	
	// Getters
	public final K getKey() {
		return key;
	}
	public final V getValue() {
		return value;
	}
	
	/**
	 * Constructor instantiating all private fields
	 * @param key represents an object of any type
	 * @param value represents an object of any type
	 */
	public AvailableCourse(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Overrides the parent method. Compares the current AvailableCourse instance to another object.
	 * @return a boolean value representing whether the two objects equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AvailableCourse) {
			if(value == ((AvailableCourse<K, V>) obj).value) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Returns the result of comparing the value field
	 * @param otherCourse represents the course that the current instance is being compared to
	 * @return integer value representing the order of the current instance
	 */
	@Override
	public int compareTo(AvailableCourse<K, V> otherCourse) {
		return value.compareTo(otherCourse.value);
	}

}
