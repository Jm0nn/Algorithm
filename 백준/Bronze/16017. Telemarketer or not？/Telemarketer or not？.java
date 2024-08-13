import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        String answer;
        if ((a == 8 || a == 9) && (d == 8 || d == 9) && b == c) answer = "ignore";
        else answer = "answer";
        System.out.print(answer);
    }
}