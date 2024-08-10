import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[2][3];
        for (int i = 0; i < 2; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        int sum = 0;
        for (int i = 0; i < 3; ++i) if (arr[0][i] < arr[1][i]) sum += arr[1][i] - arr[0][i];
        System.out.print(sum);
    }
}