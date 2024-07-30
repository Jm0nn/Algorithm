package com.jm0nn.baekjoon.gold.g5;

import java.io.*;
import java.util.*;

public class G5_23254_나는기말고사형인간이야 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int sum = 0;

        int[][] study = new int[m][2];
        for (int j = 0; j < 2; ++j) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; ++i) {
                study[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Math.min(100 - o2[0], o2[1]) - Math.min(100 - o1[0], o1[1]));
        for (int i = 0; i < m; ++i) {
            pq.offer(study[i]);
            sum += study[i][0];
        }

        int time = 0;

        while (time < 24 * n) {
            int[] cur = pq.poll();

            if (cur[0] >= 100) break;

            int cnt = (100 - cur[0]) / cur[1];

            if (cnt == 0) {
                sum += 100 - cur[0];
                cur[0] += 100 - cur[0];
                ++time;
            } else {
                cnt = Math.min(24 * n - time, cnt);
                sum += cur[1] * cnt;
                cur[0] += cur[1] * cnt;
                time += cnt;
            }

            pq.offer(cur);
        }

        System.out.println(sum);
    }
}
