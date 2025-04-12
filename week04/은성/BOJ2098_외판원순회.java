package codingTestStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2098_외판원순회 {

    private static final long INF  = 16_000_000;
    private static int n;
    private static int[][] arr;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new long[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(backTracking(0, 1));
    }

    private static long backTracking(int now, int visited) {
        // 모든곳을 순회했을 때는 출발 지점으로 돌아오는 값 반환
        if (visited == (1 << n) - 1) {
            // 0이면 갈 수 없으므로 INF 반환
            if (arr[now][0] == 0) {
                return INF;
            }
            return arr[now][0];
        }

        // 이미 값이 정의되어 있다면 그 값 반환
        if (dp[now][visited] != -1) {
            return dp[now][visited];
        }

        dp[now][visited] = INF;

        for (int i = 0; i < n; i++) {
            // 방문한 적 있거나 값이 0이면 통과
            if ((visited & 1 << i) != 0 || arr[now][i] == 0) {
                continue;
            }

            // 현재 도시에서 다음 도시로 가는 것들중 최소
            dp[now][visited] = Math.min(dp[now][visited] , arr[now][i] + backTracking(i, visited | 1 << i));
        }

        return dp[now][visited];
    }
}
