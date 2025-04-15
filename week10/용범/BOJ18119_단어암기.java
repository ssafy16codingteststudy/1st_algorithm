package 용범;

import java.io.*;
import java.util.*;

public class BOJ18119_단어암기 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N, M, o;
  static char x;
  static int[][] arr;
  static int[] tmp, sum, cache;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    sum = new int[N]; // sum[i]: i번째 문자의 전체 길이
    tmp = new int[N]; // tmp[i]: i번째 문자의 길이 임시 저장
    arr = new int[N][26]; // arr[i][j]: i번째 문자의 j 알파벳의 개수
    cache = new int[N];

    for (int i = 0; i < N; i++) {
      char[] ch = br.readLine().toCharArray();
      sum[i] = ch.length;
      tmp[i] = ch.length;
      for (char c : ch) {
        arr[i][c - 'a']++;
        cache[i] = cache[i] | 1 << (c - 'a');
      }
    }
  }

  private static void solve() throws IOException {

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      o = Integer.parseInt(st.nextToken());
      x = st.nextToken().charAt(0);

      int total = 0;
      switch (o) {
        // 알파벳 x를 잊는다.
        case 1:
          for (int j = 0; j < N; j++) {
            // 해당 문자를 가지고 있다면 -> 지운다.
            if ((cache[j] & (1 << (x - 'a'))) > 0) {
              tmp[j] -= arr[j][(x - 'a')]; // 전체 개수를 감소시킨다.
              cache[j] ^= 1 << (x - 'a'); // 해당 문자를 잊는다.
            }
            // 전체 개수와 감소시킨 개수가 같다면 -> 모든 알파벳을 알고 있는 상태++
            if (sum[j] == tmp[j]) {
              total++;
            }
          }
          // 정답 양식 저장
          sb.append(total).append("\n");
          break;

        // 알파벳 x를 기억해낸다.
        case 2:
          int idx = x - 'a';
          for (int j = 0; j < N; j++) {
            // 해당 문자를 가지고 있었지만, 잊고 있었던 경우 -> 개수를 복원한다.
            if (arr[j][idx] > 0 && !((cache[j] & 1 << (x - 'a')) > 0)) {
              tmp[j] += arr[j][idx];
              cache[j] |= 1 << (x - 'a');
            }
            // 전체 개수와 감소시킨 개수가 같다면 -> 모든 알파벳을 알고 있는 상태++
            if (sum[j] == tmp[j]) {
              total++;
            }
          }
          // 정답 양식 저장
          sb.append(total).append("\n");
          break;
      }
    }

    // 정답 출력
    System.out.println(sb);
    br.close();
  }
}
