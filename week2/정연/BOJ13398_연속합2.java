import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        int[][] dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 수열은 반드시 값이 1개 이상이어야 한다.
        dp[1][0] = arr[1];
        dp[1][1] = arr[1];
        int ans = arr[1];
        /**
         * dp[x][0] -> 하나도 빼지 않았을 때 최대 합이 되도록 그리디
         * -> 1. 이전 값+현재 값
         * -> 2. 현재 값(x인덱스부터 다시 시작)
         */
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
        }
        /**
         * dp[x][1] -> x 인덱스의 값을 뺐을 때 최대 합이 되도록 dp
         * -> 1. x인덱스를 빼기 (dp[x][1] = dp[x-1][0])
         * -> 2. 이미 그전에 뺐음(dp[x][1] = dp[x-1][1]+arr[x])
         * -> 3. 아무것도 빼지 않기(dp[x][1] = dp[x][0]
         */
        for (int i = 2; i <= n; i++) {
            dp[i][1] = Math.max(dp[i][0], Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]));
        }
        for (int i = 1; i <= n; i++) {
            /**
             * 구간의 합이니 dp[n][1]이 답이 되는 것이 아님
             * dp[1~n][1] 중에서 최대인 값을 구해야함
             */
            ans = Math.max(ans, dp[i][1]);
        }
        System.out.println(ans);
        /**
         * 반례
         * 5
         * -5 -4 3 5 6
         * -> 14
         * 
         * 1
         * -1
         * ->-1
         */
    }
}
