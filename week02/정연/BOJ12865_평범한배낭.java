import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] W, V;

    public static void main(String[] args) throws IOException {
        int[][] dp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        W = new int[N + 1];
        V = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * arr[n][k] = Math.max(arr[n-1][k], arr[n][k-last.w]+last.v)
         */

        dp = new int[N + 1][K + 1];

        for (int n = 1; n < N + 1; n++) {
            for (int k = 1; k < K + 1; k++) {
                if (k - W[n] < 0) {
                    dp[n][k] = dp[n - 1][k];
                } else {
                    dp[n][k] = Math.max(dp[n - 1][k], dp[n - 1][k - W[n]] + V[n]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }

}
