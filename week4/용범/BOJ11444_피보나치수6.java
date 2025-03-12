package 용범;

import java.io.*;
import java.util.*;

public class BOJ11444_피보나치수6 {

  static final int MOD = 1_000_000_007;
  static long N;
  static long[][] tmp, mat = {{1, 1}, {1, 0}};


  public static void main(String[] args) {
    init();
    solve();
  }

  private static void init() {
    Scanner sc = new Scanner(System.in);
    N = sc.nextLong(); // 1 <= N <= 1_000_000_000_000_000_000
    sc.close();
  }

  private static void solve() {

    long[][] ans = divide(N);

    // 정답 출력
    System.out.println(ans[0][1]);

  }

  private static long[][] divide(long cnt) {

    if (cnt == 1) {
      return mat;
    }

    tmp = divide(cnt / 2);
    tmp = calculateMatrix(tmp, tmp);
    // 홀수인 경우 -> M^((2 * cnt) + 1)
    if (cnt % 2 == 1) {
      tmp = calculateMatrix(tmp, mat);
    }

    return tmp;
  }

  private static long[][] calculateMatrix(long[][] a, long[][] b) {

    long[][] res = new long[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 2; k++) {
          res[i][j] += (a[i][k] * b[k][j]);
        }
        res[i][j] %= MOD;
      }
    }

    return res;
  }

}
