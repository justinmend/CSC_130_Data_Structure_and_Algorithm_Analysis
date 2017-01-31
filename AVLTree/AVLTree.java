/* Name: Justin Mendiguarin
 * Date: 10/18/15
 *
 * Description: Design and implement an AVL Tree Node class that must support "insert", and optionally "remove" operations.
 * AVL: BST & Balance Condition.
 * The height of the left and right subtrees can differ at most by 1.
 */

public class AVLTree {
	// Tree root.
	private AVLNode root;

	// Inserts value.
	public void insert(int val) {
		root = insert(root, val);
	}

	// Inserts values recursively.
	private AVLNode insert(AVLNode t, int x) {
		if (t == null) {
			t = new AVLNode(x);
		} else if (x < t.val) {
			t.left = insert(t.left, x);

			if (height(t.left) - height(t.right) == 2) { // Check for
															// unbalanced.
				if (x < t.left.val) {
					t = rotateToLeft(t);
				} else {
					t = doubleRotateToLeft(t);
				}
			}
		} else if (x > t.val) {
			t.right = insert(t.right, x);

			if (height(t.right) - height(t.left) == 2) { // Check for
															// unbalanced.
				if (x > t.right.val) {
					t = rotateToRight(t);
				} else {
					t = doubleRotateToRight(t);
				}
			}
		} else { // Duplicate, so do nothing.
		}
		t.height = max(height(t.left), height(t.right)) + 1;
		return t;
	}

	// Return height of node.
	private int height(AVLNode t) { // O(log(n))
		if (t == null) {
			return -1;
		} else {
			return t.height;
		}
	}

	// Returns max of left side and right side.
	private int max(int leftside, int rightside) {
		if (leftside > rightside) {
			return leftside;
		} else {
			return rightside;
		}
	}

	private AVLNode rotateToLeft(AVLNode k2) {
		AVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		k1.height = max(height(k1.left), k2.height) + 1;
		return k1;
	}

	private AVLNode rotateToRight(AVLNode k1) {
		AVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.right), k1.height) + 1;
		return k2;
	}

	private AVLNode doubleRotateToLeft(AVLNode k3) {
		k3.left = rotateToRight(k3.left);
		return rotateToLeft(k3);
	}

	private AVLNode doubleRotateToRight(AVLNode k1) {
		k1.right = rotateToLeft(k1.right);
		return rotateToRight(k1);
	}

}

class AVLNode {
	int val;
	int height;
	AVLNode left;
	AVLNode right;

	// Constructor.
	public AVLNode(int val) {
		this.val = val;
		height = 0;

	}
}
