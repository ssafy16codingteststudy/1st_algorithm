import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][6];
        dp[0][0] = 1;

        for (int day = 1; day <= N; day++) {
            dp[day][0] = (dp[day - 1][0] + dp[day - 1][1] + dp[day - 1][2]) % MOD;
            dp[day][1] = dp[day - 1][0];
            dp[day][2] = dp[day - 1][1];
            dp[day][3] = (dp[day - 1][3] + dp[day - 1][4] + dp[day - 1][5]) % MOD + (dp[day][3] + dp[day - 1][0] + dp[day - 1][1] + dp[day - 1][2]) % MOD;
            dp[day][4] = dp[day - 1][3];
            dp[day][5] = dp[day - 1][4];
        }

        long result = 0;
        for (int state = 0; state < 6; state++) {
            result = (result + dp[N][state]) % MOD;
        }

        System.out.println(result);
    }
}
