import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] word = br.readLine().split("");
        String vowel = "aeiou";
        int cnt = 0;
        for (String s : word) if (vowel.contains(s)) ++cnt;
        System.out.print(cnt);
    }
}