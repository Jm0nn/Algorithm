import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        String answer;
        if (m < 2 || (m == 2 && d < 18)) answer = "Before";
        else if (m > 2 || d > 18) answer = "After";
        else answer = "Special";
        System.out.print(answer);
    }
}