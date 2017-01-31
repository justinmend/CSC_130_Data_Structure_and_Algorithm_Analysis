/* Name: Justin Mendiguarin
 * Date: 11/29/15
 * 
 * Assignment 4 - A Simple Simulator for Priority Based CPU Scheduling
 * 
 * Design and implement a Java class of binary heap that satisfies the following requirements:
 * 1.The binary heap has a size of 101, and is implemented using an array and a heapSize index.
 * 2.Each object in the heap is a process that contains the following data fields: 
 *   (a) nice value (key), and (b) process ID (a sequence number).
 * 3.The process objects in the heap are organized based upon their nice values, 
 *   i.e. the nice value of a process must be no greater than that of its children.
 * 4.A process is inserted into the heap using heap structure property and heap order property stated in step 3.
 * 5.A process with the minimum nice values is removed from the heap using heap structure property 
 *   and heap order property stated in step 3.
 *   
 */

public class BinaryHeap {

	// 1.The binary heap has a size of 101, and is implemented using an array
	// and a heapSize index.
	protected static final int heapSize = 101; // 101
	private int currentSize; // Size of heap at the moment.
	private BinaryHeapData array[]; // Heap array

	// Construct the binary heap.
	public BinaryHeap() {
		currentSize = 0;
		array = new BinaryHeapData[heapSize];
	}

	// Create 100 processes in such a manner that their IDs sequentially go from
	// 1 to 100, and their nice values are between 0 and 39 (use a random
	// generator).
	// Then put them sequentially into the array of the first heap.
	public void putSequentially(BinaryHeapData[] processArray) {
		for (int i = 1; i < heapSize; i++) {
			array[i] = processArray[i];
			currentSize++;
		}
	}

	// Establish heap order property from
	// an arbitrary arrangement of items.
	// Run in linear time. O(n)
	// Binary Heap Order Property: start at the end.
	// Take the size and divide by 2. This will give us the last non-leaf node.
	// Work from the end back to the front. Start at n/2 and move towards 1.
	public void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--) {
			percolateDown(i);
		}
	}

	// Every time you check for the children minimum of (2i and 2i+1).
	// If there is a violation, which is that the parent is > the minimum child,
	// we swap the two indexes.
	// Then the new child must be checked if it has any children. If so, test
	// again. If not, continue.

	// 3.The process objects in the heap are organized based upon their nice
	// values,
	// i.e. the nice value of a process must be no greater than that of its
	// children.
	// left = 2*i, right = (2*i +1)
	private void percolateDown(int i) {
		int left = 2 * i;
		int right = 2 * i + 1;
		if (array[i] == null || i > currentSize || left > currentSize
				|| this.isEmpty()) {
			return;
		}
		if (right <= currentSize) {
			if (array[left].getKey() < array[right].getKey()) {
				if (array[left].getKey() < array[i].getKey()) {
					swap(left, i);
					percolateDown(left);
				}
			} else {
				if (array[right] != null
						&& (array[i].getKey() > array[right].getKey())) {
					swap(right, i);
					percolateDown(right);
				} else if (array[left] != null
						&& array[i].getKey() > array[left].getKey()) {
					swap(left, i);
					percolateDown(left);
				}
			}
		} else if (left == currentSize) {
			if (array[left].getKey() < array[i].getKey()) {
				swap(left, i);
				percolateDown(left);
			}
		} else {
			return;
		}
		return;
	}

	public void swap(int i, int j) {
		BinaryHeapData temp = new BinaryHeapData();
		temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	public void printHeap() {
		System.out.printf("%-12s%-12s%s\n", "index:", "ID:", "key:");
		for (int i = 1; i <= currentSize; i++) {
			System.out.printf("%-12d%-12d%-12d\n", i, array[i].getProcessID(),
					array[i].getKey());

		}
		System.out.println("currentSize: " + currentSize);
		System.out.println();
	}

	public boolean isEmpty() {
		if (currentSize > 0) {
			return false;
		} else {
			return true;
		}
	}

	// 4.A process is inserted into the heap using heap structure property and
	// heap order property stated in step 3.
	public void insert(BinaryHeapData userNode) {
		if (currentSize + 1 > heapSize)
			return;
		currentSize++;
		array[currentSize] = userNode;
		percolateUp(currentSize);
	}

	public void percolateUp(int i) {
		int parentIndex = i / 2;
		if (parentIndex > 0) {
			if (array[i].getKey() < array[parentIndex].getKey()) {
				swap(parentIndex, i);
				percolateUp(parentIndex);
			}
		}
		return;
	}

	// 5.A process with the minimum nice values is removed from the heap using
	// heap structure property
	// and heap order property stated in step 3.
	public BinaryHeapData deleteMin() {
		if (this.isEmpty()) {
			return new BinaryHeapData(-1, -1);
		} else {
			int root = 1;
			BinaryHeapData deleteMe = array[root];
			array[root] = array[currentSize];
			array[currentSize] = null;
			currentSize--;
			percolateDown(root);
			return deleteMe;
		}
	}
}

// 2.Each object in the heap is a process that contains the following data
// fields:
// (a) nice value (key), and (b) process ID (a sequence number).
class BinaryHeapData {
	private int key = 0;
	private int processID = 0;

	public BinaryHeapData() {

	}

	public BinaryHeapData(int processID, int key) {
		this.processID = processID;
		this.key = key;
	}

//	public void setKey(int key) {
//		this.key = key;
//	}

	public int getKey() {
		return key;
	}

//	public void setProcessID(int processID) {
//		this.processID = processID;
//	}

	public int getProcessID() {
		return processID;
	}
}