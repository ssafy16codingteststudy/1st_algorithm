package week2.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15988_123더하기3 {

    private static long[] dp;
    private static final int N_MAX = 1_000_000;
    private static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        dp = new long[N_MAX + 1];

        dp[1] = 1; // (1)
        dp[2] = 2; // (1, 1), (2)
        dp[3] = 4; // (1, 1, 1), (1, 2), (2, 1), (3)

        for (int i = 4; i <= N_MAX; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD; // 마자믹에 +1, +2, +3을 한 경우
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb);
    }
}
