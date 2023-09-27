import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class InsertDelGetRandom {

    /* Java program to design a data structure that support following operations
    in Theta(n) time
    a) Insert
    b) Delete
    c) Search
    d) getRandom
     */

    ArrayList<Integer> arr; // A resizable array
    /*
     *  arr - [11,18,32,21]
     * hash - {(18,1), (32,2) ...(11,0)} // O(1)
     *
     *
     * */

    HashMap<Integer, Integer> hash;

    // Constructor (creates arr[] and hash)
    public InsertDelGetRandom() {
        arr = new ArrayList<>();
        hash = new HashMap<>();
    }

    public static void main(String[] args) {
        InsertDelGetRandom ds = new InsertDelGetRandom();
        ds.add(10);
        ds.add(20);
        ds.add(30);
        ds.add(40);
        System.out.println(ds.search(30));
        ds.remove(20);
        ds.add(50);
        // 10 40 30 50
        System.out.println(ds.search(50));
        System.out.println(ds.getRandom());
        System.out.println(ds.search(40));

    }

    void add(int x) {
        // If element is already present, then nothing to do
        if (hash.get(x) != null)
            return;

        // Else put element at the end of arr[]
        int s = arr.size();
        arr.add(x);

        // And put in hash also
        hash.put(x, s);
    }

    // A Theta(1) function to remove an element from MyDS
    // data structure
    void remove(int x) {
        // Check if element is present
        Integer removedIndex = hash.get(x);
        if (removedIndex == null)
            return;
        // If present, then remove element from hash
        hash.remove(x);

        //swp the last element with the removed element
        int lastElem = arr.get(arr.size() - 1);
        arr.set(removedIndex, lastElem);

        //update the hash of the last element
        hash.put(lastElem, removedIndex);

    }

    // Returns a random element from MyDS
    int getRandom() {
        // Find a random index from 0 to size - 1
        Random rand = new Random(); // Choose a different seed
        int index = rand.nextInt(arr.size());

        // Return element at randomly picked index
        return arr.get(index);
    }

    // Returns index of element if element is present, otherwise null
    Integer search(int x) {
        return hash.get(x);
    }
}
