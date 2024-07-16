package patterns;

/**
 * Two pointers: one input, opposite ends
 * ------------------------------------------------------------------------
 * public int fn(int[] arr) {
 * int left = 0;
 * int right = arr.length - 1;
 * int ans = 0;
 * <p>
 * while (left < right) {
 * // do some logic here with left and right
 * if (CONDITION) {
 * left++;
 * } else {
 * right--;
 * }
 * }
 * <p>
 * return ans;
 * }
 */

public class Patterns {
    public int fn(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int ans = 0;

        while (left < right) {
            // do some logic here with left and right
            if (CONDITION) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
