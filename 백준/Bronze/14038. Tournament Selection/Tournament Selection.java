import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        for (int i = 0; i < 6; ++i) if ("W".equals(br.readLine())) ++cnt;
        int answer;
        if (cnt > 4) answer = 1;
        else if (cnt > 2) answer = 2;
        else if (cnt > 0) answer = 3;
        else answer = -1;
        System.out.print(answer);
    }
}