/* Name: Justin Mendiguarin
 * Date: 11/13/15
 * 
 * Description:
 * Design and implement a driver (the main method) that does the following:
 * Use a random number generator to create 8192 random 16-bit integers and populate these numbers into an array of length 8192.
 * Create the above four hash tables whose size is the smallest prime number that is greater than 8192; use x%TableSize as the hash function for each table where x is a 16-bit integer.
 * For each hash table that is initially empty, do the following:
 * sequentially insert the array of random short integers into the hash table.
 * randomly remove 1000 numbers from the hash table.
 * find from the hash table each number of the original array from the last to the first.
 * measure the elapsed time for the above three operations combined.
 */

import java.util.*;

class HashTable_Driver {

	public static void main(String[] args) {

		int tableSize = 8209; // 8209
		int sampleSize = 8192; // 8192
		int removeSize = 1000; // 1000

		final double startTimeLinked;
		final double startTimeLinear;
		final double startTimeQuadratic;
		final double startTimeDouble;

		// Use a random number generator to create 8192 random 16-bit integers
		// and populate these numbers into an array of length 8192.
		Random rand = new Random();
		short a[] = new short[sampleSize];// sample size array

		int[] randRemove = new int[removeSize];// rand to remove 1000 elements

		// add the random numbers into the array
		for (int i = 0; i < a.length; i++) {
			a[i] = (short) rand.nextInt(Short.MAX_VALUE + 1);
		}

		// add the random 1000 indexes to delete
		for (int i = 0; i < removeSize; i++) {
			randRemove[i] = rand.nextInt(sampleSize);
		}

		// new object table for the linked list table
		HashTable_LinkedList hlink = new HashTable_LinkedList(tableSize);

		// loop inserts each element of the array into the linked list hash
		// table
		startTimeLinked = System.nanoTime(); // Timer
		for (int i = 0; i < a.length; i++) {
			hlink.insert(a[i]);
		}

		// remove for the list hash table
		for (int i = 0; i < randRemove.length; i++) {
			hlink.remove(a[randRemove[i]]);
		}

		int countLinked = 0; 
		// find linked list loop
		for (int i = sampleSize - 1; i >= 0; i--) {
			 //System.out.println(hlink.find(a[i]));//to show true and false
			if (!hlink.find(a[i])) {
				countLinked++;
			}
		}

		final double durationLinked = (System.nanoTime() - startTimeLinked); // Timer
																				// End
		System.out.println("Linked: " + (durationLinked / 1000000000)
				+ " seconds");
		// System.out.println("How many elements found in linked list: "
		// + countLinked);
		System.out.println();

		// new object table for linear probing
		HashTable_Linear linear = new HashTable_Linear(tableSize);

		// loop inserts each element of the array into the linear hash table
		startTimeLinear = System.nanoTime(); // Linear Timer Start
		for (int i = 0; i < a.length; i++) {
			linear.insert(a[i]);
		}

		// remove for the linear hash table
		for (int i = 0; i < randRemove.length; i++) {
			linear.remove(a[randRemove[i]]);
		}

		int countLinear = 0; 
		// find linear loop
		for (int i = sampleSize - 1; i >= 0; i--) {
			// System.out.println(linear.find(a[i]));//to show true and false
			if (!linear.find(a[i])) {
				countLinear++;
			}
		}

		final double durationLinear = (System.nanoTime() - startTimeLinear); // Linear
																				// Timer
																				// End
		System.out.println("Linear: " + (durationLinear / 1000000000)
				+ " seconds");
		// System.out.println("How many elements found in linear: " +
		// countLinear);
		System.out.println();

		// new object table for quadratic probing
		HashTable_Quadratic quadratic = new HashTable_Quadratic(tableSize);

		// loop inserts each element of the array into the quadratic hash table
		startTimeQuadratic = System.nanoTime(); // Quadratic Timer Start
		for (int i = 0; i < a.length; i++) {
			quadratic.insert(a[i]);
		}

		// remove for the quadratic hash table
		for (int i = 0; i < randRemove.length; i++) {
			quadratic.remove(a[randRemove[i]]);
		}

		int countQuadratic = 0;
		// find quadratic loop
		for (int i = sampleSize - 1; i >= 0; i--) {
			// System.out.println(quadratic.find(a[i]));//to show true and false
			if (!quadratic.find(a[i])) {
				countQuadratic++;
			}
		}

		final double durationQuadratic = (System.nanoTime() - startTimeQuadratic); // Quadratic
																					// Timer
																					// End
		System.out.println("Quadratic: " + (durationQuadratic / 1000000000)
				+ " seconds");

		// System.out.println("How many elements found in quadratic: "
		// + countQuadratic);
		System.out.println();

		// new object table for double probing
		HashTable_DoubleHash doubleHash = new HashTable_DoubleHash(tableSize);

		// loop inserts each element of the array into the double hash table
		startTimeDouble = System.nanoTime(); // Double Timer Start
		for (int i = 0; i < a.length; i++) {
			doubleHash.insert(a[i]);
		}

		// remove for the double hash table
		for (int i = 0; i < randRemove.length; i++) {
			doubleHash.remove(a[randRemove[i]]);
		}

		int countDouble = 0;
		// find doubleHash loop
		for (int i = sampleSize - 1; i >= 0; i--) {
			// System.out.println(doubleHash.find(a[i]));//to show true and
			// false
			if (!doubleHash.find(a[i])) {
				countDouble++;
			}
		}

		final double durationDouble = (System.nanoTime() - startTimeDouble); // Double
																				// Timer
																				// End
		System.out.println("Double: " + (durationDouble / 1000000000)
				+ " seconds");
		// System.out.println("How many elements found in double hash: "
		// + countDouble);
		// System.out.println();
	}
}