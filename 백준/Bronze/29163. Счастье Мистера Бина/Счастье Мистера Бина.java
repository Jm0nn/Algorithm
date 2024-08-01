import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int even = 0, odd = 0;
        for (int i = 0; i < n; ++i) {
            int num = Integer.parseInt(st.nextToken());
            if (num % 2 == 0) ++even;
            else ++odd;
        }
        System.out.print(even > odd ? "Happy" : "Sad");
    }
}