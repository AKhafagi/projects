/**
 * San Diego State University.<br> CS 310: Data Structures<br> Spring 2016<br>
 * 
 * This is the primary source file for the final programming assignment. You
 * must complete the algorithms for merge sort, heap sort, shell sort, and
 * quicksort in this file and submit an additional driver file demonstrating
 * their correctness. For each of these methods, provide the appropriate javadoc
 * briefly describing the algorithm, its best and worst case data cases (if
 * any), its best, worst, and average running times, and identify if it is
 * stable and/or in place. You may (and should) create helper methods to support
 * your implementation, but these must be private.
 * 
 * @version 0.2
 */

package edu.sdsu.cs.datastructures;

import java.util.Comparator;

/**
 * collection of search and sort static methods.
 * 
 * @author Anas Khafagi
 *
 */
public class Sorters<E> {

	/**
	 * performs an iterative search for a specified value within an array.
	 * 
	 * @param items
	 *            the array of comparable objects to sort
	 * @param value
	 *            the value to find
	 * @return index position within the array to an instance of the target
	 *         value or -1 if not in the supplied array.
	 */
	public static <E extends Comparable<E>> int binarySearch(final E[] items, final E value) {
		return binarySearchIterative(items, value, 0, items.length - 1, Comparator.naturalOrder());
	}

	/**
	 * performs an iterative search for a specified value within an array.
	 * 
	 * @param items
	 *            the array of comparable objects to sort
	 * @param value
	 *            the value to find
	 * @param order
	 *            the Comparator object to use when ordering the elements in the
	 *            array
	 * @return index position within the array to an instance of the target
	 *         value or -1 if not in the supplied array.
	 */
	public static <E> int binarySearch(final E[] items, final E value, final Comparator<E> order) {
		return binarySearchIterative(items, value, 0, items.length - 1, order);
	}

	private static <E> int binarySearchIterative(E[] items, E value, int low, int high, Comparator<E> order) {
		while (low <= high) {
			int mid = (low + high) >> 1;
			int result = order.compare(value, items[mid]);
			if (result > 0) {
				// value - array > 0: array value smaller, so in right half
				low = mid + 1;
			} else if (result < 0) {
				high = mid - 1;
			} else if (result == 0) {
				return mid;
			}
		}
		return -1;
	}

	/**
	 * performs a recursive search for a specified value within an array.
	 * 
	 * @param items
	 *            the array of comparable objects to sort
	 * @param value
	 *            the value to find
	 * @return index position within the array to an instance of the target
	 *         value or -1 if not in the supplied array.
	 */
	static <E> int binarySearchRecursive(E[] items, E value, final Comparator<E> order) {
		return binarySearchRecursive(items, value, 0, items.length - 1, order);
	}

	private static <E> int binarySearchRecursive(E[] items, E value, final int low, final int high,
			final Comparator<E> order) {
		if (low > high) {
			return -1;
		}
		final int mid = (low + high) >> 1;
		final int result = order.compare(value, items[mid]);
		if (result > 0) {
			return binarySearchRecursive(items, value, mid + 1, high, order);
		} else if (result < 0) {
			return binarySearchRecursive(items, value, low, mid - 1, order);
		}
		return mid;
	}

	/**
	 * constructs a heap data structure and then uses it to place the array
	 * elements in sorted order. <br>
	 * In place: yes<br>
	 * Stable: not without a wrapper Complexity Best: O(nlog(n)) Worst:
	 * O(nlog(n)) Average: O(nlog(n))
	 * 
	 * @param toSort
	 *            the array to sort
	 * @return the sorted array
	 */
	public static <E extends Comparable<E>> E[] heapSort(E[] toSort) {
		return heapSort(toSort, Comparator.naturalOrder());
	}

