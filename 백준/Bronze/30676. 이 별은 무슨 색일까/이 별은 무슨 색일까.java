import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String answer;
        if (620 <= n) answer = "Red";
        else if (590 <= n) answer = "Orange";
        else if (570 <= n) answer = "Yellow";
        else if (495 <= n) answer = "Green";
        else if (450 <= n) answer = "Blue";
        else if (425 <= n) answer = "Indigo";
        else answer = "Violet";
        System.out.print(answer);
    }
}