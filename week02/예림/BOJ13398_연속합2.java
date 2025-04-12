package week2.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13398_연속합2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][2];
        int max = arr[0];
        dp[0][0] = arr[0];
        dp[0][1] = arr[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]); // 이때까지 삭제 X (이전 값 + 새로운 값, 새로 시작)
            dp[i][1] = Math.max(dp[i - 1][1] + arr[i], dp[i - 1][0]); // 이때까지 삭제 O (이미 삭제 + 새로운 값, 이번에 삭제)

            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(max);
    }
}
