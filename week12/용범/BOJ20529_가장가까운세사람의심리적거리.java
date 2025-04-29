package 용범;

import java.io.*;
import java.util.*;

public class BOJ20529_가장가까운세사람의심리적거리 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int T, N;
  static String[] mbti;

  public static void main(String[] args) throws IOException {

    T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      init();
      solve();
    }

    // 정답 출력
    System.out.print(sb);
    br.close();
  }

  private static void init() throws IOException {

    N = Integer.parseInt(br.readLine()); // N: 학생의 수

    mbti = new String[N]; // mbti: 성격 유형
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      mbti[i] = st.nextToken();
    }
  }

  private static void solve() {

    if (N > 32) {
      sb.append(0).append("\n");
      return;
    }

    int MIN = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        for (int k = j + 1; k < N; k++) {
          int dist = getDistance(mbti[i], mbti[j], mbti[k]);
          MIN = Math.min(MIN, dist);
        }
      }
    }

    sb.append(MIN).append("\n");
  }

  private static int getDistance(String s1, String s2, String s3) {

    int total = 0;
    for (int i = 0; i < 4; i++) {
      if (s1.charAt(i) != s2.charAt(i))
        total++;
      if (s1.charAt(i) != s3.charAt(i))
        total++;
      if (s2.charAt(i) != s3.charAt(i))
        total++;
    }

    return total;
  }
}
