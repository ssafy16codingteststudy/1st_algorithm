package 용범;

import java.io.*;
import java.util.*;

public class BOJ2023_신기한소수 {

  static StringBuilder sb = new StringBuilder();

  static int N;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    sc.close();
  }

  private static void solve() {
    bt(0, new ArrayList<Integer>());
    System.out.print(sb);
  }

  private static void bt(int cnt, ArrayList<Integer> lst) {
    if (cnt == N) {
      int res = getNumber(lst);
      sb.append(res).append("\n"); // 소수인 경우 -> 정답 저장
      return;
    }

    for (int i = 0; i < 10; i++) {
      lst.add(i);
      int res = getNumber(lst);
      // 합성수인 경우 -> 추가한 숫자 제거
      if (!isPrime(res)) {
        lst.remove(lst.size() - 1);
        continue;
      }
      bt(cnt + 1, lst);
      lst.remove(lst.size() - 1);
    }
  }

  private static int getNumber(ArrayList<Integer> lst) {
    int res = 0;
    for (Integer num : lst) {
      res *= 10;
      res += num;
    }
    return res;
  }

  private static boolean isPrime(int n) {

    if (n < 2) {
      return false;
    }

    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

}
