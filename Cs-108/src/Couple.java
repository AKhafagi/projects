/**
 * Program #7, Anas Khafagi, Masc1916. 
 * Creates a generic Couple object that has 2 generic fields
 * CS108-2 04-21-15
 * @author Anas khafagi
 */
public class Couple<T> {
	private T first;
	private T second;

	/**
	 * constructs a Couple object
	 */
	public Couple() {
		this.first = null;
		this.second = null;
	}

	/**
	 * Constructs a Couple Object with user prefence parameter
	 * @param f first string of Couple 
	 * @param s Second String of Couple
	 */
	public Couple(T f, T s) {
		this.first = f;
		this.second = s;
	}
/**
 * Gets first variable from type T
 * @return first: First variable
 */
	public T getFirst() {
		return first;
	}
/**
 * Sets first variable from type T
 * @param first
 */
	public void setFirst(T first) {
		this.first = first;
	}
/**
 * Sets second variable from type T
 * @return second: Second variable
 */
	public T getSecond() {
		return second;
	}
/**
 * Sets second variable from type T
 * @param second: Second variable from type T
 */
	public void setSecond(T second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return ("(" + first + ", " + second + ")");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object otherObj){ 
		boolean isEqual = false; 
		Couple<T> o = null; 
		if (this.getClass() == otherObj.getClass()) { 
			o = (Couple<T>) otherObj; 
			isEqual = (this.first.equals(o.first) && (this.second.equals(o.second))); } 
		return isEqual;
		}
	
}
