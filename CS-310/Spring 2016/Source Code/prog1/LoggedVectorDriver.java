/**
 * LoggedVectorDriver Class and nested GreaterThanEqual class.
 * LoggedVectorDriver: Driver class to test the LoggedVector Class.
 * GreaterThanEqual: overrides the compare method of the Comparator to change 
 * the condition to if element is greater than or equal return 0 otherwise return -1.
 * Monday Febuary 22nd, 2016
 * Computer Science 310
 * San Diego State University
 * @author Anas Khafagi
 */
package edu.sdsu.cs.prog1;

import edu.sdsu.cs.datastructures.LoggedVector;

import java.io.IOException;
import java.util.Comparator;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggedVectorDriver {

	private class GreaterThanEqual implements Comparator<Integer> {

		@Override
		public int compare(Integer compareVal, Integer element) {
			return compareVal <= element ? 0 : -1;
		}
	}

	final Logger myLogger = Logger.getLogger("edu.sdsu.cs.datastructures.LoggedVector");

	static FileHandler fileTxt;
	static SimpleFormatter formatterTxt;

	LoggedVector<Integer> integerVector = new LoggedVector<Integer>(15, 7);

	private LoggedVectorDriver() throws SecurityException, IOException {
		initLogger();
		integerVector.useLog(myLogger);

		myLogger.info("Running with Anonymous comparator . . . ");
		integerVector.addTrigger(new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return (arg0.equals(arg1) ? 0 : 1);
			}
		}, new Integer(5));

		runTests(integerVector);
		integerVector.clearTriggers();

		myLogger.info("Running with greaterThan comparator . . . ");
		integerVector.addTrigger(new GreaterThanEqual(), new Integer(3));
		runTests(integerVector);

		System.out.println("Writing to the Log complete");
	}

	void runTests(LoggedVector<Integer> sut) {
		sut.add(1);
		sut.add(5);
		sut.add(3);
		sut.add(3, 4);
		sut.add(5);
		sut.add(6);
		sut.add(8);
		sut.add(34);
		sut.add(45);
		sut.set(0, 5);
		sut.get(0);
		sut.get(1);
		sut.get(5);
		sut.remove(0);
		sut.remove(5);
		sut.remove(6);
		sut.remove(5);
		sut.remove(4);
		sut.clear();
		sut.add(0, 1);
		sut.add(6);
		sut.get(0);
		sut.remove(0);

	}

	void disableConsoleLogger() {
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}
	}

	void initLogger() throws SecurityException, IOException {

		disableConsoleLogger();

		fileTxt = new FileHandler("Logging1.xml");
		myLogger.setLevel(Level.INFO);
		myLogger.addHandler(fileTxt);
	}

	/**
	 * Quickly creates a new instance of this class.
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {
		
		try {
			new LoggedVectorDriver();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
