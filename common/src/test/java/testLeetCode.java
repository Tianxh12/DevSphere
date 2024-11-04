import java.util.Arrays;
import java.util.PriorityQueue;

public class testLeetCode {
    private final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public  static void main(String[] args) {
        int[][] moveTime = {{0, 1}, {1, 2}};
        System.out.println(new testLeetCode().minTimeToReach(moveTime));
    }

    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;
        int[][] dis = new int[n][m];
        for (int[] row : dis) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dis[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0, 0});
        for (;;) {
            int[] p = pq.poll();
            int d = p[0], i = p[1], j = p[2];
            if (i == n - 1 && j == m - 1) {
                return d;
            }
            if (d > dis[i][j]) {
                continue;
            }
            for (int[] q : DIRS) {
                int x = i + q[0], y = j + q[1];
                if (0 <= x && x < n && 0 <= y && y < m) {
                    int newDis = Math.max(d, moveTime[x][y]) + (i + j) % 2 + 1;
                    if (newDis < dis[x][y]) {
                        dis[x][y] = newDis;
                        pq.add(new int[]{newDis, x, y});
                    }
                }
            }
        }


    }
}
