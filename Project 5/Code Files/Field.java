import java.util.ArrayList;
import java.util.Iterator;

/**
 * Field is a class that represents a field.
 * @author Sivalee Intachit
 *
 */
public class Field<T> implements FlexibleIterable<T> {
	private T[][] matrix;
	private int height;
	private int width;
	
	/**
	 * Constructor that instantiates the private fields. Creates a new matrix based on given dimensions
	 * @param height
	 * @param width
	 */
	public Field(int height, int width) {
		this.height = height;
		this.width = width;
		matrix = (T[][]) new Object[height][width]; 
	}
	
	public T getElement(int row, int col) {
		return matrix[row][col];
	}
	public void setElement(int row, int col, T el) {
		matrix[row][col] = el;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
	@Override
	public String toString() {
		String matrixString = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j == matrix[i].length - 1) {
					matrixString += matrix[i][j] + "\n";
				}
				else {
					matrixString += matrix[i][j] + " ";
				}
			}
		}
		return matrixString;
	}
	
	@Override
	public Iterator<T> iterator() {
		Iterator<T> iter = new Iterator<T>() {
			private int currRow = 0;
			private int currCol = 0;
			
			@Override
			public boolean hasNext() {
				if (currRow < height && currCol < width) {
					return true;
				}
				return false;
			}

			@Override
			public T next() {
				if (currCol == width - 1 && currRow < height - 1) {
					T element = matrix[currRow++][currCol];
					currCol = 0;
					return element;
				}
				else {
					return matrix[currRow][currCol++];
				}
			}
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return iter;
	}
	/**
	 * Overloaded iterator method which instantiates a new FieldIterator.
	 * FieldIterator iterates over certain block types based on given parameter.
	 * @return new iterator
	 */
	@Override
	public Iterator<T> iterator(String iterableObjectName) {
		// TODO Auto-generated method stub
		FieldIterator<T> fieldIterator = new FieldIterator<T>(this, iterableObjectName);
		return fieldIterator;
	}
}
