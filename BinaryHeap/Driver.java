/* Name: Justin Mendiguarin
 * Date: 11/29/15
 * 
 * Assignment 4 - A Simple Simulator for Priority Based CPU Scheduling
 * 
 * Develop a driver that does the following:
 * 1.Create two instances of heap objects where the arrays are initially empty.
 * 2.Create 100 processes in such a manner that their IDs sequentially go from 1 to 100,
 * 	and their nice values are between 0 and 39 (use a random generator).
 * 	Then put them sequentially into the array of the first heap.
 * 3.Use "buildHeap" algorithm to reorganize the first heap according to the
 *  heap order property.
 * 4.Print out the content of the 1st heap sequentially.
 * 5.Perform "deleteMin" to remove a process from the first heap (then the
 *	process is running),
 * 	then "insert" it into the second heap (the process returns to the waiting
 *	queue after
 * 	running out of its time share).
 * 6.Keep doing step 5 till the first heap becomes empty and the second full.
 * 7.Print out the content of the 2nd heap sequentially.
 * 8.Compare if the printouts of the two heaps are the same.
 *
 *
 */

import java.util.*;

public class Driver extends BinaryHeap {
	public static void main(String[] args) {
		// 1.Create two instances of heap objects where the arrays are initially
		// empty.
		BinaryHeap heap1 = new BinaryHeap();
		BinaryHeap heap2 = new BinaryHeap();

		// 2.Create 100 processes in such a manner that their IDs sequentially
		// go from 1 to 100,
		// and their nice values are between 0 and 39 (use a random generator).
		// Then put them sequentially into the array of the first heap.
		BinaryHeapData[] processArray = createProcesses();
		heap1.putSequentially(processArray);

		// 3.Use "buildHeap" algorithm to reorganize the first heap according to
		// the
		// heap order property.
		heap1.buildHeap();

		// 4.Print out the content of the 1st heap sequentially.
		System.out.println("1st Heap: ");
		heap1.printHeap();

		// 5.Perform "deleteMin" to remove a process from the first heap (then
		// the process is running),
		// then "insert" it into the second heap (the process returns to the
		// waiting queue after
		// 6.Keep doing step 5 till the first heap becomes empty and the second
		// full.
		while (!heap1.isEmpty()) {
			BinaryHeapData deleted = heap1.deleteMin();
			heap2.insert(deleted);
		}

		// 7.Print out the content of the 2nd heap sequentially.
		System.out.println("2nd Heap: ");
		heap2.printHeap();

	}

	// 2.Create 100 processes in such a manner that their IDs sequentially go
	// from 1 to 100,
	// and their nice values are between 0 and 39 (use a random generator).
	// Then put them sequentially into the array of the first heap.
	public static BinaryHeapData[] createProcesses() {
		int randKey = -1;
		Random rand = new Random();
		BinaryHeapData[] localArray = new BinaryHeapData[heapSize];
		for (int i = 1; i < heapSize; i++) {
			randKey = rand.nextInt((39 - 1) + 1) + 1;
			localArray[i] = new BinaryHeapData(i, randKey);
		}
		return localArray;
	}
}
