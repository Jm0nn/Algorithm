import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] wire = new int[n][2];
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            wire[i][0] = Integer.parseInt(st.nextToken());
            wire[i][1] = Integer.parseInt(st.nextToken());
            treeSet.add(wire[i][0]);
        }

        Arrays.sort(wire, (o1, o2) -> o1[1] - o2[1]);

        List<Integer> LIS = new ArrayList<>();
        LIS.add(Integer.MIN_VALUE);
        int[] index = new int[n];

        for (int i = 0; i < n; i++) {
            int key = wire[i][0];

            if (LIS.get(LIS.size() - 1) < key) {
                LIS.add(key);
                index[i] = LIS.size() - 1;
            } else {
                int lo = 1;
                int hi = LIS.size() - 1;
                while (lo < hi) {
                    int mid = (lo + hi) / 2;

                    if (LIS.get(mid) < key)
                        lo = mid + 1;
                    else
                        hi = mid;
                }

                LIS.set(hi, key);
                index[i] = hi;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(n - (LIS.size() - 1)).append('\n');

        int idx = LIS.size() - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (index[i] == idx) {
                treeSet.remove(wire[i][0]);
                --idx;
            }
        }

        for (int i : treeSet)
            sb.append(i).append('\n');

        System.out.println(sb);
    }

}