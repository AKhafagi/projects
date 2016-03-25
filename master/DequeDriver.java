package edu.sdsu.cs.prog2;

import java.io.IOException;
import java.util.Deque;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.sdsu.cs.datastructures.LinkedList;
import edu.sdsu.cs.datastructures.SlowDeque;

public class DequeDriver {
	protected Deque<Integer> deque;
	protected Logger myLogger;
	protected static FileHandler fileTxt;
	protected long startTime;
	protected long endTime;
	protected double duration;
	protected Iterator<Integer> it;

	public DequeDriver() {
		try {
			initLogger();
			myLogger.info("Now using " + "\"" + myLogger.getName() + "\"" + " log ");
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	private void initLinkedListDeque() {
		deque = new LinkedList<Integer>();
		it = deque.iterator();
		myLogger.info("running Tests with LinkedList Deque");
		runtests();
	}

	private void initVectorDeque() {
		deque = new SlowDeque<Integer>();
		it = deque.iterator();
		myLogger.info("running Tests with Vector Deque");
		runtests();
	}

	private void initLogger() throws SecurityException, IOException {
		this.myLogger = Logger.getLogger("DequeDriver Logger");
		myLogger.setUseParentHandlers(false);
		fileTxt = new FileHandler("Deque Driver.xml");
		myLogger.setLevel(Level.INFO);
		myLogger.addHandler(fileTxt);

	}

	private void runtests() {
		int n = 1000;
		myLogger.info("running Tests with " + "\"" + n + "\"" + " elements");
		addTests(deque, n);
		contains(deque, 2000);
		removeTests(deque, n);
		lastOccurenceOf(deque, 50);
		n = 100000;
		myLogger.info("running Tests with " + "\"" + n + "\"" + " elements");
		addTests(deque, n);
		contains(deque, -1);
		removeTests(deque, n);
		lastOccurenceOf(deque, -1);
		n = 300000;
		myLogger.info("running Tests with " + "\"" + n + "\"" + " elements");
		addTests(deque, n);
		contains(deque, 100);
		removeTests(deque, n);
		lastOccurenceOf(deque, 92938238);
	}

	private void contains(Deque<Integer> sut, Integer element) {
		startTime = System.nanoTime();
		myLogger.info("searching for the element  " + "\"" + element + "\"" + " in the deque of size " + "\""
				+ deque.size() + "\".");
		sut.contains(element);
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info("searching for the element " + "\"" + element + "\"" + " in the Deque took " + "\"" + duration
				+ "\"" + " seconds");

	}

	private void addTests(Deque<Integer> sut, int size) {
		startTime = System.nanoTime();
		myLogger.info("adding " + "\"" + size + "\"" + " elements to the head of the Deque");
		for (int i = 0; i < size; i++) {
			sut.addFirst(i);
		}
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info("adding " + "\"" + size + "\"" + " elements to the head of  Deque took " + "\"" + duration + "\""
				+ " seconds");

	}

	private void removeTests(Deque<Integer> sut, int size) {
		startTime = System.nanoTime();
		myLogger.info("removing " + "\"" + size + "\"" + " elements from the head of the Deque");
		while (it.hasNext()) {
			sut.removeFirst();
		}
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info("removing " + "\"" + size + "\"" + " elements from the the head of the Deque took " + "\""
				+ duration + "\"" + " seconds");

	}

	private void lastOccurenceOf(Deque<Integer> sut, int element) {
		startTime = System.nanoTime();
		myLogger.info("removing the last occurence of  " + "\"" + element + "\"" + " from  the Deque");
		sut.removeLastOccurrence(element);
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info("removing the last occurence of  " + "\"" + element + "\"" + " from the Deque" + duration + "\""
				+ " seconds");

	}

	private void conclusion() {
		myLogger.info(
				"This shows that the slow deque is better in searching (as it uses the Binary search Algorithm), and traversing");
		myLogger.info("the linkedList is alot better at adding and removing at & from the begining of the list.");
		myLogger.info("This agrees with the design of the LinkedList over the Vector");
		myLogger.info("the LinkedList is better in adding and removing.");
		myLogger.info("However,the Vector is better at index access and middle of the list modifications");
	}

	public static void main(String[] args) {
		try {
			DequeDriver driver = new DequeDriver();
			driver.initLinkedListDeque();
			driver.initVectorDeque();
			driver.conclusion();

		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
