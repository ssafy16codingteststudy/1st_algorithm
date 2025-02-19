package 용범;

import java.io.*;
import java.util.*;

public class BOJ13398_연속합2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] nums;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        init();

        solve();

    }

    private static void solve() {

        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1] + nums[i], nums[i]);
            dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + nums[i]);
        }

        int MAX = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            MAX = Math.max(MAX, dp[0][i]);
            MAX = Math.max(MAX, dp[1][i]);
        }

        System.out.println(MAX);
    }


    private static void init() throws IOException {

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        // dp 초기화
        dp = new int[2][n];
        dp[0][0] = nums[0];
        dp[1][0] = nums[0];

        br.close();
    }
}
