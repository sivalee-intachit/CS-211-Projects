import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FieldIterator is a class that provides an iterator for the Field class.
 * @author Sivalee Intachit
 * @param <T>
 */
public class FieldIterator<T> implements Iterator<T> {
	private Field<T> field;
	private String blockType;
	
	/**
	 * Constructor that instantiates private fields
	 * @param field
	 * @param iterableObjectName
	 */
	public FieldIterator(Field field, String iterableObjectName) {
		this.field = field;
		if (iterableObjectName == "Passage") {
			this.blockType = iterableObjectName;
		}
		else if (iterableObjectName == "Obstacle") {
			this.blockType = iterableObjectName;
		}
	}
	
	/**
	 * currRow serves as a tracker for the current row index.
	 */
	private int currRow = 0;
	/**
	 * currCol serves as a tracker for the current column index.
	 */
	private int currCol = 0;
	
	@Override
	public T next() throws NoSuchElementException {
		if (currCol == field.getWidth() - 1 && currRow < field.getHeight() - 1) {
			T element = (T) field.getElement(currRow++, currCol);
			currCol = 0;
			return element;
		}
		else {
			return (T) field.getElement(currRow, currCol++);
		}
	}
	
	@Override
	public boolean hasNext() {
		for (int i = currRow; i < field.getHeight(); i++) {
			for (int j = currCol; j < field.getWidth(); j++) {
				if (field.getElement(i, j).getClass().getName().equals(blockType)) {
					currRow = i;
					currCol = j;
					return true;
				}
			}
			currCol = 0;
		}
		return false;
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
}
