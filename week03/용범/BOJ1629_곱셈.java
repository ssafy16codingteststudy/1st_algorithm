package 용범;

import java.io.*;
import java.util.*;

public class BOJ1629_곱셈 {

  static long A, B, C, res, ans;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() {

    // 입력 부분
    Scanner sc = new Scanner(System.in);

    A = sc.nextLong();
    B = sc.nextLong();
    C = sc.nextLong();
    res = 0;

    sc.close();
  }

  private static void solve() {

    ans = divide(B);

    // 정답 출력
    System.out.println(ans % C);
  }

  private static long divide(long cnt) {

    if (cnt == 1) {
      return A;
    }

    // 제곱 횟수가 홀수인 경우
    if (cnt % 2 == 1) {
      res = divide(cnt / 2);
      res = (res * res) % C; // A^cnt * A^cnt = A^(2 * cnt)
      res = (res * A) % C; // A^(2*cnt + 1)
    }
    // 제곱 횟수가 짝수인 경우
    else {
      res = divide(cnt / 2);
      res = (res * res) % C;
    }

    return res;
  }

}
