package edu.sdsu.cs.prog2;

import java.io.IOException;
import java.util.Deque;
import java.util.Iterator;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import edu.sdsu.cs.datastructures.LinkedList;
import edu.sdsu.cs.datastructures.SlowDeque;

public class DequeDriver {
	protected Deque<Integer> deque;
	protected Logger myLogger;
	protected static FileHandler fileTxt;
	protected static SimpleFormatter formatterTxt;
	protected long startTime;
	protected long endTime;
	protected double duration;
	protected Iterator<Integer> it;
	
	public DequeDriver(){
		try {
			initLogger();
			myLogger.info("Now using " + "\"" + myLogger.getName() + "\"" + " log ");
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	protected void initLinkedListDeque() {
		deque = new LinkedList<Integer>();
		it = deque.iterator();
		myLogger.info("running Tests with LinkedList Deque");
		runtests();
	}

	protected void initVectorDeque() {
		deque = new SlowDeque<Integer>();
		it = deque.iterator();
		myLogger.info("running Tests with Vector Deque");
		runtests();
	}

	protected void initLogger() throws SecurityException, IOException {
		this.myLogger = Logger.getLogger("DequeDriver Logger");
		myLogger.setUseParentHandlers(false);
		fileTxt = new FileHandler("Deque Driver.xml");
		myLogger.setLevel(Level.INFO);
		myLogger.addHandler(fileTxt);

	}

	protected void runtests() {
		int n = 1000;
		myLogger.info("running Tests with " + "\"" + n + "\"" + " elements");
		addTests(deque, n);
		contains(deque, 500);
		removeTests(deque, n);
		n = 100000;
		myLogger.info("running Tests with " + "\"" + n + "\"" + " elements");
		addTests(deque, n);
		contains(deque, 50000);
		removeTests(deque, n);
		n = 500000;
		myLogger.info("running Tests with " + "\"" + n + "\"" + " elements");
		addTests(deque, n);
		contains(deque, 500000);
		removeTests(deque, n);
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

	void addTests(Deque<Integer> sut, int size) {
		startTime = System.nanoTime();
		myLogger.info("adding " + "\"" + size + "\"" + " elements to the Deque");
		for (int i = 0; i < size; i++) {
			sut.addFirst(i);
		}
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info(
				"adding " + "\"" + size + "\"" + " elements to the Deque took " + "\"" + duration + "\"" + " seconds");

	}

	void removeTests(Deque<Integer> sut, int size) {
		startTime = System.nanoTime();
		myLogger.info("removing " + "\"" + size + "\"" + " elements from the Deque");
		while (it.hasNext()) {
			sut.removeFirst();
		}
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info("removing " + "\"" + size + "\"" + " elements from the Deque took " + "\"" + duration + "\""
				+ " seconds");

	}

	void disableConsoleLogger() {
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}
	}

	public static void main(String[] args) {
		try {
			DequeDriver driver = new DequeDriver();
			driver.initLinkedListDeque();
			driver.initVectorDeque();

		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
