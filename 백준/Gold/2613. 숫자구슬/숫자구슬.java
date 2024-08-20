import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[] marbles;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        marbles = new int[N];
        int max = 0;
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            int marble = Integer.parseInt(st.nextToken());
            marbles[i] = marble;
            max = Math.max(max, marble);
            sum += marble;
        }

        int left = max;
        int right = sum;

        while (left < right) {
            int mid = (left + right) / 2;

            if (find(mid)) left = mid + 1;
            else right = mid;
        }

        int[] groupCnt = new int[N];
        Arrays.fill(groupCnt, 1);
        boolean[] removed = new boolean[N];

        for (int i = 0; i < N; ++i) {
            int cnt = 0;
            for (boolean r : removed) if (!r) ++cnt;
            if (cnt == M) break;

            if (removed[i]) continue;

            for (int j = i + 1; j < N; ++j) {
                if (removed[j]) break;

                if (marbles[i] + marbles[j] <= left) {
                    removed[j] = true;
                    ++groupCnt[i];
                    marbles[i] += marbles[j];
                } else {
                    break;
                }
            }
        }

        sb.append(left).append('\n');
        for (int i = 0; i < N; ++i) if (!removed[i]) sb.append(groupCnt[i]).append(' ');
        System.out.print(sb);
    }

    private static boolean find(int maxSize) {
        List<Integer> groups = new ArrayList<>();

        int size = 0;
        int sum = 0;

        for (int i = 0; i < N; ++i) {
            if (marbles[i] > maxSize) {
                return false;
            } else if (sum + marbles[i] <= maxSize) {
                ++size;
                sum += marbles[i];
            } else {
                groups.add(size);
                size = 1;
                sum = marbles[i];
            }
        }

        if (size > 0) groups.add(size);

        return groups.size() > M;
    }

}