	/**
	 * constructs a heap data structure and then uses it to place the array
	 * elements in sorted order. <br>
	 * In place: yes<br>
	 * Stable: not without a wrapper Complexity Best: O(nlog(n)) Worst:
	 * O(nlog(n)) Average: O(nlog(n))
	 * 
	 * @param toSort
	 *            the array to sort
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 * @return the sorted array
	 */
	public static <E> E[] heapSort(E[] toSort, Comparator<E> order) {
		E[] n = toSort;
		int heapSize = 1;
		int i, index, parent;
		E newInsert, top;
		int larger, left, right;
		int length = n.length;
		for (i = 0; i < length; i++) {
			index = heapSize - 1;
			parent = (index - 1) >> 1;
			newInsert = n[index];
			while (index > 0 && order.compare(n[parent], newInsert) < 0) {
				n[index] = n[parent];
				index = parent;
				parent = (parent - 1) >> 1;
			}
			n[index] = newInsert;
			heapSize++;
		}

		heapSize--;
		for (i = length - 1; i >= 0; i--) {
			E tmp = n[0];
			larger = 0;
			index = 0;
			n[0] = n[heapSize - 1];
			top = n[0];
			while (index < (heapSize >> 1)) {
				left = (index << 1) + 1;
				right = left + 1;
				if (right < heapSize && order.compare(n[left], n[right]) < 0) {
					larger = right;
				} else {
					larger = left;
				}
				if (order.compare(top, n[larger]) >= 0) {
					break;
				}
				n[index] = n[larger];
				index = larger;
			}
			n[index] = top;
			heapSize--;
			n[i] = tmp;
		}
		return n;
	}

	/**
	 * places the input array in its natural order using the Comparable pattern.
	 * Insertion sort represents the best of the worst sorts. In practice, one
	 * should consider using quicksort, heapsort, shell sort, or any number of
	 * more efficient algorithms. Insertion sort's computational complexity
	 * grows quadratically as the data set grows. Thus, for large arrays, this
	 * algorithm may render the incorporating class inoperable.<br>
	 * <br>
	 * That said, in the best case scenario, insertion sort completes in the
	 * minimum amount of time possible: <i>O(n)</i>. Interestingly, insertion
	 * sort actually runs in <i>O(kn)</i> time where <i>k</i> represents the
	 * average number of positions an element in the array is from its correct
	 * position. Thus, insertion sort not only performs well for an array in
	 * sorted order, but it does so for arrays near their sorted order. One of
	 * the faster sorting algorithms, <i>Shell Sort</i> uses this fact to
	 * achieve excellent performance.<br>
	 * <br>
	 * In place: yes<br>
	 * Stable: yes
	 * 
	 * @see https://en.wikipedia.org/wiki/Insertion_sort
	 * 
	 * @param toSort
	 *            a reference to the unsorted array
	 * @return the sorted array
	 */
	public static <E extends Comparable<E>> E[] insertionSort(E[] toSort) {
		return insertionSort(toSort, Comparator.naturalOrder());
	}

	/**
	 * sorts the parameter array with the insertion sort algorithm using the
	 * provided comparator.<br>
	 * In place: yes<br>
	 * Stable: yes
	 * 
	 * @param toSort
	 *            the array of elements to sort
	 * @param order
	 *            the Comparator object to consult when ranking elements
	 * @return
	 */
	public static <E> E[] insertionSort(E[] toSort, Comparator<E> order) {
		for (int idxUnsorted = 1; idxUnsorted < toSort.length; idxUnsorted++) {
			E elementToInsert = toSort[idxUnsorted];
			int idxSortedPortion = idxUnsorted;

			while (idxSortedPortion > 0 && order.compare(toSort[idxSortedPortion - 1], elementToInsert) > 0) {
				toSort[idxSortedPortion] = toSort[idxSortedPortion - 1];
				idxSortedPortion--;
			}
			toSort[idxSortedPortion] = elementToInsert;
		}
		return toSort;
	}

	/**
	 * The algorithm partitions the array into n logical sections of size 1 each
	 * , then merges those arrays with their neighbors to form partitions of
	 * size 2 , then size 4 and so on until the array is in sorted order In
	 * place: No, as it requires more memory as the input size increases<br>
	 * Stable: Yes Complexity Best: O(nlog(n)) Worst: O(nlog(n)) Average:
	 * O(nlog(n))
	 * 
	 * @param toSort
	 *            the array to be sorted
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 * @return the sorted array
	 */
	public static <E extends Comparable<E>> E[] mergeSort(E[] toSort) {
		return mergeSort(toSort, Comparator.naturalOrder());
	}

