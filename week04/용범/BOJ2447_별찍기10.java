package 용범;

import java.io.*;
import java.util.*;

public class BOJ2447_별찍기10 {

  static StringBuilder sb = new StringBuilder();

  static final int SIZE = (int) Math.pow(3, 7);
  static char[][] arr = new char[SIZE][SIZE];
  static int N;

  public static void main(String[] args) {
    init();
    solve();
  }

  private static void init() {

    // 입력 부분
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt(); // N: N = 3^k, 1 <= k < 8
    for (int i = 0; i < SIZE; i++) {
      Arrays.fill(arr[i], ' '); // 공백으로 초기화
    }

    sc.close();
  }

  private static void solve() {

    divide(0, 0, N);

    // 정답 양식 저장
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        sb.append(arr[i][j]);
      }
      sb.append("\n");
    }

    // 정답 출력
    System.out.println(sb);
  }

  private static void divide(int y, int x, int cnt) {

    if (cnt == 3) {
      for (int i = y; i < y + 3; i++) {
        for (int j = x; j < x + 3; j++) {
          // 가장 중앙 부분은 문자를 저장하지 않는다
          if (i == y + 1 && j == x + 1) {
            continue;
          }
          arr[i][j] = '*';
        }
      }
      return;
    }

    // 처음 가로 3 뭉치
    divide(y, x, cnt / 3);
    divide(y, x + cnt / 3, cnt / 3);
    divide(y, x + 2 * (cnt / 3), cnt / 3);

    // 가장 중앙을 제외한 왼쪽, 오른쪽 뭉치
    divide(y + cnt / 3, x, cnt / 3);
    divide(y + cnt / 3, x + 2 * (cnt / 3), cnt / 3);

    // 마지막 가로 3 뭉치
    divide(y + 2 * (cnt / 3), x, cnt / 3);
    divide(y + 2 * (cnt / 3), x + cnt / 3, cnt / 3);
    divide(y + 2 * (cnt / 3), x + 2 * (cnt / 3), cnt / 3);

  }

}
