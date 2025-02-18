import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        long [] dp = new long[1000001];
        int lastIdx = 2;
        dp[0] = 1; dp[1] = 1; dp[2] = 2;
        for (int t=0;t<T;t++){
            int N = Integer.parseInt(br.readLine());
            if (N > lastIdx){
                for (int i=lastIdx+1; i<N+1; i++) {
                    dp[i] = (dp[i-3] + dp[i-2] + dp[i-1]) % 1000000009;
                }
            }
            sb.append(dp[N]).append("\n");
        }
        System.out.print(sb);
    }
}