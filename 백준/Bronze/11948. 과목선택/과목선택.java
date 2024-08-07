import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] science = new int[4];
        int[] society = new int[2];
        science[0] = Integer.parseInt(br.readLine());
        science[1] = Integer.parseInt(br.readLine());
        science[2] = Integer.parseInt(br.readLine());
        science[3] = Integer.parseInt(br.readLine());
        society[0] = Integer.parseInt(br.readLine());
        society[1] = Integer.parseInt(br.readLine());
        Arrays.sort(science);
        int sum = science[1] + science[2] + science[3] + Math.max(society[0], society[1]);
        System.out.print(sum);
    }
}