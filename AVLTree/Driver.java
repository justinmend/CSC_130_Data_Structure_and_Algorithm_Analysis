/* Name: Justin Mendiguarin
 * Date: 10/18/15
 *
 * Description: Design and implement a driver (the main method) that does the following:
 * Creates an array that contains a list of integers, in an ascending order, 
 * from 1 to to a large enough number (e.g. 1000000 so that the running time of building a tree containing those numbers will be at least 1 second).
 * Creates an array that contains a list of integers that is the same as the 1st but in descending order (e.g. from 1000000 to 1).
 * Creates an array that has the same numbers as above, but the order of the numbers (e.g. 1 - 1000000) is random. 
 * You can use a random number generator to decide and put a number into the array.
 * AVL-insert, into a separate AVL tree that is initially empty, the numbers in each array sequentially from the start to the end. 
 * Measure and print out the elapsed time of building each tree.
 * RB-insert,  into a separate RB tree that is initially empty, the numbers in each array sequentially from the start to the end. 
 * Measure and print out the elapsed time of building each tree.
 */

// Missing proper driver, need to implement.
public class Driver {

	public static void main(String[] args) {
		AVLTree theTree = new AVLTree();

		theTree.insert(50);
		theTree.insert(25);
		theTree.insert(15);
		theTree.insert(30);
		theTree.insert(75);
		theTree.insert(85);
		theTree.insert(90);
		theTree.insert(74);
		theTree.insert(73);
	}

}
