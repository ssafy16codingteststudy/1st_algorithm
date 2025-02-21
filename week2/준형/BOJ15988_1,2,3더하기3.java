import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000009;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        int maxN = 0;
        ArrayList<Integer> testCases = new ArrayList<>();
        
        // 모든 테스트 케이스를 읽고 최대 n 값을 찾기
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            testCases.add(n);
            if(n > maxN) {
                maxN = n;
            }
        }
        
        // dp 배열 초기화: dp[i] = i를 1, 2, 3의 합으로 나타내는 방법의 수
        int[] dp = new int[maxN + 1];
        dp[0] = 1;
        if(maxN >= 1) dp[1] = 1;
        if(maxN >= 2) dp[2] = 2;
        
        // 점화식: dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
        for (int i = 3; i <= maxN; i++) {
        	dp[i] = (((dp[i-1] + dp[i-2]) % MOD) + dp[i-3]) % MOD;
        }
        
        // 각 테스트 케이스에 대해 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int n : testCases) {
            sb.append(dp[n]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
