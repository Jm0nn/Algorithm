import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        String answer;
        if (a < b && b < c && c < d) answer = "Fish Rising";
        else if (a > b && b > c && c > d) answer = "Fish Diving";
        else if (a == b && b == c && c == d) answer = "Fish At Constant Depth";
        else answer = "No Fish";
        System.out.print(answer);
    }
}