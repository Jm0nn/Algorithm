import java.io.*;
import java.util.*;

public class Main {

    private static final int TIME = 3;
    private static final int[] FLAGS = {-1, 0, 1};
    private static final int[] NUMS = new int[TIME];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < TIME; ++i) NUMS[i] = Integer.parseInt(st.nextToken());
        System.out.print(isValid(0, 0, 0) ? 'S' : 'N');
    }

    private static boolean isValid(int idx, int sum, int cnt) {
        if (idx == TIME) return cnt > 0 && sum == 0;

        for (int flag : FLAGS) {
            int nSum = sum + NUMS[idx] * flag;
            int nCnt = flag == 0 ? cnt : cnt + 1;
            if (isValid(idx + 1, nSum, nCnt)) return true;
        }

        return false;
    }

}