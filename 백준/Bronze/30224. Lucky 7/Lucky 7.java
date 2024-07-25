import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        String input = br.readLine();
        if (input.contains("7")) answer += 2;
        if (Integer.parseInt(input) % 7 == 0) ++answer;
        System.out.print(answer);
    }
}