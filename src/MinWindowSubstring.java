import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinWindowSubstring {

    class Pair{
        Integer index;
        Character c;

        public Pair(int i, char c) {
            this.index = i;
            this.c = c;
        }
    }

    private String minWindowSubSubstring(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> requiredCharCounts = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            requiredCharCounts.putIfAbsent(t.charAt(i), 0);
            requiredCharCounts.put(t.charAt(i), requiredCharCounts.get(t.charAt(i)) + 1);
        }

        //filtered -> find out all the required chars and where they exist in S
        List<Pair> filteredS = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if(requiredCharCounts.containsKey(s.charAt(i))){
                filteredS.add(new Pair(i, s.charAt(i)));
            }
        }
        int left =0;
        int right =0;
        Map<Character, Integer> formedCounts = new HashMap<>();
        int formedTillNow = 0;

        // A 0, B 1 , C 2, A 3
        int[] result = new int[3]; //len, st, en
        Arrays.fill(result, -1);

        while( right < filteredS.size()){

            char c = filteredS.get(right).c;
            formedCounts.putIfAbsent(c, 0);
            formedCounts.put(c, formedCounts.get(c) + 1);

            //if(requiredCharCounts.containsKey(c) && formedCounts.get(c).equals(requiredCharCounts.get(c))){
            if(formedCounts.get(c).equals(requiredCharCounts.get(c))){
                formedTillNow++;
            }

            while (left <= right && formedTillNow == requiredCharCounts.size()){

                c = filteredS.get(left).c;

                int start = filteredS.get(left).index;
                int end = filteredS.get(right).index;

                if(result[0] == -1 || (result[0] > end - start + 1)){
                    result[0] = end- start + 1;
                    result[1] = start;
                    result[2] = end;
                }

                formedCounts.put(c, formedCounts.get(c) - 1);

                //if(requiredCharCounts.containsKey(c) && formedCounts.get(c) < requiredCharCounts.get(c))
                if(formedCounts.get(c) < requiredCharCounts.get(c))
                    formedTillNow--;

                left++;
            }

            right++;

        }

        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }

    public static void main(String[] args) {
        String s = "a", t = "aa";


        MinWindowSubstring minWindowSubstring = new MinWindowSubstring();
        System.out.println(minWindowSubstring.minWindowSubSubstring(s,t)); //.equals("BANC");
    }
}
