package 용범;

import java.io.*;
import java.util.*;

public class BOJ24230_트리색칠하기 {

  static final int ROOT = 1;
  static int N, a, b, cnt;
  static int[] C;
  static boolean[] visited;
  static ArrayList<ArrayList<Integer>> vertex;


  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    C = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      C[i] = Integer.parseInt(st.nextToken());
    }

    vertex = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      vertex.add(new ArrayList<>());
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      // a <-> b 양방향
      vertex.get(a).add(b);
      vertex.get(b).add(a);
    }

    br.close();
  }

  private static void solve() {

    visited = new boolean[N + 1];
    visited[1] = true; // 루트 노드 방문 처리

    cnt = C[ROOT] == 0 ? 0 : 1;
    dfs(ROOT, C[ROOT]);
    System.out.println(cnt);
  }

  private static void dfs(int root, int rootColor) {

    for (Integer nxt : vertex.get(root)) {
      // 이미 방문했던 정점이라면 -> continue
      if (visited[nxt]) {
        continue;
      }

      visited[nxt] = true; // 방문 처리
      // 루트 노드의 색과 다음 노드의 색이 다르다면 -> 색칠 횟수 증가
      if (rootColor != C[nxt]) {
        cnt++;
        dfs(nxt, C[nxt]);
      }
      // 루트 노드의 색과 다음 노드의 색이 같다면 -> 그대로 dfs
      else {
        dfs(nxt, rootColor);
      }
    }
  }

}