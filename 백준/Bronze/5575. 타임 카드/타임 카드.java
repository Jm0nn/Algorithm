import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("HH mm ss");
        for (int i = 0; i < 3; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int m1 = Integer.parseInt(st.nextToken());
            int s1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            String start = h1 + " " + m1 + " " + s1;
            String end = h2 + " " + m2 + " " + s2;
            Date date1 = format.parse(start);
            Date date2 = format.parse(end);
            long dif = date2.getTime() - date1.getTime();
            long h = dif / (1000 * 60 * 60);
            long m = (dif / (1000 * 60)) % 60;
            long s = (dif / 1000) % 60;
            sb.append(h).append(' ').append(m).append(' ').append(s).append('\n');
        }
        System.out.print(sb);
    }
}