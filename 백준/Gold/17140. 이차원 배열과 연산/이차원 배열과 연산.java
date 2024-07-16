import java.io.*;
import java.util.*;

public class Main {

    static int MAX = 100;
    static int R_SIZE = 3;
    static int C_SIZE = 3;

    static int[][] arr = new int[MAX + 1][MAX + 1];

    static class Item implements Comparable<Item> {
        int value;
        int count;

        Item(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Item o) {
            return this.count != o.count ? this.count - o.count : this.value - o.value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= R_SIZE; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C_SIZE; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = -1;
        int time = 0;

        while (true) {
            if (arr[r][c] == k) {
                ans = time;
                break;
            }

            if (time > 100) {
                break;
            }

            if (R_SIZE >= C_SIZE) {
                R();
            } else {
                C();
            }

            ++time;
        }

        System.out.println(ans);
    }

    static void R() {
        int maxSize = 0;

        for (int i = 1; i <= R_SIZE; ++i) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = 1; j <= C_SIZE; ++j) {
                if (arr[i][j] == 0) {
                    continue;
                }

                if (map.containsKey(arr[i][j])) {
                    map.put(arr[i][j], map.get(arr[i][j]) + 1);
                } else {
                    map.put(arr[i][j], 1);
                }

                if (map.size() >= 50) {
                    break;
                }
            }

            if (map.size() * 2 > maxSize) {
                maxSize = map.size() * 2;
            }

            PriorityQueue<Item> pq = new PriorityQueue<>();

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                pq.add(new Item(entry.getKey(), entry.getValue()));
            }

            int idx = 1;

            while (!pq.isEmpty()) {
                Item item = pq.poll();
                arr[i][idx++] = item.value;
                arr[i][idx++] = item.count;
            }

            for (int j = idx; j <= MAX; ++j) {
                arr[i][j] = 0;
            }
        }

        C_SIZE = maxSize;
    }

    static void C() {
        int maxSize = 0;

        for (int i = 1; i <= C_SIZE; ++i) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = 1; j <= R_SIZE; ++j) {
                if (arr[j][i] == 0) {
                    continue;
                }

                if (map.containsKey(arr[j][i])) {
                    map.put(arr[j][i], map.get(arr[j][i]) + 1);
                } else {
                    map.put(arr[j][i], 1);
                }

                if (map.size() >= 50) {
                    break;
                }
            }

            if (map.size() * 2 > maxSize) {
                maxSize = map.size() * 2;
            }

            PriorityQueue<Item> pq = new PriorityQueue<>();

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                pq.add(new Item(entry.getKey(), entry.getValue()));
            }

            int idx = 1;

            while (!pq.isEmpty()) {
                Item item = pq.poll();
                arr[idx++][i] = item.value;
                arr[idx++][i] = item.count;
            }

            for (int j = idx; j <= MAX; ++j) {
                arr[j][i] = 0;
            }
        }

        R_SIZE = maxSize;
    }

}