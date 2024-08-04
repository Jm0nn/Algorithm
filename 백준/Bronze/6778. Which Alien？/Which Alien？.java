import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int ant = Integer.parseInt(br.readLine());
        int eye = Integer.parseInt(br.readLine());
        if (ant >= 3 && eye <= 4) sb.append("TroyMartian\n");
        if (ant <= 6 && eye >= 2) sb.append("VladSaturnian\n");
        if (ant <= 2 && eye <= 3) sb.append("GraemeMercurian");
        System.out.print(sb);
    }
}