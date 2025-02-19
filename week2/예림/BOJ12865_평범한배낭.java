package week2.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865_평범한배낭 {

    private static int[] W; // 물건 무게
    private static int[] V; // 물건 가치
    private static int[][] dp; // dp[i][w]: i번째 물건까지 고려했을 때, 배낭 무게 w에서의 최대 가치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물건 수
        int K = Integer.parseInt(st.nextToken()); // 무게
        dp = new int[N + 1][K + 1];

        W = new int[N + 1];
        V = new int[N + 1];
        for (int i = 1; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= K; w++) {
                if (w < W[i]) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - W[i]] + V[i]);
                }
            }
        }

        // 가치 최대값
        System.out.println(dp[N][K]);
}
