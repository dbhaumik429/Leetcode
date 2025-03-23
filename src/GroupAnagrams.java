import java.util.*;

public class GroupAnagrams {

    //Categorize by count
    //[a,b,c,d,....] -> [1#1#1#1#...]
    //[a,b,a,c,c] -> [2#1#2# ... ] --
    // so each character will be put into the respective place and the count will just increase
    //key to list of strings here

    public static void groupAnagram(String[] str) {

        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < str.length; i++) {

            String s = str[i];
            char[] chars = s.toCharArray();
            int[] count = new int[26];
            Arrays.fill(count, 0);
            for (int j = 0; j < chars.length; j++) {
                count[chars[j] - 'a']++;
            }

            StringBuilder result = new StringBuilder();
            for (int k = 0; k < count.length; k++) {
                result.append(count[k]);
                result.append("#");// 2#1#2#
            }

            String resultStr = result.toString();
            map.putIfAbsent(resultStr, new ArrayList<>());
            map.get(resultStr).add(s);

        }
        int numberOfGroups = 0;
        for (Map.Entry e : map.entrySet()) {
            var list = (List) e.getValue();
            System.out.print("group " + ++numberOfGroups + " ->");

            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + ", ");
            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagram(str);
    }
}
