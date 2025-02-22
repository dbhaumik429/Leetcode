package elementsOfProgrammingInterview;

/*
* 25.14 COMPUTE FAIR BONUSES &
You manage a team of developers. You have to give concert tickets as a bonus to
the developers. For each developer, you know how many lines of code he wrote the
previous week, and you want to reward more productive developers.
The developers sit in a row. Each developer, save for the first and last, has two
neighbors. You must give each developer one or more tickets in such a way that if
a developer has written more lines of code than a neighbor, then he receives more
tickets than his neighbor.

* Your task isto develop an algorithm that computes the minimum number of tickets
you need to buy to satisfy the constraint. For example, if Andy, Bob, Charlie, and
David sit in a row from left to right, and they wrote 300, 400, 500, and 200 lines of
code, respectively, the previous week, then Andy and David should receive one ticket

each, Bob should receive two tickets, and Charlie should receive three tickets, for a
total of seven tickets.
Write a program for computing the minimum number of tickets to distribute to the
developers, while ensuring that if a developer has written more lines of code than a
neighbor, then he receives more tickets than his neighbor.

* Hint:Consider iteratively improving an assignment that may notsatisfy the constraint
*
* [300,400,500,200]
* [1,2,3,1] -> 7
*
* 100, 200, 300, 400
* Left pass
* assign 1, move to the right, add 1 more if the number is > the prev one, else reset to 1
*
* Right pass
* [400,300,200] - decreasing order - right to left should be traversed
* 3 2 1
*
*
* * */

import java.util.List;

public class DistributeFairBonus {

    public static void main(String[] args) {
        //List<Integer> numberOfLinesArr = Arrays.asList(300, 400, 500, 200);- 1, 2, 3, 1,
        //List<Integer> numberOfLinesArr = Arrays.asList(100, 200, 300, 400); //- 1,2,3,4
        //List<Integer> numberOfLinesArr = Arrays.asList(500, 400, 300, 200); // 4,3,2,1
        //List<Integer> numberOfLinesArr = List.of(500); // 1
        //List<Integer> numberOfLinesArr = List.of(); //
        List<Integer> numberOfLinesArr = List.of(300, 200, 100, 600); // 3,2,1,2

        int[] res = distributeBonus(numberOfLinesArr);

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + ", ");
        }
    }

    private static int[] distributeBonus(List<Integer> numberOfLinesArr) {

        if (numberOfLinesArr.isEmpty())
            return new int[0];

        if (numberOfLinesArr.size() == 1) {
            int[] result = new int[1];
            result[0] = 1;
            return result;
        }

        int[] result = new int[numberOfLinesArr.size()];
        int defaultBonus = 1;
        result[0] = defaultBonus;

        //left pass
        for (int i = 1; i < numberOfLinesArr.size(); i++) {
            result[i] = defaultBonus;
            if (numberOfLinesArr.get(i) > numberOfLinesArr.get(i - 1)) {
                result[i] = result[i - 1] + 1;
            }
        }

        //right pass
        for (int i = numberOfLinesArr.size() - 2; i >= 0; i--) {

            if (numberOfLinesArr.get(i) > numberOfLinesArr.get(i + 1)) {
                result[i] = Math.max(result[i], result[i + 1] + 1);
            }
        }

        return result;
    }


}
