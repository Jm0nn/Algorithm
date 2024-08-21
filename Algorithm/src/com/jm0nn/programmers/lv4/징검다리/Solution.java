package com.jm0nn.programmers.lv4.징검다리;

import java.util.*;

public class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        int len = rocks.length + 1;

        int[] tmpRocks = new int[len];
        System.arraycopy(rocks, 0, tmpRocks, 0, len - 1);
        tmpRocks[len - 1] = distance;
        rocks = tmpRocks;

        Arrays.sort(rocks);

        int left = 0;
        int right = distance;

        while (left <= right) {
            int mid = (left + right) / 2;

            int cur = 0;
            int cnt = 0;

            for (int rock : rocks) {
                if (rock - cur < mid) ++cnt;
                else cur = rock;
            }

            if (cnt > n) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }
}
