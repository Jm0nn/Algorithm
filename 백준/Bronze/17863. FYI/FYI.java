import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();
        String answer;
        if (number.startsWith("555")) answer = "YES";
        else answer = "NO";
        System.out.print(answer);
    }
}