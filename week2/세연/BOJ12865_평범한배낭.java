import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][] baggage = new int[N+1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            baggage[i][0] = Integer.parseInt(st.nextToken());
            baggage[i][1] = Integer.parseInt(st.nextToken());
        }
        int [][] dp = new int[N+1][K+1];
        int weight, value;
        for (int b=1; b<=N; b++) {
            weight = baggage[b][0]; value = baggage[b][1];
            for (int d=1; d<=K; d++) {
                if (d >= weight) dp[b][d] = Math.max(dp[b-1][d], value + dp[b-1][d-weight]);
                else dp[b][d] = dp[b-1][d];
            }
        }
        System.out.println(dp[N][K]);
    }
}