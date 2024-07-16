package atlassian.loggerPack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoggerUsingRadixSort {

    /*
     *  bucket stores timestamp , 0 t1, 1 t2,
     *  sets store the actual message, 0 (m1,m2), 1 (m3)
     *
     * */
    private final int[] buckets;
    private final List<Set<String>> sets;

    /**
     * Initialize your data structure here.
     */
    public LoggerUsingRadixSort() {
        buckets = new int[10];
        sets = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            sets.add(new HashSet<>());
        }
    }

    public static void main(String[] args) {

        LoggerUsingRadixSort obj = new LoggerUsingRadixSort();
        System.out.println(obj.shouldPrintMessage(1, "Hi"));
        System.out.println(obj.shouldPrintMessage(1, "Deb"));
        System.out.println(obj.shouldPrintMessage(9, "Hi"));
        System.out.println(obj.shouldPrintMessage(11, "Hi"));
        System.out.println(obj.shouldPrintMessage(12, "Deb"));

    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        int idx = timestamp % 10;
        // if the current timestamp is not same as that of the stored timestamp in the bucket
        if (timestamp != buckets[idx]) {
            sets.get(idx).clear();
            buckets[idx] = timestamp;
        }

        for (int i = 0; i < buckets.length; ++i) {

            if (timestamp - buckets[i] < 10) {
                if (sets.get(i).contains(message)) {
                    return false;
                }
            }
        }
        sets.get(idx).add(message);
        return true;
    }
}