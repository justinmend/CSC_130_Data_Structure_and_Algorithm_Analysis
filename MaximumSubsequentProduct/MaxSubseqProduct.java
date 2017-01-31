/* Name: Justin Mendiguarin
 * Date: 9/27/15
 * 
 * Description: Finds out the maximum subsequence whose product is the largest among all the possible subsequences from a list of integers. 
 * Input: An array of integers: list[N]; 
 * Output: The maximum subsequence MaxSeq[M] (its start and end indexes) and its product MaxSeqProduct.
 * Use divide and conquer algorithm.
 */

// Need to print the maximum subsequence lists and (its start and end indexes).
// Need to fix. Wrong indexes for maximum subsequences that does not contain any of the two center points.

import java.util.*;

public class MaxSubseqProduct {
	static int start;
    static int end;
	
	public static void main(String[] args) {
		// int[] ARRAY = {0,-4, 3, 5,2,1, 2, 6, -2,0};
		// int[] ARRAY = { -4, 3, 5, 2, 0, 0, 1, 2, 6, -2 };
		// int[] ARRAY = {-4, 3, 5,0, 2,1,0, 2, 6, -2};
		// int[] ARRAY = { -4, 3, 5, 2, 1, 2, 6, -2 };
		// int[] ARRAY = { 4, 3, 5, -2, -1, 2, 6, 2 };
		// int[] ARRAY = { 4, 3, 5, 2, 1, 2, 6, 2 };
		// int[] ARRAY = { -4, -3, -5, -2, -1, -2, -6, -2 };
		 int[] A = {4,-3,5,-2,1,2,6,-2};
	
		maxSubseqProduct(A, 0, A.length - 1);

	}

	public static int maxSubseqProduct(int[] ARRAY, int start, int end) {
		//Base Case
		if (start == end)              
			return (ARRAY[start]);
		int center = (start + end) / 2;

		int leftMaxProduct = maxSubseqProduct(ARRAY, start, center);      // T(maxSubseqProduct, N/2)
		int rightMaxProduct = maxSubseqProduct(ARRAY, center + 1, end);   // T(maxSubseqProduct, N/2)

		int leftProductofCrossinMiddle = 0, maxLeftCenterProduct = 0, minLeftCenterProduct = 0;   // Constant
		leftProductofCrossinMiddle = maxLeftCenterProduct = minLeftCenterProduct = ARRAY[center]; // Constant

		int maxLeftSequenceStart, minLeftSequenceStart;
		maxLeftSequenceStart = minLeftSequenceStart = center;

		// (N/2) for first For loop.
		//FOR LOOP for the Left Max Center Product
		for (int i = center - 1; i >= start; i--) {
			leftProductofCrossinMiddle *= ARRAY[i];
			if (leftProductofCrossinMiddle > maxLeftCenterProduct) { 
				maxLeftCenterProduct = leftProductofCrossinMiddle;
				maxLeftSequenceStart = i;
			}
			else if (leftProductofCrossinMiddle < minLeftCenterProduct) {
				minLeftCenterProduct = leftProductofCrossinMiddle;
				minLeftSequenceStart = i;
			}

		}

		int rightProductofCrossinMiddle = 0, maxRightCenterProduct = 0, minRightCenterProduct = 0; // Constant
		rightProductofCrossinMiddle = maxRightCenterProduct = minRightCenterProduct = ARRAY[center + 1]; //Constant

		int maxRightSequenceEnd, minRightSequenceEnd;
		maxRightSequenceEnd = minRightSequenceEnd = center + 1;

		// (N/2) for second For loop.
		//FOR LOOP for the Right Max Center Product
		for (int i = center + 2; i <= end; i++) {
			rightProductofCrossinMiddle *= ARRAY[i];
			if (rightProductofCrossinMiddle > maxRightCenterProduct) {
				maxRightCenterProduct = rightProductofCrossinMiddle;
				maxRightSequenceEnd = i;
			}
			else if (rightProductofCrossinMiddle < minRightCenterProduct) {
				minRightCenterProduct = rightProductofCrossinMiddle;
				minRightSequenceEnd = i;
			}		
		}

		// Combine the solution from the left and right max center product to arrive at a
		// Solution for the max center product.

		int maxCenterProduct = maxLeftCenterProduct * maxRightCenterProduct;
		int minCenterProduct = minLeftCenterProduct * minRightCenterProduct;

		// Compare the the four values of which is the maximum product
		int max = getMax(leftMaxProduct, rightMaxProduct, maxCenterProduct,
				minCenterProduct);


		if (maxCenterProduct > minCenterProduct) {
			start = maxLeftSequenceStart;
			end = maxRightSequenceEnd;
		}

		if (minCenterProduct > maxCenterProduct) {
			start = minLeftSequenceStart;
			end = minRightSequenceEnd;
		}

		
		System.out.println("Start: " + start + " End: " + end);
		System.out.print("Maximum Subsequence: [");
		for (int i = start; i <= end; i++) {
			System.out.print("" + ARRAY[i] + ",");
		}
		System.out.println("]");
		System.out.println("max:                   " + max);
		System.out.println();

		return max;
	}

	// return the maximum value out of the four values inputed
	public static int getMax(int... values) {

		int max = Integer.MIN_VALUE;

		for (int value : values) {
			if (value > max)
				max = value;
		}

		return max;
	}
}
