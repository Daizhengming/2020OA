package com.company;
import java.util.LinkedList;
import java.util.Queue;

public class Result {

    /***
     * Create checkerboard point
     * x  -> x coordinate point
     * y  -> y coordinate point
     */
    private static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     *
     * Determine whether it is out of bounds
     * @param next  ->  Point next move position
     * @param n   ->  Board size
     * @param m   ->  Board size m = n
     * @return    ->  boolean
     */

    private static boolean is_in_bound(Point next, int n, int m) {
        return 0 <= next.x && next.x < n && 0 <= next.y && next.y < m;
    }

    /**
     *
     * @param n
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     * @param bishopRow
     * @param bishopCol
     * @return
     */
    public static int moves(int n, int startRow, int startCol, int endRow, int endCol, int bishopRow, int bishopCol) {

        boolean[][] grid = new boolean[n][n];
        int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
        int[] dy = {2, -2, 1, -1, 2, -2, 1, -1};


        Point source = new Point(startRow, startCol);

        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);
        for (int i = 0; i < n; i++) {
            if (0 <= bishopRow - i && 0 <= bishopCol - i) { grid[bishopRow - i][bishopCol - i] = true; }
            if (0 <= bishopRow - i && bishopCol + i < n) { grid[bishopRow - i][bishopCol + i] = true; }
            if (bishopRow + i < n && bishopCol + i < n ) { grid[bishopRow + i][bishopCol + i] = true; }
            if (bishopRow + i < n && 0 < bishopCol - i) { grid[bishopRow + i][bishopCol - i] = true; }
        }

        grid[endRow][endCol] = false;           // Prevent the end point on the path of the bishop
        grid[bishopRow][bishopCol] = false;     // Allowed to kill the bishop
        int ans = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Point cur = queue.poll();

                if (cur.x == endRow && cur.y == endCol) {
                    return ans;       // Reach the end, return distance
                }

                // Exterminate the bishop
                if (cur.x == bishopRow && cur.y == bishopCol){
                    for (int i = 0; i < n; i++) {
                        if (0 <= bishopRow - i && 0 <= bishopCol - i) { grid[bishopRow - i][bishopCol - i] = false; }
                        if (0 <= bishopRow - i && bishopCol + i < n) { grid[bishopRow - i][bishopCol + i] = false; }
                        if (bishopRow + i < n && bishopCol + i < n ) { grid[bishopRow + i][bishopCol + i] = false; }
                        if (bishopRow + i < n && 0 < bishopCol - i) { grid[bishopRow + i][bishopCol - i] = false; }
                    }
                }

                for (int i = 0; i < 8; i++) {

                    Point next = new Point(
                            cur.x + dx[i],
                            cur.y + dy[i]
                    );
                    // Judge whether it is reachable
                    if (is_in_bound(next, n, n) && grid[next.x][next.y] == false) {
                        queue.offer(next);
                        grid[next.x][next.y] = true;
                    }
                }
            }
            ans++;
        }
        return -1;
    }
}
