import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char answer = 'S';
        while (st.hasMoreTokens()) {
            if (st.nextToken().equals("9")) {
                answer = 'F';
                break;
            }
        }
        System.out.print(answer);
    }
}