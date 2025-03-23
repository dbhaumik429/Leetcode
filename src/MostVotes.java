import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MostVotes {
    //https://leetcode.com/problems/rank-teams-by-votes/description/

    public static String rankTeams(String[] votes) {

        //if you have 3 teams you have 3 positions
        // A got 5 times 0 position - that it is rank [ 5,0, 0]
        // B got 2 times 2nd position and 3 times 3rd position - [0,2, 3]
        // C got 3 times 2nd position and 2 times 3rd position - [0,3, 2]
        //A [5,0,0]
        //B [0,2,3]
        //C [0,3,2]

        //now we can traverse
        // A got most, so rank 1
        // C got more than B, so rank 2
        // B got least, so rank 3
        Map<Character, int[]> teamNumberOfVotesAndPositionMap = new HashMap<>();
        for (int i = 0; i < votes.length; i++) {
            String vote = votes[i];

            for (int j = 0; j < vote.length(); j++) {
                Character teamName = vote.charAt(j);
                teamNumberOfVotesAndPositionMap.putIfAbsent(teamName, new int[vote.length()]);
                //at this position, this team got this much vote
                teamNumberOfVotesAndPositionMap.get(teamName)[j]++;
            }
        }
        //Sort the various arrays created now//
        Set<Character> teamSet = teamNumberOfVotesAndPositionMap.keySet();
        var sortedList = teamSet.stream().sorted((a, b) -> {
            //[5,0,0] and [0,2,3] and [0,3,2]
            //When 2 arrays are compared, it should break at the first mismatched
            // So if A is compared with B, at position 0 A have 5 votes and B have 0 - so A > B
            // WHen A and C is compared, again A have 5 and C has 0 - so  A > C - hence A is the first one
            //When B and C are compared, both have 0 at index 0, so we go to the next index
            //now we see B have 2 compared to C have 3 - hence C s ahead of B and returned back.
            //Nothing else to compare here.
            int len = teamNumberOfVotesAndPositionMap.get(a).length;

            for (int i = 0; i < len; i++) {
                if (teamNumberOfVotesAndPositionMap.get(a)[i] != teamNumberOfVotesAndPositionMap.get(b)[i]) {
                    //important that you put a -1 * (condition to check) to sort the list in decreasing order
                    return -1 * (teamNumberOfVotesAndPositionMap.get(a)[i] - teamNumberOfVotesAndPositionMap.get(b)[i]);
                }
            }
            //just lexicographically whichever is longer
            return a.compareTo(b);
        }).toList();

        StringBuilder rank = new StringBuilder();
        for (Character c : sortedList) {
            rank.append(c);
        }

        return rank.toString();
    }

    public static void main(String[] args) {
        String[] votes = {"ABC", "ACB", "ABC", "ACB", "ACB"};
        System.out.println(rankTeams(votes));
    }

}
