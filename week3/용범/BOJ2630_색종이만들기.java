package 용범;

import java.io.*;
import java.util.*;

public class BOJ2630_색종이만들기 {

  static int N, white, blue, cy, cx;
  static int[][] papers;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    // 입력 부분
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine()); // N: 변의 길이
    white = 0;
    blue = 0;
    papers = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        papers[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    br.close();
  }

  private static void solve() {

    StringBuilder sb = new StringBuilder();

    cy = 0;
    cx = 0;
    // 전체가 같은 색인 경우
    if (isPossible(cy, cx, N)) {
      sb.append(white).append("\n").append(blue);
    }
    // 전체가 같은 색이 아닌 경우 -> 분할
    else {
      divide(cy, cx, N / 2);
      sb.append(white).append("\n").append(blue);
    }

    // 정답 출력
    System.out.println(sb.toString());
  }

  private static boolean isPossible(int y, int x, int L) {

    int wCnt = 0;
    int bCnt = 0;
    // 범위 내에 속하는 색깔의 개수를 센다.
    for (int i = y; i < y + L; i++) {
      for (int j = x; j < x + L; j++) {
        wCnt += papers[i][j] == 0 ? 1 : 0;
        bCnt += papers[i][j] == 1 ? 1 : 0;
      }
    }

    // 전체가 하얀색이면 -> 하얀색 종이 개수 증가
    if (wCnt == L * L) {
      white++;
      return true;
    }
    // 전체가 파란색이면 -> 파란색 종이 개수 증가
    if (bCnt == L * L) {
      blue++;
      return true;
    }

    // 전체가 같은 색이 아니라 더 분할해야 하는 경우
    return false;
  }

  private static void divide(int cy, int cx, int N) {

    // 크기가 1인 종이인 경우
    if (N == 0) {
      white += papers[cy][cx] == 0 ? 1 : 0;
      blue += papers[cy][cx] == 1 ? 1 : 0;
      return;
    }

    int[][] nxt = {
        {cy, cx}, {cy + N, cx}, {cy, cx + N}, {cy + N, cx + N}
    };

    for (int[] pos : nxt) {
      if (isPossible(pos[0], pos[1], N)) {
        continue;
      }
      divide(pos[0], pos[1], N / 2);
    }
  }

}
