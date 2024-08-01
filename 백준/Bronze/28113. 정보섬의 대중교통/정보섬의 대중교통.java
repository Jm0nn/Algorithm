import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        String answer;
        if (a == b && n <= b) {
            answer = "Anything";
        } else if (a > b && n <= b) {
            answer = "Subway";
        } else {
            answer = "Bus";
        }
        System.out.print(answer);
    }
}