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

        int[][] dp = new int[2][N];
        int answer = arr[0];
        dp[0][0] = arr[0];
        dp[1][0] = arr[0];
        for (int i = 1; i < N; i++) {
            dp[0][i] = Math.max(arr[i], dp[0][i - 1] + arr[i]);
            dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + arr[i]);
            answer = Math.max(answer, Math.max(dp[0][i], dp[1][i]));
        }
        System.out.println(answer);
    }
}