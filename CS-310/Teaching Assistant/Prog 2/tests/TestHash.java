package tests;

import java.util.Iterator;

import data.AnObject;
import data_structures.Hash;
import data_structures.HashI;

public class TestHash {

	public static void main(String[] args) {
		
		System.out.println("TESTING THE HASH");
		HashI<AnObject, Integer> hash = new Hash<AnObject, Integer>(64);

		// generate 10 things and add them to the hash. This should not require
		// resizing (hopefully!)

		for (int i=65; i<75; i++) {
			AnObject o = new AnObject(i, Character.toString((char)i));
			hash.add(o, i);
		}


		System.out.print("\tall keys: ");
		// get all the keys
		try {
			for (AnObject ao : hash)
				System.out.print(ao.anInt + " ");
			System.out.println();
		} catch (ClassCastException e) {
			System.out.println("\nERROR: The iterator should return keys :" + e.getMessage());
		}
		catch (Exception e) {
			System.out.println("\nERROR: There was an error iterating through all the keys: " + e.getMessage());
		}

		System.out.println("\ttesting size and load factor");
		// test the size
		int s = hash.size();
		if (s != 10)
			System.out.println("ERROR: The size should be 10 but is " + s);
		// test the number of elements
		double l = hash.loadFactor();
		// currently should be 10/64 = 0.2
		if (l != 0.15625)
			System.out.println("ERROR: The load factor should be 0.15625 but is " + l);

		// add another 10 things and check these values again
		for (int i=75; i<85; i++) {
			AnObject o = new AnObject(i, Character.toString((char)i));
			hash.add(o, i);
		}
		// test the size
		s = hash.size();
		if (s != 20)
			System.out.println("ERROR: The size should be 20 but is " + s);
		// test the number of elements
		l = hash.loadFactor();
		// currently should be 20/64 = 0.3125
		if (l != 0.3125)
			System.out.println("ERROR: The load factor should be 0.3125 but is " + l);


		// test getting a value for some keys
		System.out.println("\ttesting getting some values for a few keys");
		try {
			for (int i : new Integer[]{65, 68, 73, 78, 82}) {
				AnObject ao = new AnObject(i, Character.toString((char)i));
				Integer v = hash.getValue(ao);

				if (v == null || v != i)
					System.out.println("ERROR: Tried to retrieve a value for " + i + " but got " + v);
			}
		}
		catch (ClassCastException e) {
			System.out.println("\nERROR: The iterator should return keys :" + e.getMessage());
		}

		// test removing 5 things
		System.out.println("\ttesting removing a few keys");
		try {
			for (int i : new Integer[]{65, 68, 73, 78, 82}) {
				AnObject ao = new AnObject(i, Character.toString((char)i));
				boolean b = hash.remove(ao);
				if (!b)
					System.out.println("ERROR: Tried to remove an object for " + i + " but failed");
			}
		}
		catch (ClassCastException e) {
			System.out.println("\nERROR: The iterator should return keys :" + e.getMessage());
		}


		// the size should be different!
		s = hash.size();
		if (s != 15)
			System.out.println("ERROR: The size should be 15 but is " + s);
		// test the number of elements
		l = hash.loadFactor();
		// currently should be 20/64 = 0.3125
		if (l != 0.234375)
			System.out.println("ERROR: The load factor should be 15/64 0.234375 but is " + l);

		// these keys should not exist
		try {
			for (int i : new Integer[]{65, 68, 73, 78, 82}) {
				AnObject ao = new AnObject(i, Character.toString((char)i));
				Integer v = hash.getValue(ao);
				if (v != null)
					System.out.println("ERROR: Deleted object at " + i + " but got a vlaue for it: " + v);
			}
		}
		catch (ClassCastException e) {
			System.out.println("\nERROR: The iterator should return keys but had a class cast exception:" + e.getMessage());
		}
		catch (NullPointerException e) {
			System.out.println("\nERROR: The iterator should return keys but had a null pointer exception :" + e.getMessage());
		}


		// make empty
		hash = new Hash<AnObject, Integer>(16);


		System.out.println("\ttesting the resize method");
		// now lets force a resize
		try {
			for (int i = 0; i<256; i++) {
				AnObject o = new AnObject(i);
				hash.add(o, i);
			}
		}
		catch (ClassCastException e) {
			System.out.println("\nERROR: The iterator should return keys :" + e.getMessage());
		}

		s = hash.size();
		if (s != 256)
			System.out.println("ERROR: The size should be 256 but is " + s);
		// test the number of elements
		l = hash.loadFactor();
		// currently should be 20/64 = 0.3125
		if (l != 0.5)
			System.out.println("ERROR: The load factor should be 0.5 but is " + l);

		System.out.println("\ttesting the change value method");
		hash.changeValue(new AnObject(65), 265);
		if (hash.getValue(new AnObject(65)) != 265)
			System.out.println("ERROR: Tried to change the value of key 65, but failed");
		
		if (hash.changeValue(new AnObject("This does not exist"), 7))
			System.out.println("ERROR: Tried to change the value of an object not in the hash, and it said we succeeded!");
		
		System.out.println("END TESTING THE HASH\n");

	}

}
