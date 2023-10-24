import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        if (P < Q) {
            int tmp = P;
            P = Q;
            Q = tmp;
        }

        int limit = D / P + 1;
        int ans = P * limit;

        for (int i = limit - 1; i >= 0; --i) {
            int div = (D - P * i) / Q;
            int mod = (D - P * i) % Q;

            if (mod == 0) {
                ans = D;
                break;
            }

            int min = P * i + (div + 1) * Q;

            if (ans == min) {
                break;
            }

            ans = Math.min(ans, min);
        }

        System.out.println(ans);
    }

}