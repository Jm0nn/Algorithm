import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        sb.append(":fan::fan::fan:\n:fan::").append(br.readLine()).append("::fan:\n:fan::fan::fan:");
        System.out.print(sb);
    }
}