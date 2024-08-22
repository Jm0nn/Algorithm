import java.util.*;

public class Solution {

    private final int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private int cardCnt, result;
    private int[][] board;
    private int[][][] pos;
    private boolean[] remain;

    public int solution(int[][] board, int r, int c) {
        cardCnt = 0;
        result = Integer.MAX_VALUE;
        this.board = board;
        pos = new int[7][2][2];
        remain = new boolean[7];

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                int cardNum = board[i][j];

                if (cardNum == 0) continue;

                if (!remain[cardNum]) {
                    remain[cardNum] = true;
                    ++cardCnt;

                    pos[cardNum][0][0] = i;
                    pos[cardNum][0][1] = j;
                } else {
                    pos[cardNum][1][0] = i;
                    pos[cardNum][1][1] = j;
                }
            }
        }

        dfs(0, 0, r, c);

        return result;
    }

    private void dfs(int cnt, int dist, int r, int c) {
        if (cnt == cardCnt) {
            result = Math.min(result, dist);
            return;
        }

        for (int i = 1; i <= 6; ++i) {
            if (!remain[i]) continue;

            int[] pos1 = pos[i][0];
            int[] pos2 = pos[i][1];

            int dist1 = bfs(r, c, pos1[0], pos1[1])
                    + bfs(pos1[0], pos1[1], pos2[0], pos2[1]);
            int dist2 = bfs(r, c, pos2[0], pos2[1])
                    + bfs(pos2[0], pos2[1], pos1[0], pos1[1]);

            remain[i] = false;

            board[pos1[0]][pos1[1]] = 0;
            board[pos2[0]][pos2[1]] = 0;

            if (dist1 < dist2) dfs(cnt + 1, dist + dist1, pos2[0], pos2[1]);
            else dfs(cnt + 1, dist + dist2, pos1[0], pos1[1]);

            board[pos1[0]][pos1[1]] = i;
            board[pos2[0]][pos2[1]] = i;

            remain[i] = true;
        }
    }

    private int bfs(int sr, int sc, int fr, int fc) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[4][4];

        q.offer(new int[]{sr, sc, 1});
        visit[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];

            if (r == fr && c == fc) return dist;

            for (int d = 0; d < 4; ++d) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];

                if (0 > nr || nr >= 4 || 0 > nc || nc >= 4) continue;
                if (visit[nr][nc]) continue;

                visit[nr][nc] = true;
                q.offer(new int[]{nr, nc, dist + 1});
            }

            for (int d = 0; d < 4; ++d) {
                int nr = r;
                int nc = c;

                while (true) {
                    nr += deltas[d][0];
                    nc += deltas[d][1];

                    if (0 > nr || nr >= 4 || 0 > nc || nc >= 4) {
                        nr -= deltas[d][0];
                        nc -= deltas[d][1];
                        break;
                    }

                    if (board[nr][nc] > 0) break;
                }

                if (visit[nr][nc]) continue;
                
                visit[nr][nc] = true;
                q.offer(new int[]{nr, nc, dist + 1});
            }
        }

        return -1;
    }

}
