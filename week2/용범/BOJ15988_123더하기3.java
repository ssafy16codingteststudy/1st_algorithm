package 용범;

import java.io.*;
import java.util.*;

public class BOJ15988_123더하기3 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int T, N, MAX = 1_000_000;
    static long MOD = 1_000_000_009;
    static long[] dp = new long[MAX + 1];

    public static void main(String[] args) throws IOException {

        setUp();

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void setUp() {

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= MAX; i++)
            dp[i] = (dp[i - 3] + dp[i - 2] + dp[i - 1]) % MOD;
    }
}
