import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int lenMagic = s.length();
        char[] magic = s.toCharArray();

        s = br.readLine();
        int len = s.length();

        char[][] bridge = new char[2][len];
        bridge[0] = s.toCharArray();
        s = br.readLine();
        bridge[1] = s.toCharArray();

        int [][] dp = new int[4][lenMagic];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < lenMagic; j++) {
                if (j == 0 && bridge[0][i] == magic[j]) {
                    dp[0][j] += 1;
                }
                if (j != 0 && bridge[0][i] == magic[j]) {
                    dp[0][j] += dp[3][j - 1];
                }

                if (j == 0 && bridge[1][i] == magic[j]) {
                    dp[1][j] += 1;
                }
                if (j != 0 && bridge[1][i] == magic[j]) {
                    dp[1][j] += dp[2][j - 1];
                }
            }

            for (int j = 0; j < lenMagic; j++) {
                dp[2][j] = dp[0][j];
                dp[3][j] = dp[1][j];
            }
        }

        System.out.println(dp[0][lenMagic - 1] + dp[1][lenMagic - 1]);
    }

}
