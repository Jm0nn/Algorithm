import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double a1 = Double.parseDouble(st.nextToken());
        double p1 = Double.parseDouble(st.nextToken());
        st = new StringTokenizer(br.readLine());
        double r1 = Double.parseDouble(st.nextToken());
        double p2 = Double.parseDouble(st.nextToken());
        double slice = a1 / p1;
        double whole = Math.PI * r1 * r1 / p2;
        String answer = slice > whole ? "Slice of pizza" : "Whole pizza";
        System.out.print(answer);
    }
}