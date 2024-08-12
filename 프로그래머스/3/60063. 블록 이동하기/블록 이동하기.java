import java.util.*;

public class Solution {

    private int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private int n;
    private int[][] board;
    private boolean[][][][] visit;

    public int solution(int[][] board) {
        int answer = 0;

        n = board.length;
        this.board = board;
        visit = new boolean[n][n][n][n];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0, 1, 0});
        visit[0][0][0][1] = true;
        visit[0][1][0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x1 = cur[0];
            int y1 = cur[1];
            int x2 = cur[2];
            int y2 = cur[3];
            int time = cur[4];

            if ((x1 == n - 1 && y1 == n - 1) || (x2 == n - 1 && y2 == n - 1)) {
                answer = cur[4];
                break;
            }

            for (int d = 0; d < 4; ++d) {
                int nx1 = x1 + deltas[d][0];
                int ny1 = y1 + deltas[d][1];
                int nx2 = x2 + deltas[d][0];
                int ny2 = y2 + deltas[d][1];

                if (!validation(nx1, ny1, nx2, ny2)) continue;

                visit[nx1][ny1][nx2][ny2] = true;
                visit[nx2][ny2][nx1][ny1] = true;
                queue.offer(new int[]{nx1, ny1, nx2, ny2, time + 1});
            }

            for (int r = -1; r < 2; r += 2) {
                int nx1, ny1, nx2, ny2;

                if (x1 == x2) {
                    nx1 = x1;
                    ny1 = y1;
                    nx2 = x1 + r;
                    ny2 = y1;

                    if (0 > nx2 || nx2 >= n || 0 > y2 || y2 >= n)
                        continue;

                    if (board[nx2][y2] == 1)
                        continue;
                } else {
                    nx1 = x1;
                    ny1 = y1;
                    nx2 = x1;
                    ny2 = y1 + r;

                    if (0 > x2 || x2 >= n || 0 > ny2 || ny2 >= n)
                        continue;

                    if (board[x2][ny2] == 1)
                        continue;
                }

                if (!validation(nx1, ny1, nx2, ny2)) continue;

                visit[nx1][ny1][nx2][ny2] = true;
                visit[nx2][ny2][nx1][ny1] = true;
                queue.offer(new int[]{nx1, ny1, nx2, ny2, time + 1});
            }

            for (int r = -1; r < 2; r += 2) {
                int nx1, ny1, nx2, ny2;

                if (x1 == x2) {
                    nx1 = x2 + r;
                    ny1 = y2;
                    nx2 = x2;
                    ny2 = y2;

                    if (0 > nx1 || nx1 >= n || 0 > y1 || y1 >= n)
                        continue;

                    if (board[nx1][y1] == 1)
                        continue;
                } else {
                    nx1 = x2;
                    ny1 = y2 + r;
                    nx2 = x2;
                    ny2 = y2;

                    if (0 > x1 || x1 >= n || 0 > ny1 || ny1 >= n)
                        continue;

                    if (board[x1][ny1] == 1)
                        continue;
                }

                if (!validation(nx1, ny1, nx2, ny2)) continue;

                visit[nx1][ny1][nx2][ny2] = true;
                visit[nx2][ny2][nx1][ny1] = true;
                queue.offer(new int[]{nx1, ny1, nx2, ny2, time + 1});
            }
        }

        return answer;
    }

    private boolean validation(int x1, int y1, int x2, int y2) {
        if (0 > x1 || x1 >= n || 0 > y1 || y1 >= n || 0 > x2 || x2 >= n || 0 > y2 || y2 >= n)
            return false;

        if (board[x1][y1] == 1 || board[x2][y2] == 1)
            return false;

        if (visit[x1][y1][x2][y2] || visit[x2][y2][x1][y1])
            return false;

        return true;
    }

}
