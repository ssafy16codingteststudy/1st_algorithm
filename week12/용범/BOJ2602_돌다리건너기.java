package 용범;

import java.io.*;
import java.util.*;

public class BOJ2602_돌다리건너기 {

  static int LEN;
  static char[] command;
  static char[][] bridge;
  static int[][][] dp;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    command = br.readLine().toCharArray();

    bridge = new char[2][];
    bridge[0] = br.readLine().toCharArray();
    bridge[1] = br.readLine().toCharArray();

    LEN = bridge[0].length;
    dp = new int[2][LEN + 1][command.length + 1];

    br.close();
  }

  private static void solve() {

    dpTableInit();

    for (int i = 0; i < LEN; i++) {
      for (int j = 1; j < command.length; j++) {
        if (bridge[0][i] == command[j]) {
          int cnt = 0;
          for (int k = 0; k < i; k++) {
            cnt += dp[1][k][j - 1];
          }
          dp[0][i][j] = cnt;
        }

        if (bridge[1][i] == command[j]) {
          int cnt = 0;
          for (int k = 0; k < i; k++) {
            cnt += dp[0][k][j - 1];
          }
          dp[1][i][j] = cnt;
        }
      }
    }

    int ans = 0;
    for (int i = 0; i < LEN; i++) {
      ans += (dp[0][i][command.length - 1] + dp[1][i][command.length - 1]);
    }

    System.out.print(ans);
  }

  private static void dpTableInit() {
    // 두루마리의 첫번째 글자와 일치하는 경우 -> 1 저장
    for (int i = 0; i < LEN; i++) {
      if (bridge[0][i] == command[0]) {
        dp[0][i][0] = 1;
      }
      if (bridge[1][i] == command[0]) {
        dp[1][i][0] = 1;
      }
    }
  }
}