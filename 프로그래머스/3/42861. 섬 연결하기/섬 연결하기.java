import java.util.*;

class Solution {
    
    private int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;

        parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        for (int[] cost : costs) {
            pq.offer(cost);
        }

        while (!pq.isEmpty()) {
            int[] cost = pq.poll();
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];

            if (union(a, b)) {
                answer += c;
            }
        }
        
        return answer;
    }

    private boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x >= y) parent[x] = y;
        else parent[y] = x;

        return true;
    }

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
        
}