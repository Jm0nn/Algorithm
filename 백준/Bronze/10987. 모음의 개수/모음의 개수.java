import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        String vowel = "aeiou";
        int cnt = 0;
        for (char c : word) if (vowel.contains(String.valueOf(c))) ++cnt;
        System.out.print(cnt);
    }
}