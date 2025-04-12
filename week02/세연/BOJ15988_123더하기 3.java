import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        long [] dp = new long[1000001];
        int lastIdx = 2; // dp에서 값이 업데이트 된 가장 마지막 idx
        dp[0] = 1; dp[1] = 1; dp[2] = 2; // 점화식 초기값
        for (int t=0;t<T;t++){
            int N = Integer.parseInt(br.readLine());
            if (N > lastIdx){ // 아직 값을 구하지 않은 경우
                for (int i=lastIdx+1; i<N+1; i++) {
                    dp[i] = (dp[i-3] + dp[i-2] + dp[i-1]) % 1000000009;
                }
            }
            sb.append(dp[N]).append("\n");
        }
        System.out.print(sb);
    }
}