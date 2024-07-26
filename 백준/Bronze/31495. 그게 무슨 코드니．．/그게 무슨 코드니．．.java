import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String answer;
        int len = input.length();
        if (len <= 2 || input.charAt(0) != '\"' || input.charAt(len - 1) != '\"') answer = "CE";
        else answer = input.substring(1, len - 1);
        System.out.print(answer);
    }
}