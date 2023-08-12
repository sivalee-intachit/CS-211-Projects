import java.util.Iterator;

/**
 * FlexibleIterable is an interface that extends the Iterable interface.
 * @author Sivalee Intachit
 * @param <T>
 *
 */
public interface FlexibleIterable<T> extends Iterable<T> {
	public Iterator<T> iterator(String iterableObjectName);
}
