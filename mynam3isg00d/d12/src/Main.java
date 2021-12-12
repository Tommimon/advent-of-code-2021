package mynam3isg00d.d12.src;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        Graph grp = r.parseInput("input.txt");
        int part1;

        //visitAll recursively visits every node of the graph remembering the previously visited in the "visited"
        //array list and the small caves, which become inaccessible, in the "smallVisited" array list.
        grp.visitAll(grp.inGraph("start"),null, null);
        part1 = grp.numOfPaths;
        System.out.println("\nPart 1: " + part1);

        //Interesting math: visitAll2 adds an exception node which can be visited twice (exV tells if
        //the *ex*ception has been NOT been *V*isisted before, shitty name i know). The result (numOfPaths)
        //is the number of paths found in part 1 plus the number of paths given by this exception
        //therefore, if we subtract the original number of paths we only get the paths created by the exception.
        //Of course, we want to count the original paths as well at least once so we add them to the result.
        grp.numOfPaths = 0;
        for(Node n : grp.nodes) {
            if (!(grp.bigCaves.contains(n)) && !n.name.equals("start") && !n.name.equals("end")) {
                //small node, exception found
                grp.visitAll2(grp.inGraph("start"), n, null, null, true);
                grp.numOfPaths -= part1;
            }
        }
        System.out.println("\nPart 2: " + (grp.numOfPaths + part1));
    }
}
