package edu.sdsu.cs.extracredit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.RandomAccess;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.sdsu.cs.datastructures.LinkedList;
import edu.sdsu.cs.datastructures.ListPriorityQueue;

public class PriorityDriver {
	protected ListPriorityQueue<Integer> queue;
	protected Logger myLogger;
	protected static FileHandler fileTxt;
	protected long startTime;
	protected long endTime;
	protected double duration;
	protected Iterator<Integer> it;
	Random rand = new Random();

	public PriorityDriver() {
		try {
			initLogger();
			myLogger.info("Now using " + "\"" + myLogger.getName() + "\"" + " log ");
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		initpriorityQueue();
	}

	private void initpriorityQueue() {
		queue = new ListPriorityQueue<Integer>();
		it = queue.iterator();
		if ((queue.getBaseQueue()) instanceof RandomAccess) {
			myLogger.info("Running Tests with ArrayList Priority Queue");
			System.out.println("Running Tests with ArrayList Priority Queue");
		} else {
			myLogger.info("Running Tests with LinkedList priority queue");
			System.out.println("Running Tests with LinkedList Priority Queue");
		}
		runtests();
		if ((queue.getBaseQueue()) instanceof RandomAccess) {
			System.out.println("Done running tests with the ArrayList Priority Queue !!!");
			myLogger.info("Done running tests with the ArrayList Priority Queue !!!");
		}
		else{
			System.out.println("Done running tests with the LinkedList Priority Queue !!!");
			myLogger.info("Done running tests with the LinkedList Priority Queue !!!");
	}
	}

	private void initLogger() throws SecurityException, IOException {
		this.myLogger = Logger.getLogger("Priority Queue Logger");
		myLogger.setUseParentHandlers(false);
		fileTxt = new FileHandler("PriorityQueue.xml");
		myLogger.setLevel(Level.INFO);
		myLogger.addHandler(fileTxt);

	}

	private void runtests() {
		int n = 10000;
		myLogger.info("Running Tests with " + "\"" + n + "\"" + " elements");
		offerTests(queue, n);
		contains(queue, rand.nextInt(n)+n >>1);
		peekTests(queue);
		pollTests(queue, n);
		n = 20000;
		myLogger.info("Running Tests with " + "\"" + n + "\"" + " elements");
		offerTests(queue, n);
		contains(queue, rand.nextInt(n)+n >>1);
		peekTests(queue);
		pollTests(queue, n);
		n = 40000;
		myLogger.info("Running Tests with " + "\"" + n + "\"" + " elements");
		offerTests(queue, n);
		contains(queue, rand.nextInt(n)+n >>1);
		peekTests(queue);
		pollTests(queue, n);
		n = 80000;
		myLogger.info("Running Tests with " + "\"" + n + "\"" + " elements");
		offerTests(queue, n);
		contains(queue, rand.nextInt(n)+n >>1);
		peekTests(queue);
		pollTests(queue, n);
	}

	private void contains(ListPriorityQueue<Integer> sut, Integer element) {
		startTime = System.nanoTime();
		myLogger.info("Searching for the element  " + "\"" + element + "\"" + " in the Priority Queue of size " + "\""
				+ queue.size() + "\".");
		sut.contains(element);
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info("Searching for the element " + "\"" + element + "\"" + " in the Priority Queue took " + "\""
				+ duration + "\"" + " seconds");

	}

	private void offerTests(ListPriorityQueue<Integer> sut, int size) {
		startTime = System.nanoTime();
		myLogger.info("offering " + "\"" + size + "\"" + " elements to the Priority Queue");
		for (int i = 0; i < size; i++) {
			sut.offer(rand.nextInt(size) + 1);
		}
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info("offering " + "\"" + size + "\"" + " elements to the the Priority Queue took " + "\"" + duration
				+ "\"" + " seconds");

	}

	private void pollTests(ListPriorityQueue<Integer> sut, int size) {
		startTime = System.nanoTime();
		myLogger.info("polling " + "\"" + size + "\"" + " elements from the Priority Queue");
		while (it.hasNext()) {
			sut.poll();
		}
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info("polling " + "\"" + size + "\"" + " elements from the Priority Queue took " + "\"" + duration
				+ "\"" + " seconds");

	}

	private void peekTests(ListPriorityQueue<Integer> sut) {
		startTime = System.nanoTime();
		myLogger.info("Peeking the first element in the Priority Queue.");
		sut.peek();
		endTime = System.nanoTime();
		duration = ((endTime - startTime) * Math.pow(10, -9));
		myLogger.info("Peeking the first element in the Priority Queue "  +  "\"" + duration + "\""
				+ " seconds.");

	}
	public static void main(String[] args) {
		try {
			new PriorityDriver();

		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
