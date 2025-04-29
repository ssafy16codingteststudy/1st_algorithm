import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    private static int N;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean checkPrime(int x) {
        if (x == 1) return false;
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    private static void dfs(int x, int length) {
        if (length == N) {
            sb.append(x).append('\n');
            return;
        }

        for (int i = 1; i < 10; i++) {
            int tmp = x * 10 + i;
            if (checkPrime(tmp)) {
                dfs(tmp, length + 1);
            }
        }
    }
}
