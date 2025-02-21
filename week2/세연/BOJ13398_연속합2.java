import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[2][N]; //dp[0]: 모든 수를 배열에서 제외하지 않음, dp[1] : 하나의 수는 제외한 경우 고려
        int answer = arr[0];
        dp[0][0] = arr[0];
        dp[1][0] = arr[0];
        for (int i = 1; i < N; i++) {
            dp[0][i] = Math.max(arr[i], dp[0][i - 1] + arr[i]); // 현재의 수부터 새로 합하기 vs 이전까지의 최대합 합하기
            dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + arr[i]); // 지금 걸 제외했을 때의 최대 합 vs 이미 하나를 제외했을 때의 최대 합 
            answer = Math.max(answer, Math.max(dp[0][i], dp[1][i])); // 최대 합 업데이트
        }
        System.out.println(answer);
    }
}