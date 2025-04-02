package 용범;

import java.io.*;
import java.util.*;

public class BOJ17398_통신망분할 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  static int N, M, Q, X, Y;
  static int[] A;
  static int[] uf;
  static long[] rank;
  static ArrayList<int[]> edges;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // N: 통신탑의 개수
    M = Integer.parseInt(st.nextToken()); // M: 연결의 개수
    Q = Integer.parseInt(st.nextToken()); // Q: 연결 분할 횟수

    edges = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      // X - Y: 연결
      X = Integer.parseInt(st.nextToken());
      Y = Integer.parseInt(st.nextToken());
      edges.add(new int[]{X, Y}); // 연결 순서 저장
    }

    A = new int[Q]; // 연결을 제거할 엣지 번호
    visited = new boolean[M];
    for (int i = 0; i < Q; i++) {
      A[i] = Integer.parseInt(br.readLine()) - 1;
      visited[A[i]] = true; // 방문 처리
    }

    // union-find 배열 초기화
    uf = new int[N + 1];
    for (int i = 0; i <= N; i++) {
      uf[i] = i;
    }

    rank = new long[N + 1];
    Arrays.fill(rank, 1L);

    for (int i = 0; i < M; i++) {
      // 제거되지 않은 간선들끼리 union 해준다.
      if (visited[i]) {
        continue;
      }

      int[] edge = edges.get(i);
      union(edge[0], edge[1]);
    }

    br.close();
  }

  private static void solve() {

    long total = 0L;
    // 삭제과정의 역순으로 union
    for (int i = Q - 1; i >= 0; i--) {
      int[] edge = edges.get(A[i]);
      int a = find(edge[0]);
      int b = find(edge[1]);

      // 두 정점이 같은 집합이 아니라면 -> 계산 후 union
      if (a != b) {
        total += (rank[a] * rank[b]);
        union(a, b);
      }
    }

    // 정답 출력
    System.out.print(total);
  }

  private static void union(int x, int y) {

    int xRoot = find(x);
    int yRoot = find(y);

    if (xRoot == yRoot) {
      return;
    }

    if (xRoot > yRoot) {
      int tmp = xRoot;
      xRoot = yRoot;
      yRoot = tmp;
    }

    rank[xRoot] += rank[yRoot];
    uf[yRoot] = xRoot;
  }

  private static int find(int x) {
    if (uf[x] != x) {
      uf[x] = find(uf[x]);
    }
    return uf[x];
  }
}
