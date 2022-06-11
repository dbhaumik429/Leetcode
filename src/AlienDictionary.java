import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class AlienDictionary {

    public static String alientDict(String[] words){

        // w -> r -> t
        // w -> r -> f
        // w -> r -> t -> f

        // w -> r
        // r -> {t,f}
        // e -> {r, t}
        // r -> f

        Map<Character,Integer> indegree = new TreeMap<>();
        Map<Character, Set<Character>> map = new HashMap<>();

        StringBuilder result = new StringBuilder();

        for(int i=0; i< words.length; i++) {
            //indegree
            //w, e
            // for each words
            String word = words[i];
            for (int j = 0; j < word.length() - 1; j++) {

                indegree.putIfAbsent(word.charAt(j), 0);

                if (word.charAt(j) != word.charAt(j + 1)) {

                    indegree.putIfAbsent(word.charAt(j + 1), 0);

                    int count = indegree.get(word.charAt(j+1));
                    indegree.put(word.charAt(j + 1), ++count);

                    // w -> r
                    Character currChar = word.charAt(j);
                    map.putIfAbsent(currChar, new HashSet<>());

                    Set<Character> characters = map.get(currChar);
                    characters.add(word.charAt(j + 1));
                    map.put(currChar, characters);
                }
            }
        }
            Queue<Character> queue = new LinkedList<>();
            for(Map.Entry e: indegree.entrySet()){

                if((Integer)e.getValue() == 0 )
                    queue.add((Character) e.getKey());

            }

        System.out.println(queue);
        System.out.println(map);

        boolean[] visited = new boolean[26];
        Arrays.fill(visited,false);

            while(!queue.isEmpty()){

                Character c = queue.poll();
                if(!visited[c - 'a'])
                {
                    visited[c - 'a'] = true;
                    result.append(c);
                }
                Set<Character> neigh = map.get(c);

                if(neigh!=null) {
                    for(Character n: neigh){
                        queue.add(n);
                    }
                }
            }

        return  result.toString();
    }

    public static void main(String[] args) {
        String[] words = {"wrt","wrf","er","ett","rftt"};

        System.out.println(alientDict(words));// "wertf"
    }
}
