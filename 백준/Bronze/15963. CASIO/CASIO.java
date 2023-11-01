import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (st.nextToken().equals(st.nextToken()))
            System.out.print("1");
        else
            System.out.print("0");
    }
}