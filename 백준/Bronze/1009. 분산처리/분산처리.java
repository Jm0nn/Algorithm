import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int[] num2 = { 6, 2, 4, 8 };
        int[] num3 = { 1, 3, 9, 7 };
        int[] num4 = { 6, 4 };
        int[] num7 = { 1, 7, 9, 3 };
        int[] num8 = { 6, 8, 4, 2 };
        int[] num9 = { 1, 9 };
        
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            switch (a % 10) {
                case 0:
                    sb.append(10);
                    break;
                case 1:
                    sb.append(1);
                    break;
                case 2:
                    sb.append(num2[b % 4]);
                    break;
                case 3:
                    sb.append(num3[b % 4]);
                    break;
                case 4:
                    sb.append(num4[b % 2]);
                    break;
                case 5:
                    sb.append(5);
                    break;
                case 6:
                    sb.append(6);
                    break;
                case 7:
                    sb.append(num7[b % 4]);
                    break;
                case 8:
                    sb.append(num8[b % 4]);
                    break;
                case 9:
                    sb.append(num9[b % 2]);
                    break;
            }
            sb.append('\n');
        }
        
        System.out.println(sb);
        
        br.close();
    }
}