import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] p = br.readLine().split(" ");
        String[] apple = br.readLine().split(" ");
        int answer = 0;
        for (int i = 0; i < 4; ++i) if (p[i].equals(apple[0])) answer = i + 1;
        System.out.print(answer);
    }
}