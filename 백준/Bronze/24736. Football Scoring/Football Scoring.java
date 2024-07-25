import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] score = new int[2][5];
        for (int i = 0; i < 2; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; ++j) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; ++i) {
            int s = 0;
            s += score[i][0] * 6;
            s += score[i][1] * 3;
            s += score[i][2] * 2;
            s += score[i][3];
            s += score[i][4] * 2;
            sb.append(s).append(' ');
        }
        
        System.out.print(sb);
    }
}