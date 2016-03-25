/**
 * LoggedVector Class 
 * Extends the Vector class to create a Logggable Vector.
 * monday Febuary 22nd, 2016
 * Computer Science 310
 * San Diego State University
 * @author Anas Khafagi
 */


import java.util.Collection;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * @param <E>
 *            The data type to be added to the Loggable Vector.
 */
public class LoggedVector<E> extends Vector<E> implements ILoggable<E> {

	protected Logger myLogger;
	protected Comparator<E> trigger;
	protected E CompareVal;

	/**
	 * Constructs a LoggedVector so that its internal data array has size 10 and
	 * its standard capacity increment is zero. gets the class Logger to log to
	 * intializes memory for <code> value </code>
	 */
	@SuppressWarnings("unchecked")
	public LoggedVector() {
		super();
		this.CompareVal = (E) new Object();
		this.myLogger = Logger.getLogger("edu.sdsu.cs.datastructures.LoggedVector");
		myLogger.setUseParentHandlers(false);
		myLogger.info("Created a new Logged Vector");
	}

	/**
	 * Constructs a vector containing the elements of the specified collection,
	 * in the order they are returned by the collection's iterator. gets the
	 * class Logger to log to intializes memory for <code> value </code>
	 * 
	 * @param c
	 *            the collection whose elements are to be placed into this
	 *            vector
	 * @throws NullPointerException
	 *             - if the specified collection is null
	 */
	@SuppressWarnings("unchecked")
	public LoggedVector(Collection<? extends E> c) {
		super(c);
		this.CompareVal = (E) new Object();
		this.myLogger = Logger.getLogger("edu.sdsu.cs.datastructures.LoggedVector");
		myLogger.setUseParentHandlers(false);
		myLogger.info("Created a new Logged Vector");
	}

	/**
	 * Constructs a LoggedVector with size <code> IntialCapacit </code> and
	 * <code> capacityIncrement </code> blockSize. gets the class Logger to log
	 * to intializes memory for <code> value </code>
	 * 
	 * @param intialCapacity
	 *            the initial capacity of the vector.
	 * @param blockSize
	 *            the amount by which the capacity is increased when the vector
	 *            overflows.
	 * @throws IllegalArgumentException
	 *             - if the specified initial capacity is negative
	 */
	@SuppressWarnings("unchecked")
	public LoggedVector(int initialCapacity, int blockSize) {
		super(initialCapacity, blockSize);
		this.CompareVal = (E) new Object();
		this.myLogger = Logger.getLogger("edu.sdsu.cs.datastructures.LoggedVector");
		myLogger.setUseParentHandlers(false);
		myLogger.info("Created a new Logged Vector");
	}

	@Override
	public boolean addTrigger(Comparator<E> trigger, E value) {
		if (!myLogger.equals(null)) {
			this.CompareVal = value;
			this.trigger = trigger;
			myLogger.info("Added new trigger " + " of value " + "\"" + value + "\"" + " to the Log ");
			return true;
		}
		return false;
	}

	@Override
	public void clearTriggers() {
		if (!trigger.equals(null)) {
			CompareVal = null;
			trigger = null;
		}
		myLogger.info("Removed all triggers from the Log ");

	}

	@Override
	public Logger useLog(Logger log) {
		if (!myLogger.equals(null)) {
			Logger temp = this.myLogger;
			this.myLogger = log;
			this.myLogger.info("Now using " + "\"" + log.getName() + "\"" + " as the new Log");
			return temp;

		}
		return null;
	}

	@Override
	public void clear() {
		super.clear();
		if (!myLogger.equals(null))
			myLogger.info("Cleared all elements of the Vector");
	}

	@Override
	public boolean add(E element) {
		boolean temp = super.add(element);
		if (isLoggable(element) && !myLogger.equals(null)) {
			myLogger.info("Added the element " + "\"" + element + "\"" + " to the end of the Vector");
		}
		return temp;

	}

	@Override
	public E set(int index, E element) {
		E temp = super.set(index, element);
		if (isLoggable(element) && myLogger.equals(null)) {
			myLogger.info(
					"Set the element at the index " + "\"" + index + "\"" + " to the element " + "\"" + element + "\"");
		}
		return temp;
	}

	@Override
	public void add(int index, E element) {
		super.add(index, element);
		if (isLoggable(element) && !myLogger.equals(null)) {
			myLogger.info("Added the element " + "\"" + element + "\"" + " at the index " + "\"" + index + "\""
					+ " of the Vector");

		}
	}

	@Override
	public E remove(int index) {
		E temp = super.remove(index);
		if (isLoggable(temp) && !myLogger.equals(null)) {
			myLogger.info("Removed the element at the index " + "\"" + index + "\"" + " from the Vector");
		}
		return temp;
	}

	@Override
	public E get(int index) {
		E temp = super.get(index);
		if (isLoggable(temp) && !myLogger.equals(null)) {
			myLogger.info("got the element at the index " + "\"" + index + "\"" + " from the Vector");
		}
		return temp;
	}

	@Override
	protected void contract() {
		if (!myLogger.equals(null))
			myLogger.info("Attempting to contract the Vector to size " + "\"" + this.elementCount + "\"");
		super.contract();
		if (!myLogger.equals(null))
			myLogger.info("The current Vector Size is " + "\"" + this.capacity() + "\"");
	}

	@Override
	protected void expand() {
		if (!myLogger.equals(null)) {
			myLogger.info("Attempting to expand the Vector.");
		}
		super.expand();
		if (!myLogger.equals(null))
			myLogger.info("Current Vector Size is " + "\"" + this.capacity() + "\"");
	}

	/**
	 * checks if there is a Comparator if there is then it checks wether or not
	 * to log the event by comparing <code> element </code> to
	 * <code> value </code> if they are equal then the event should be logged
	 * also checks if there is a Comparator
	 * 
	 * @param element
	 *            the object being compared to <code> value </code>.
	 * @return true - if both <code> element </code> and <code> value </code> 
	 *         are equal. false - if there is no comparator or the objects are
	 *         not equal.
	 */
	protected boolean isLoggable(E element) {
		if (this.trigger.equals(null)) {
			return false;
		}
		return (trigger.compare(CompareVal, element) == 0);
	}
}
