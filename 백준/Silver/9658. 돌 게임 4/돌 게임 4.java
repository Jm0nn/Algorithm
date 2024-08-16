import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String answer;
        if (n % 7 == 1 || n % 7 == 3) answer = "CY";
        else answer = "SK";
        System.out.print(answer);
    }
}
