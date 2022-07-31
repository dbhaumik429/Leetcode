package binarySearch;

import java.util.Arrays;

public class RemoveDups {

    private int binarySearch(int[] nums, int st, int curr ){

        int en = nums.length - 1;
        int nextIndex = nums.length;
        while( st < en ){
            int mid = st + en / 2;

            if(nums[mid] > curr){
                nextIndex = mid;
                en = mid - 1;
            }else
                st = mid + 1;
        }

        return nextIndex;
    }

    public int[] removeDuplicates(int[] nums) {

        //classing BS problem
        Arrays.sort(nums);
        int i=1;

        for(; i< nums.length; i++){

            if(nums[i-1] == nums[i]){
                //find the next non matching number
                // linear won't do
                // do Bs
                int next = binarySearch(nums, i, nums[i]);

                nums[i] = nums[next];
                i = next;
            }
        }

        return Arrays.copyOfRange(nums, 0, i-1);
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2};

        RemoveDups removeDups = new RemoveDups();

        Arrays.stream(removeDups.removeDuplicates(arr)).forEach(System.out::println);
    }
}