package 용범;

import java.io.*;
import java.util.*;

public class BOJ13913_숨바꼭질4 {

  static StringBuilder sb = new StringBuilder();

  static final int MAX = 100_000;
  static int[] path = new int[MAX + 1];
  static boolean[] visited = new boolean[MAX + 1];

  static int N, K;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // N: 수빈 위치
    K = Integer.parseInt(st.nextToken()); // K: 동생 위치

    visited[N] = true; // 수빈이 현재 위치 방문 처리
    br.close();
  }

  private static void solve() {

    ArrayDeque<int[]> dq = new ArrayDeque<>();
    dq.offer(new int[]{N, 0});

    while (!dq.isEmpty()) {
      int[] curr = dq.poll();
      int x = curr[0];
      int cnt = curr[1];

      // 수빈이가 동생을 찾았다면 -> 탐색을 중지한다.
      if (x == K) {
        sb.append(cnt).append("\n");
        break;
      }

      int[] nxt = {x - 1, x + 1, x * 2};

      for (int nxtX : nxt) {
        // 이미 방문한 곳이거나, 범위를 넘어선 경우 -> 탐색하지 않는다.
        if (!inRange(nxtX) || visited[nxtX]) {
          continue;
        }

        // 다음 위치에 현재 위치를 저장
        path[nxtX] = x;
        visited[nxtX] = true; // 방문 처리
        dq.offer(new int[]{nxtX, cnt + 1});
      }
    }

    getPath();
    System.out.print(sb);
  }

  private static void getPath() {

    ArrayDeque<Integer> dq = new ArrayDeque<>();

    int curr = K;
    dq.offerFirst(curr);
    while (curr != N) {
      dq.offerFirst(path[curr]);
      curr = path[curr];
    }

    while (!dq.isEmpty()) {
      sb.append(dq.pollFirst()).append(" ");
    }
  }

  private static boolean inRange(int x) {
    return 0 <= x && x <= MAX;
  }
}
