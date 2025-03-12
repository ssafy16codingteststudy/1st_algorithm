package 용범;

import java.io.*;
import java.util.*;

public class BOJ4779_칸토어집합 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();

  static int N, LEN, d;
  static char[] word;
  static String line;

  public static void main(String[] args) throws IOException {

    line = null;
    while ((line = br.readLine()) != null) {
      init();
      solve();
    }

    // 정답 출력 부분
    System.out.println(sb.toString());
    br.close();
  }

  private static void init() {
    // 입력 부분
    N = Integer.parseInt(line.trim()); // N: 3^N 개 막대기
    LEN = (int) Math.pow(3, N); // LEN: 전체 길이
    word = new char[LEN];
    Arrays.fill(word, '-');
  }

  private static void solve() {

    divide(0, LEN);

    // 정답 양식 저장
    for (char c : word) {
      sb.append(c);
    }
    sb.append("\n");
  }

  private static void divide(int x, int LEN) {

    // 만약, 길이가 1이 된다면 -> 분할 중단
    if (LEN == 1) {
      return;
    }

    d = LEN / 3;
    for (int i = x + d; i < x + 2 * d; i++) {
      word[i] = ' ';
    }
    int[] pos = {x, x + 2 * d};
    for (int nxt : pos) {
      divide(nxt, LEN / 3);
    }
  }

}
