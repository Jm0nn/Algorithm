package com.jm0nn.programmers.lv3.양과늑대;

import java.util.*;

public class Solution {

    private int answer;
    private int[] info;
    Set<Integer>[] child;

    public int solution(int[] info, int[][] edges) {
        answer = 0;

        this.info = info;
        int len = info.length;

        child = new Set[len];

        for (int i = 0; i < len; ++i) child[i] = new HashSet<>();
        for (int i = 0; i < len - 1; ++i) child[edges[i][0]].add(edges[i][1]);

        dfs(0, 0, 0, child[0]);

        return answer;
    }

    private void dfs(int cur, int sheep, int wolf, Set<Integer> move) {
        if (info[cur] == 0) ++sheep;
        else ++wolf;

        if (wolf >= sheep) return;
        if (sheep > answer) answer = sheep;

        Set<Integer> nMove = new HashSet<>();
        nMove.addAll(move);
        nMove.addAll(child[cur]);
        nMove.remove(cur);

        for (int next : nMove) dfs(next, sheep, wolf, nMove);
    }

}
