import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int a = 0;
        int b = 0;
        for (int i = 0; i < input.length; i += 2) {
            if (input[i] == 'A') a += input[i + 1];
            else b += input[i + 1];
        }
        System.out.print(a > b ? 'A' : 'B');
    }
}