package com.jm0nn.programmers.lv3.블록이동하기;

import java.util.*;

public class Solution {

    private final int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private int n;
    private int[][] board;
    private boolean[][][][] visit;
    private Queue<int[]> queue;

    public int solution(int[][] board) {
        int answer = 0;

        n = board.length;
        this.board = board;
        visit = new boolean[n][n][n][n];

        queue = new ArrayDeque<>();
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
                answer = time;
                break;
            }

            for (int d = 0; d < 4; ++d) {
                int nx1 = x1 + deltas[d][0];
                int ny1 = y1 + deltas[d][1];
                int nx2 = x2 + deltas[d][0];
                int ny2 = y2 + deltas[d][1];

                offerQueue(nx1, ny1, nx2, ny2, time);
            }

            for (int r = -1; r < 2; r += 2) {
                int nx1, ny1, nx2, ny2;

                if (x1 == x2) {
                    nx1 = x1;
                    ny1 = y1;
                    nx2 = x1 + r;
                    ny2 = y1;

                    if (invalidPos(nx2, y2)) continue;
                } else {
                    nx1 = x1;
                    ny1 = y1;
                    nx2 = x1;
                    ny2 = y1 + r;

                    if (invalidPos(x2, ny2)) continue;
                }

                offerQueue(nx1, ny1, nx2, ny2, time);
            }

            for (int r = -1; r < 2; r += 2) {
                int nx1, ny1, nx2, ny2;

                if (x1 == x2) {
                    nx1 = x2 + r;
                    ny1 = y2;
                    nx2 = x2;
                    ny2 = y2;

                    if (invalidPos(nx1, y1)) continue;
                } else {
                    nx1 = x2;
                    ny1 = y2 + r;
                    nx2 = x2;
                    ny2 = y2;

                    if (invalidPos(x1, ny1)) continue;
                }

                offerQueue(nx1, ny1, nx2, ny2, time);
            }
        }

        return answer;
    }

    private void offerQueue(int x1, int y1, int x2, int y2, int time) {
        if (invalidPos(x1, y1) || invalidPos(x2, y2)) return;

        if (visit[x1][y1][x2][y2] || visit[x2][y2][x1][y1]) return;

        visit[x1][y1][x2][y2] = true;
        visit[x2][y2][x1][y1] = true;
        queue.offer(new int[]{x1, y1, x2, y2, time + 1});
    }

    private boolean invalidPos(int x, int y) {
        return 0 > x || x >= n || 0 > y || y >= n || board[x][y] == 1;
    }

}
