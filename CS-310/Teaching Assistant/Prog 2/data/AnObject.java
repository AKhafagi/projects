/**
 * 
 */
package data;

/**
 * This is a really simple object that has an integer and a string and 
 * we use them to create something that no one has seen before so
 * you can test your data structures!
 * 
 * @author Rob Edwards
 */
public class AnObject implements Comparable<AnObject>{

	/**
	 * This is a generic object that just has some generic data. We can use it for
	 * testing generic code
	 */

	// two variables
	public String aString;
	public Integer anInt;

	// these are constructors that allow us to call AnObject with zero or more
	// variables and in any order.
	
	public AnObject() {}
	public AnObject(Integer i) {
		this.anInt = i;
		this.aString =  "";
	}
	
	
	public AnObject(String s) {
		this.aString = s;
		this.anInt = 0;
	}
	
	public AnObject(Integer i, String s) {
		this.aString = s;
		this.anInt = i;
	}

	public AnObject(String s, Integer i) {
		this.aString = s;
		this.anInt = i;
	}

	
	// these are the setter and getter methods
	
	/**
	 * Just a string
	 * @return the aString
	 */
	public String getString() {
		return aString;
	}
	/**
	 * @param aString the aString to set
	 */
	public void setString(String aString) {
		this.aString = aString;
	}
	/**
	 * Just an Int
	 * @return the anInt
	 */
	public int getInt() {
		return anInt;
	}
	/**
	 * @param anInt the anInt to set
	 */
	public void setInt(int anInt) {
		this.anInt = anInt;
	}
	
	
	// things we should override from Object


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return ((((AnObject)o).getInt() == this.getInt()) && (((AnObject)o).getString() == this.getString())); 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.anInt.hashCode() + this.aString.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Int: " + this.anInt + " and String " + this.aString;
	}
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AnObject o) {
		int x = o.anInt.compareTo(this.anInt);
		int y = o.aString.compareTo(this.aString);
		if (x == 0 && y== 0)
			return 0;
		// this catches the odd case where x = -y so x+y == 0!
		if (x == -y)
			return x-y;
		return x+y;
	}
	

}
