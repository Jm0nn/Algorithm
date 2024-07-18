import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

        int reqAlp = 0;
        int reqCop = 0;

        for (int[] p : problems) {
            if (reqAlp < p[0]) reqAlp = p[0];
            if (reqCop < p[1]) reqCop = p[1];
        }

        if (alp > reqAlp) {
            alp = reqAlp;
        }

        if (cop > reqCop) {
            cop = reqCop;
        }

        if (alp == reqAlp && cop == reqCop) {
            return answer;
        }

        int len = problems.length;

        int[][] newProblems = new int[len + 2][5];
        for (int i = 0; i < len; ++i) {
            newProblems[i] = problems[i];
        }
        newProblems[len] = new int[]{0, 0, 0, 1, 1};
        newProblems[len + 1] = new int[]{0, 0, 1, 0, 1};
        problems = newProblems;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        boolean[][][] visited = new boolean[301][151][151];
        pq.offer(new int[]{0, alp, cop});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int time = cur[0];
            int curAlp = Math.min(cur[1], reqAlp);
            int curCop = Math.min(cur[2], reqCop);

            if (visited[time][curAlp][curCop]) {
                continue;
            }

            visited[time][curAlp][curCop] = true;

            if (curAlp == reqAlp && curCop == reqCop) {
                answer = time;
                break;
            }

            for (int[] p : problems) {
                int alpReq = p[0];
                int copReq = p[1];
                int alpRwd = p[2];
                int copRwd = p[3];
                int cost = p[4];

                if (alpRwd == 0 && copRwd == 0) {
                    continue;
                }

                if (curAlp >= alpReq && curCop >= copReq) {
                    pq.offer(new int[]{time + cost, curAlp + alpRwd, curCop + copRwd});
                }
            }
        }

        return answer;
    }
}