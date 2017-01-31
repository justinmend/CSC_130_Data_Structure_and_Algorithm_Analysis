/* Name: Justin Mendiguarin
 * Date: 11/13/15
 *
 * Implement a hash table that uses linear probing to resolve the issue of "collision".
 */

public class HashTable_Linear {

	int tableSize;
	int[] hashTable;// Hash tableSize
	int[] h;// hash value
	int[] c;// hash count

	// constructor for the class
	public HashTable_Linear(int size) {
		tableSize = size;
		hashTable = new int[tableSize];
		c = new int[tableSize];
		h = new int[tableSize];

		for (int i = 0; i < tableSize; i++) {
			hashTable[i] = -1;
		}
	}

	// this method calculates the linear hash value
	public int hashFunction(short x, int i) {
		return ((x % tableSize) + i) % tableSize;
	}

	void insert(short x) {

		int i = 0;
		int index;
		int indexOrig;

		do {
			index = hashFunction(x, i++);
		} while (hashTable[index] != -1);

		hashTable[index] = x;
		indexOrig = (x % tableSize);
		c[indexOrig]++;
		h[index] = indexOrig;
	}

	// remove method
	boolean remove(short x) {
		int i = 0;
		int index;
		int hCount = 0;
		int findHash = x % tableSize;
		int findC = c[findHash];

		while (hCount <= findC) {
			index = hashFunction(x, i++);
			if (h[index] == findHash) {
				if (hashTable[index] == x) {
					hashTable[index] = -1;
					return true;
				}
			}
			hCount++;
		}
		return false;
	}

	// find method
	public boolean find(short x) {
		int i = 0;
		int index;
		int hCount = 0;
		int findHash = x % tableSize;
		int findC = c[findHash];

		while (hCount <= findC) {
			index = hashFunction(x, i++);
			if (h[index] == findHash) {
				if (hashTable[index] == x) {
					hashTable[index] = -1;
					return true;
				}
			}
			hCount++;
		}
		return false;
	}

}