	/**
	 * The algorithm partitions the array into n logical sections of size 1 each
	 * , then merges those arrays with their neighbors to form partitions of
	 * size 2 , then size 4 and so on until the array is in sorted order In
	 * place: No, as it requires more memory as the input size increases<br>
	 * Stable: Yes Complexity Best: O(nlog(n)) Worst: O(nlog(n)) Average:
	 * O(nlog(n))
	 * 
	 * @param toSort
	 *            the array to be sorted
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 * @return the sorted array
	 */
	public static <E> E[] mergeSort(E[] toSort, Comparator<E> order) {
		E[] n = toSort;
		int size = n.length;
		mergeSortHelper(0, size - 1, n, order);
		return n;
	}

	/**
	 * recursively breaks down the array down to logical arrays of size 1.
	 * 
	 * @param low
	 *            the smallest index
	 * @param hi
	 *            the largest index
	 * @param array
	 *            the array to break down
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 */
	private static <E> void mergeSortHelper(int low, int hi, E[] array, Comparator<E> order) {
		if (low == hi)
			return;
		int mid = (low + hi) >> 1;
		mergeSortHelper(low, mid, array, order);
		mergeSortHelper(mid + 1, hi, array, order);
		merge(low, mid + 1, hi, array, order);

	}

	/**
	 * merges each array with its neighbor to build the array back in sorted
	 * order
	 * 
	 * @param low
	 *            the smallest index
	 * @param hi
	 *            the largest index
	 * @param upperBound
	 *            the upperbound of the array
	 * @param array
	 *            the array to be sorted
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 */
	private static <E> void merge(int low, int hi, int upperBound, E[] array, Comparator<E> order) {
		E[] aux = (E[]) new Object[array.length];
		int j = 0;
		int lowerBound = low;
		int mid = hi - 1;
		int numElements = (upperBound - lowerBound) + 1;
		while (low <= mid && hi <= upperBound) {
			if (order.compare(array[low], array[hi]) <= 0) {
				aux[j++] = array[low++];
			} else {
				aux[j++] = array[hi++];
			}
		}
		while (low <= mid) {
			aux[j++] = array[low++];
		}
		while (hi <= upperBound) {
			aux[j++] = array[hi++];
		}
		for (j = 0; j < numElements; j++) {
			array[lowerBound + j] = aux[j];
		}

	}

	/**
	 * modified Quick sort (by swapping the element at the middle with the rightmost element)
	 * recursively partitions the array into smaller pieces based on
	 * a pivot the elements are arranged so that everything on the left of the
	 * pivot is smaller than the pivot and everything to the right greater than
	 * the pivot. <br>
	 * In place: Yes. Stable: No. Complexity Best: O(nlog(n)) Average:
	 * O(nlog(n)) Worst: O(n^2)
	 * 
	 * @param toSort
	 *            the array to be sorted.
	 * @param order
	 *            the Comparator object to consult when ranking the elements.
	 * @return the sorted array.
	 */
	public static <E extends Comparable<E>> E[] quickSort(E[] toSort) {
		return quickSort(toSort, Comparator.naturalOrder());
	}

	/**
	 * Quick sort recursively partitions the array into smaller pieces based on
	 * a pivot the elements are arranged so that everything on the left of the
	 * pivot is smaller than the pivot and everything to the right greater than
	 * the pivot. <br>
	 * In place: Yes. Stable: No. 
	 * Complexity Best: O(nlog(n)) Average:
	 * O(nlog(n)) Worst: O(n^2) the worst case is when the data is in order, or
	 * almost sorted order.
	 * 
	 * @param toSort
	 *            the array to be sorted.
	 * @param order
	 *            the Comparator object to consult when ranking the elements.
	 * @return the sorted array.
	 */
	public static <E> E[] quickSort(E[] toSort, Comparator<E> order) {
		E[] n = toSort;
		quickSort(0, n.length - 1, order, n);
		return n;
	}

	/**
	 * Recursively partitions the array based on the pivot
	 * 
	 * @param left
	 *            the left most index
	 * @param right
	 *            the right most index
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 * @param array
	 *            the array to be sorted
	 */
	private static <E> void quickSort(int left, int right, Comparator<E> order, E[] array) {
		if ((right - left) <= 0) {
			return;
		}
		swap(((left + right) >> 1), right, array);
		E pivot = array[right];
		int partition = getPartition(left, right, pivot, array, order);
		quickSort(left, partition - 1, order, array);
		quickSort(partition + 1, right, order, array);
	}

