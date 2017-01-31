/* Name: Justin Mendiguarin
 * Date: 11/13/15
 * 
 * Implement a hash table that uses separate linked lists to resolve the issue of "collision".
 */

public class HashTable_LinkedList {

	int tableSize;
	Node hashLinked[];

	// constructor for the class
	public HashTable_LinkedList(int size) {
		tableSize = size;
		hashLinked = new Node[tableSize];

		// makes blank nodes
		for (int i = 0; i < tableSize; i++) {
			hashLinked[i] = new Node((short) 0, null);
		}
	}

	// this method calculates the hash value
	public int hashFunction(short x) {
		int hashValue = x % tableSize;
		return hashValue;
	}

	// the insert method individually inserts the values
	void insert(short x) {

		int index = hashFunction(x);
		Node n = hashLinked[index];

		// creates the first node
		while (n.nextNode != null) {
			n = n.nextNode;
		}
		n.nextNode = new Node(x, null);
	}

	// remove method
	void remove(short x) {

		int index = hashFunction(x);
		Node n = hashLinked[index];

		// creates the first node
		while (n.nextNode != null) {
			if (n.nextNode.data == x) {
				// remove
				n.nextNode = n.nextNode.nextNode;
				return;
			}
			n = n.nextNode;
		}
	}

	// find method
	public boolean find(short x) {

		int index = hashFunction(x);
		Node n = hashLinked[index];

		// creates the first node
		while (n.nextNode != null) {
			if (n.nextNode.data == x) {
				// found!
				return true;
				// return n.nextNode;
			}
			n = n.nextNode;
		}
		// return null
		return false;
	}

	// node class for the linked list
	class Node {
		short data;
		Node nextNode;

		public Node(short data, Node nextNode) {
			this.data = data;
			this.nextNode = nextNode;
		}
	}
}