import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final String v = "aeiouAEIOU";
        String input;
        while (!(input = br.readLine()).equals("#")) {
            int len = input.length();
            int cnt = 0;
            for (int i = 0; i < len; ++i) {
                if (v.contains(String.valueOf(input.charAt(i))))
                    ++cnt;
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
}