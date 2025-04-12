package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ25634 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] bright = new int[N];
        int[] state = new int[N];
        // 뒤집힐때 얻을 수 있는 수치
        int[] dp = new int[N];

        // 전구 밝기
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            bright[i] = Integer.parseInt(input[i]);
        }
        // 전구 상태
        input = br.readLine().split(" ");
        long initialSum = 0; //초기
        for (int i = 0; i < N; i++) {
            state[i] = Integer.parseInt(input[i]);
            if(state[i] == 1){ // 켜져있다면 밝기를 저장
                initialSum += bright[i];
                dp[i] = -bright[i]; //dp 배열에는 꺼졌을때의 값
            } else {
                dp[i] = bright[i]; //dp 배열에는 켜졌을때의 값
            }
        }

        // Kadane 알고리즘
        int currentSum = dp[0]; //지금까지의 연속구간의 합
        int maxSum = dp[0]; //지금까지 찾은 구간 중 최고합
        for (int i = 1; i < N; i++) {
            currentSum = Math.max(dp[i], currentSum + dp[i]);
            // 예시를 기준으로
            // 1번째 구간까지의 합
            // Math.max(2, -3 + 2) //스위치를 돌린 값이 더 좋으니까 2선택
            // 2번째 구간까지의 합
            // Math.max(-5, 2 + (-5)
            // 결론은 구간합에서 최대였던건 2
            maxSum = Math.max(maxSum, currentSum);
        }
        // 그래서 기존 초기값에서 2만큼을 더해주면 할 수있는 최선
        System.out.println(initialSum + maxSum);

    }
}
