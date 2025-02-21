package 용범;

import java.io.*;
import java.util.*;

public class BOJ2631_줄세우기 {

    static int N, num;
    static int[] child;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        init();

        solve();

    }

    private static void solve() {

        int MAX = 0;
        // LIS(최장 증가 부분 수열) -> O(N^2) 방식
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                // 자신보다 숫자가 작은 경우에만 -> dp 점화식 적용
                if (child[j] < child[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            MAX = Math.max(MAX, dp[i]);
        }

        System.out.println(N - MAX);
    }

    private static void init() throws IOException {

        // 입력 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        child = new int[N];
        // dp 초기화
        dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());
            child[i] = num;
        }

        br.close();
    }
}
