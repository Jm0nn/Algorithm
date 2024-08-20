import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String e = input.substring(1, input.length() - 1);
        System.out.println('h' + e + e + 'y');
    }
}