	/**
	 * @param left
	 *            the left most index of the partition
	 * @param right
	 *            the right most index of the partition
	 * @param pivot
	 *            the pivot where everything to the left is smaller and
	 *            everything to the right is greater
	 * @param array
	 *            the array to be sorted
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 * @return the left most pointer
	 */
	private static <E> int getPartition(int left, int right, E pivot, E[] array, Comparator<E> order) {
		int lPtr = left - 1;
		int rPtr = right;
		for (;;) {
			while (order.compare(array[++lPtr], pivot) < 0)
				;
			while (rPtr > 0 && order.compare(array[--rPtr], pivot) > 0)
				;
			if (lPtr >= rPtr) {
				break;
			} else {
				swap(lPtr, rPtr, array);
			}
		}
		swap(lPtr, right, array);
		return lPtr;
	}

	/**
	 * swaps 2 elements in the array by using a temp.
	 * 
	 * @param one
	 *            the index of the first element
	 * @param two
	 *            the index of the second element
	 * @param array
	 *            the array to swap the elements from
	 */
	private static <E> void swap(int one, int two, E[] array) {
		E temp = array[one];
		array[one] = array[two];
		array[two] = temp;
	}

	/**
	 * places the parameter object in order using the elements natural ordering.
	 * <br>
	 * In place: yes<br>
	 * Stable: no
	 * 
	 * @param toSort
	 *            the array to sort
	 * @return the sorted array
	 */
	public static <E extends Comparable<E>> E[] selectionSort(E[] toSort) {
		return selectionSort(toSort, Comparator.naturalOrder());
	}

	/**
	 * places the parameter object in order using the provided comparator.<br>
	 * In place: yes<br>
	 * Stable: no
	 * 
	 * @param toSort
	 *            the array to sort
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 * @return The sorted array
	 */
	public static <E> E[] selectionSort(E[] toSort, Comparator<E> order) {
		for (int sortedCount = 0; sortedCount < toSort.length; sortedCount++) {
			int minIndex = sortedCount;
			for (int index = sortedCount + 1; index < toSort.length; index++) {
				if (order.compare(toSort[minIndex], toSort[index]) < 0) {
					minIndex = index;
				}
			}
			final E toSwap = toSort[minIndex];
			toSort[minIndex] = toSort[sortedCount];
			toSort[sortedCount] = toSwap;
		}
		return toSort;
	}

	/**
	 * Shell sort works by repeatedly sort small sections of the array by using
	 * insertion sort. these small sections span the entire array and are
	 * compromised of every nth element. then n decreases until n=1.<br>
	 * In place: Yes<br>
	 * Stable: No 
	 * Complexity Best: O(n^3/2) Average: O(n^(5/4) Worst: O(n^7/6)
	 * 
	 * @param toSort
	 *            the array to be sorted
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 * @return the sorted array.
	 */
	public static <E extends Comparable<E>> E[] shellSort(E[] toSort) {
		return shellSort(toSort, Comparator.naturalOrder());
	}

	/**
	 * Shell sort works by repeatedly sort small sections of the array by using
	 * insertion sort. these small sections span the entire array and are
	 * compromised of every nth element. then n decreases until n=1.<br>
	 * In place: Yes<br>
	 * Stable: No
	 * 
	 * @param toSort
	 *            the array to be sorted
	 * @param order
	 *            the Comparator object to consult when ranking the elements
	 * @return the sorted array.
	 */
	public static <E> E[] shellSort(E[] toSort, Comparator<E> order) {
		E[] n = toSort;
		int in, out, h = 1;
		E temp;
		int size = n.length;
		while (h <= size / 3)
			h = h * 3 + 1;
		while (h > 0) {
			for (out = h; out < size; out++) {
				temp = n[out];
				in = out;
				while (in > h - 1 && order.compare(n[in - h], temp) >= 0) {
					n[in] = n[in - h];
					in -= h;
				}
				n[in] = temp;
			}
			h = (h - 1) / 3;
		}
		return n;
	}

}