package Gonduls.d15;

import Gonduls.d02.Point2d;
import Gonduls.d11.Adjacent;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

// trying to implement singleton pattern
// (did for part 1, turned out to be a mistake I had to work around, fixing it isn't hard but did not want to)
public class PathManager {
    private final int[][] matrix;
    private static PathManager instance = null;

    private PathManager(int[][] matrix){
        this.matrix = matrix;
    }

    public static PathManager getInstance(int[][] matrix){
        if(instance == null)
            instance = new PathManager(matrix);

        return instance;
    }

    // had to implement endpoint mechanism for part 2, not particularly clean
    public int getFinalScore(Point2d endPoint){
        return Dijkstra(endPoint);
    }

    // intellij suggested turning this class into a record, works for me
    private record ElementInQueue(Point2d current, int distanceFrom0) implements Comparable<ElementInQueue> {

        public int compareTo(ElementInQueue other) {
            assert other!=null;
            return distanceFrom0 - other.distanceFrom0; // no overflow problems to worry about
        }

    }


    private int Dijkstra(Point2d endPoint){

        // all the magic happens in this PriorityQueue, and we'll never know how
        PriorityQueue<ElementInQueue> minHeap = new PriorityQueue<>(ElementInQueue::compareTo);

        // visited was a HashMap, but I found out that I only needed to know if I had already been in a point,
        // not how far that point was from the start nor where that point was, just "have I been here?"
        final HashSet<Point2d> visited = new HashSet<>();

        // new Point2d() creates a point in (0,0)
        ElementInQueue current = new ElementInQueue(new Point2d(), 0);
        visited.add(new Point2d());

        while(true){
            // endPoint mechanism at its peak
            List<Point2d> adjacent = Adjacent.getAdjacent(current.current, endPoint.x + 1, endPoint.y +1, false);
            for(Point2d point : adjacent){

                // If I just found a worse way to a point I know there is a better way for: ignore it
                if(visited.contains(point))
                    continue;

                // Did not intend for it to be the only way to find the end, but it works like a charm
                if(point.equals(endPoint))
                    return current.distanceFrom0 + endPoint.valueInMatrix(matrix);


                // All new points found are added to the PriorityQueue, but we did not yet determine what their smallest distance to 0 is
                ElementInQueue next = new ElementInQueue(point, current.distanceFrom0 + point.valueInMatrix(matrix));
                minHeap.add(next);
            }

            // Loop needed: a point can be adjacent to multiple visited points,
            // but can only be found once for the algorithm to work.
            // Smallest distance to the same point is guaranteed by the PriorityQueue structure
            do {
                current = minHeap.poll();
                assert current != null;
            } while (visited.contains(current.current));
            // do while structure suggested by Intellij.

            visited.add(current.current);
        }
    }

}



