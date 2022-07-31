package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaTest {

    private static void printArr(int[] a){
        for(int i=0; i< a.length; i++){
            System.out.print(a[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

       int[] one = new int[2];
       int[] two = new int[2];

        Arrays.fill(one, Integer.MAX_VALUE);
        Arrays.fill(two, Integer.MAX_VALUE);

        printArr(one);
        printArr(two);

        for(int i=0; i< 5;i++){

            one[i & 1]= 0;
            one[1-(i & 1)] = 1;

        }


    }
}
