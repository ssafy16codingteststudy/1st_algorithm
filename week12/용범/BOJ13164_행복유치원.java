package 용범;

import java.io.*;
import java.util.*;

public class BOJ13164_행복유치원 {

  static int N, K, MIN;
  static int[] arr, diff;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // N: 원생의 수
    K = Integer.parseInt(st.nextToken()); // K: 조의 개수

    arr = new int[N];
    diff = new int[N - 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < N - 1; i++) {
      diff[i] = arr[i + 1] - arr[i];
    }
    br.close();
  }

  private static void solve() {

    Arrays.sort(diff);

    MIN = 0;
    for (int i = 0; i < N - K; i++) {
      MIN += diff[i];
    }

    System.out.print(MIN);
  }
}

