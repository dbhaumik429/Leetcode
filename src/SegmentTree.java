// Java Program to show segment tree operations like construction,
// query and update
class SegmentTree
{
    int[] segmentTree; // The array that stores segment tree nodes

    /* Constructor to construct segment tree from given array. This
    constructor allocates memory for segment tree and calls
    constructSTUtil() to fill the allocated memory */
    SegmentTree(int[] arr, int n)
    {
        // Allocate memory for segment tree
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        segmentTree = new int[max_size]; // Memory allocation

        constructSTUtil(arr, 0, n - 1, 0);

//        Arrays.stream(st).forEach( y -> System.out.print(","+ y));
//        System.out.println("------------------------------------");
//        Arrays.stream(arr).forEach( y -> System.out.print(","+ y));
//        System.out.println("------------------------------------");

    }

    // A utility function to get the middle index from corner indexes.
    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    /* A recursive function to get the sum of values in given range
        of the array. The following are parameters for this function.

     */

    int getSumUtil(int low, int high, int queryLow, int queryHigh, int currIndex)
    {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        // ---------------------------
        // ---- + --------- + --------
        if (queryLow <= low && queryHigh >= high)
            return segmentTree[currIndex];

        // If segment of this node is outside the given range
        if (high < queryLow || low > queryHigh)
            return 0;

        // If a part of this segment overlaps with the given range
        // search on both side of the tree, go left [low, mid,]
        int mid = getMid(low, high);
        return getSumUtil(low, mid, queryLow, queryHigh, 2 * currIndex + 1) +
                getSumUtil(mid + 1, high, queryLow, queryHigh, 2 * currIndex + 2);
    }

    /* A recursive function to update the nodes which have the given
    index in their range. The following are parameters
        st, si, ss and se are same as getSumUtil()
        i --> index of the element to be updated. This index is in
                input array.
    diff --> Value to be added to all nodes which have i in range */
    void updateValueUtil(int low, int high, int arrIndex, int diff, int segmentIndex)
    {
        // Base Case: If the input index lies outside the range of
        // this segment
        if (arrIndex < low || arrIndex > high)
            return;

        // If the input index is in range of this node, then update the
        // value of the node and its children
        segmentTree[segmentIndex] = segmentTree[segmentIndex] + diff;

        if (high != low) {
            int mid = getMid(low, high);

            updateValueUtil(low, mid, arrIndex, diff, 2 * segmentIndex + 1);

            updateValueUtil(mid + 1, high, arrIndex, diff, 2 * segmentIndex + 2);
        }
    }

    // The function to update a value in input array and segment tree.
// It uses updateValueUtil() to update the value in segment tree
    void updateValue(int[] arr, int n, int i, int new_val)
    {
        // Check for erroneous input index
        if (i < 0 || i > n - 1) {
            System.out.println("Invalid Input");
            return;
        }

        // Get the difference between new value and old value
        int diff = new_val - arr[i];

        // Update the value in array
        arr[i] = new_val;

        // Update the values of nodes in segment tree
        updateValueUtil(0, n - 1, i, diff, 0);
    }

    // Return sum of elements in range from index qs (query start) to
// qe (query end). It mainly uses getSumUtil()
    int getSum(int n, int qs, int qe)
    {
        // Check for erroneous input values
        if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return -1;
        }
        return getSumUtil(0, n - 1, qs, qe, 0);
    }

    // A recursive function that constructs Segment Tree for array[ss..se].
    // si is index of current node in segment tree st
    int constructSTUtil(int[] arr, int low, int high, int index)
    {
        // If there is one element in array, store it in current node of
        // segment tree and return
        if (low == high) {
            segmentTree[index] = arr[low];
            return arr[low];
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the sum of values in this node
        int mid = getMid(low, high);
        segmentTree[index] = constructSTUtil(arr, low, mid, index * 2 + 1) +
                constructSTUtil(arr, mid + 1, high, index * 2 + 2);
        return segmentTree[index];
    }

    // Driver program to test above functions
    public static void main(String[] args)
    {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        SegmentTree tree = new SegmentTree(arr, n);

        // Build segment tree from given array

        // Print sum of values in array from index 1 to 3
        System.out.println("Sum of values in given range = " +
                tree.getSum(n, 1, 3));

        // Update: set arr[1] = 10 and update corresponding segment
        // tree nodes
        tree.updateValue(arr, n, 1, 10);

        // Find sum after the value is updated
        System.out.println("Updated sum of values in given range = " +
                tree.getSum(n, 1, 3));
    }
}
/*
*
*  36,9,27,4,5,16,11,1,3,0,0,7,9,0,0
*
*
*
*
*
*
* */
