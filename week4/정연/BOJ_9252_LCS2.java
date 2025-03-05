import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9252_LCS2 {

    static String[] a, b;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine().split("");
        b = br.readLine().split("");

        dp = new int[b.length + 1][a.length + 1];

        for (int i = 1; i <= b.length; i++) {
            for (int j = 1; j <= a.length; j++) {

                // a 수열 b 수열의 같은 문자 발생
                if (b[i - 1].equals(a[j - 1])) {
                    // 새롭게 만든 것과 이전 것의 길이비교
                    if (dp[i - 1][j - 1] + 1 > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        continue;
                    }
                }
                // 이전 것 중에서 큰것 골라 값 넘기기
                dp[i][j] = (dp[i - 1][j] > dp[i][j - 1]) ? dp[i - 1][j] : dp[i][j - 1];
            }

        }
        int curx = b.length;
        int cury = a.length;
        int[] dx = { 0, -1, -1 };
        int[] dy = { -1, -1, 0 };

        while (true) {
            int curV = dp[curx][cury];
            if (curx == 0 || cury == 0)
                break;
            if (curV - 1 == dp[curx - 1][cury] && curV - 1 == dp[curx][cury - 1]
                    && curV - 1 == dp[curx - 1][cury - 1]) {
                sb.insert(0, b[curx - 1]);
                --curx;
                --cury;
                continue;
            }

            for (int i = 0; i < 3; i++) {
                int nx = dx[i] + curx;
                int ny = dy[i] + cury;
                if (curV == dp[nx][ny]) {
                    curx = nx;
                    cury = ny;
                    break;
                }
            }
        }
        System.out.println(dp[b.length][a.length]);
        System.out.println(sb.toString());

    }
}
