package com.jm0nn.programmers.lv3.외벽점검;

import java.util.*;

public class Solution {

    private int n;
    private int weakLen;
    private int[] weak;
    private int[] repaired;
    private List<Integer> workers;

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;

        weakLen = weak.length;
        int distLen = dist.length;

        repaired = new int[weakLen];
        workers = new ArrayList<>();

        Arrays.sort(dist);

        for (int i = distLen - 1; i >= 0; --i) {
            workers.add(dist[i]);
            if (repairable(0)) return workers.size();
        }

        return -1;
    }

    private boolean repairable(int idx) {
        if (idx == workers.size()) return chkRepaired();

        for (int i = 0; i < weakLen; ++i) {
            if (repaired[i] > 0) continue;
            doRepair(workers.get(idx), i);
            if (repairable(idx + 1)) return true;
            unRepair(workers.get(idx), i);
        }

        return false;
    }

    private boolean chkRepaired() {
        for (int r : repaired) if (r == 0) return false;
        return true;
    }

    private void doRepair(int dist, int idx) {
        repair(dist, idx, 1);
    }

    private void unRepair(int dist, int idx) {
        repair(dist, idx, -1);
    }

    private void repair(int dist, int idx, int flag) {
        repaired[idx] += flag;
        int len = 0;
        for (int i = afterIdx(idx); i != idx; i = afterIdx(i)) {
            len += calcLen(weak[i], weak[beforeIdx(i)]);
            if (len > dist) break;
            repaired[i] += flag;
        }
    }

    private int afterIdx(int idx) {
        return (idx + 1) % weakLen;
    }

    private int beforeIdx(int idx) {
        return (idx - 1) < 0 ? weakLen - 1 : idx - 1;
    }

    private int calcLen(int front, int back) {
        if (back > front) return (n - back) + front;
        return front - back;
    }

}
