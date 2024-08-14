package com.jm0nn.programmers.lv3.단속카메라;

import java.util.*;

public class Solution {
    public int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int len = routes.length;

        int pos = routes[0][1];

        for (int i = 1; i < len; ++i) {
            int left = routes[i][0];
            int right = routes[i][1];

            if (right < pos) {
                pos = right;
            } else if (left > pos) {
                pos = right;
                ++answer;
            }
        }

        return answer;
    }
}
