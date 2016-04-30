package edu.sdsu.cs.datastructures;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortDriver {
	private Logger myLog;
	private Long startTime;
	private Long endTime;
	private long duration;
	private Integer[] toSort;
	private Comparator<Integer> comp;
	private static FileHandler fileTxt;

	public SortDriver() throws SecurityException, IOException {
		initLogger();
		toSort = new Integer[4000];
		toSort = intializeArray(toSort);
		comp = Comparator.naturalOrder();
		myLog.info("Running Sorters with arraySize of " + toSort.length);
		shellSortTests(toSort, comp);
		quickSortTests(toSort, comp);
		mergeSortTests(toSort, comp);
		heapSortTests(toSort, comp);
		toSort = new Integer[8000];
		toSort = intializeArray(toSort);
		myLog.info("Running Sorters with arraySize of " + toSort.length);
		shellSortTests(toSort, comp);
		quickSortTests(toSort, comp);
		mergeSortTests(toSort, comp);
		heapSortTests(toSort, comp);
		toSort = new Integer[16000];
		toSort = intializeArray(toSort);
		myLog.info("Running Sorters with arraySize of " + toSort.length);
		shellSortTests(toSort, comp);
		quickSortTests(toSort, comp);
		mergeSortTests(toSort, comp);
		heapSortTests(toSort, comp);
		myLog.info("Done running Sorters tests");
		System.out.println("Done running Sorters tests");
	}

	private void heapSortTests(Integer[] toSort, Comparator<Integer> comp) {
		myLog.info("running Heap Sort on already sorted array");
		myLog.info(print(toSort));
		startTime = System.currentTimeMillis();
		Sorters.heapSort(toSort, comp);
		endTime = System.currentTimeMillis();
		duration = ((endTime - startTime));
		myLog.info("Sorting an already Sorted array of size " + toSort.length + " using heap sort took " + duration + " mili seconds.");
		myLog.info(print(toSort));
		shuffle(toSort, toSort.length / 2);
		myLog.info(print(toSort));
		myLog.info("running Heap Sort on unsorted array");
		startTime = System.currentTimeMillis();
		Sorters.heapSort(toSort, comp);
		endTime = System.currentTimeMillis();
		duration = ((endTime - startTime));
		myLog.info("Sorting an unsorted array of size " + toSort.length + " using heap sort took " + duration + " mili seconds.");
		myLog.info(print(toSort));

	}

	private void mergeSortTests(Integer[] toSort2, Comparator<Integer> comp2) {
		myLog.info("running Merge Sort on sorted array");
		myLog.info(print(toSort));
		startTime = System.currentTimeMillis();
		Sorters.mergeSort(toSort, comp);
		endTime = System.currentTimeMillis();
		duration = ((endTime - startTime));
		myLog.info("Sorting an already Sorted array of size " + toSort.length + " using merge sort took " + duration + " mili seconds.");
		myLog.info(print(toSort));
		shuffle(toSort, toSort.length / 2);
		myLog.info(print(toSort));
		myLog.info("running Merge Sort on unsorted array");
		startTime = System.currentTimeMillis();
		Sorters.mergeSort(toSort, comp);
		endTime = System.currentTimeMillis();
		duration = ((endTime - startTime));
		myLog.info("Sorting an unsorted array of size " + toSort.length + " using merge sort took " + duration + " mili seconds.");
		myLog.info(print(toSort));

	}

	private void quickSortTests(Integer[] toSort2, Comparator<Integer> comp2) {
		myLog.info("running Quick Sort on sorted array");
		myLog.info(print(toSort));
		startTime = System.currentTimeMillis();
		Sorters.quickSort(toSort, comp);
		endTime = System.currentTimeMillis();
		duration = ((endTime - startTime));
		myLog.info("Sorting an already Sorted array of size " + toSort.length + " using quick sort took " + duration + " mili seconds.");
		myLog.info(print(toSort));
		shuffle(toSort, toSort.length / 2);
		myLog.info(print(toSort));
		myLog.info("running Quick Sort on unsorted array");
		startTime = System.currentTimeMillis();
		Sorters.quickSort(toSort, comp);
		endTime = System.currentTimeMillis();
		duration = ((endTime - startTime));
		myLog.info("Sorting an unsorted array of size " + toSort.length + " using quick sort took " + duration + " mili seconds.");
		myLog.info(print(toSort));

	}

	private void shellSortTests(Integer[] toSort2, Comparator<Integer> comp2) {
		myLog.info("running shell Sort on sorted array");
		myLog.info(print(toSort));
		startTime = System.currentTimeMillis();
		Sorters.shellSort(toSort, comp);
		endTime = System.currentTimeMillis();
		duration = ((endTime - startTime));
		myLog.info("Sorting an already sorted array of size " + toSort.length + " using shell sort took " + duration + " mili seconds");
		myLog.info(print(toSort));
		shuffle(toSort, toSort.length / 2);
		myLog.info(print(toSort));
		myLog.info("running shell Sort on unSorted array");
		startTime = System.currentTimeMillis();
		Sorters.shellSort(toSort, comp);
		endTime = System.currentTimeMillis();
		duration = ((endTime - startTime));
		myLog.info("Sorting an unsorted array of size " + toSort.length + " using shell sort took " + duration + " mili seconds");
		myLog.info(print(toSort));

	}

	private void initLogger() throws SecurityException, IOException {
		this.myLog = Logger.getLogger("DequeDriver Logger");
		myLog.setUseParentHandlers(false);
		fileTxt = new FileHandler("SortDriver.xml");
		myLog.setLevel(Level.INFO);
		myLog.addHandler(fileTxt);
		myLog.info("Now Logging to " + myLog);
	}

	public void shuffle(Integer[] a, int num) {
		myLog.info("shuffling array " + num + " times.");
		Integer cardTemp;
		for (int i = 0; i < num; i++) {
			int random1 = (int) (Math.random() * a.length);
			int random2 = (int) (Math.random() * a.length);
			cardTemp = a[random1];
			a[random1] = a[random2];
			a[random2] = cardTemp;
		}
	}

	private String print(Integer[] a) {
		myLog.info("printing all array elements");
		return Arrays.toString(a);
	}

	private Integer[] intializeArray(Integer[] toSort) {
		for (int i = 0; i < toSort.length; i++) {
			toSort[i] = i;
		}
		return toSort;
	}

	public static void main(String args[]) throws SecurityException, IOException {
		new SortDriver();
	}
}
