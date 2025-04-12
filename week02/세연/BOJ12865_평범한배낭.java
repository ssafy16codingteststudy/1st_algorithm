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
        for (int b=1; b<=N; b++) { // 순서대로 짐을 받아서 경우 따지기 
            weight = baggage[b][0]; value = baggage[b][1]; 
            for (int d=1; d<=K; d++) { // 현재의 짐으로 가능한 가방 무게 경우의 수 따지기(d : 가방 무게 -> dp[b-1] : 현재 짐을 따지지 않았을 때의 경우의 수, dp[b][d] : 현재 짐까지 고려해서 무게가 d일 때의 최대 가치)
                if (d >= weight) // 현재의 짐의 무게가 가방 무게보다 작은 경우 
                    dp[b][d] = Math.max(dp[b-1][d], value + dp[b-1][d-weight]); // 현재의 짐을 넣을 때와 넣지 않을 때를 비교하여 최대 가치 넣어주기
                else // 현재의 짐의 무게가 가방 무게보다 큰 경우 -> 어차피 현재의 짐을 넣지 못함
                    dp[b][d] = dp[b-1][d]; // 이전까지 구한 현재 가방 무게에 대한 최대 가치 넣어주기
            }
        }
        System.out.println(dp[N][K]); 
    }
}