package week4.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7579_앱 {

    // 앱을 비활성하면 비용이 들고, 그만큼 메모리 확보 가능
    // 최소한의 비용으로 목표 메모리 확보 -> 배낭 문제
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 앱의 수
        int M = Integer.parseInt(st.nextToken()); // 확보해야 할 메모리

        int[] memories = new int[N]; // 각 앱의 메모리
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        int[] costs = new int[N]; // 각 앱의 비용
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int maxCost = Arrays.stream(costs).sum();
        int[] dp = new int[maxCost + 1]; // dp[c]: 비용 c로 확보할 수 있는 최대 메모리

        for (int i = 0; i < N; i++) {
            for (int c = maxCost; c >= costs[i]; c--) {
                dp[c] = Math.max(dp[c], dp[c - costs[i]] + memories[i]);
            }
        }

        for (int c = 0; c <= maxCost; c++) {
            if (dp[c] >= M) {
                System.out.println(c);
                break;
            }
        }
    }
}
