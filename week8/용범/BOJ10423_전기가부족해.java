package 용범;

import java.io.*;
import java.util.*;

public class BOJ10423_전기가부족해 {

  static class Edge implements Comparable<Edge> {

    int x, y, cost;

    Edge(int x, int y, int cost) {
      this.x = x;
      this.y = y;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
      return this.cost - o.cost; // 비용을 기준으로 오름차순 정렬
    }
  }

  static int N, M, K, u, v, w;
  static int[] tower;
  static int[] uf;
  static ArrayList<Edge> edges;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // N: 도시의 개수
    M = Integer.parseInt(st.nextToken()); // M: 케이블의 수
    K = Integer.parseInt(st.nextToken()); // K: 발전소의 개수

    // union-find 배열 초기화
    uf = new int[N + 1];
    for (int i = 0; i <= N; i++) {
      uf[i] = i;
    }

    tower = new int[K];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      tower[i] = Integer.parseInt(st.nextToken());
    }

    edges = new ArrayList<Edge>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      // u <-> v: w 양방향
      u = Integer.parseInt(st.nextToken());
      v = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());
      edges.add(new Edge(u, v, w));
    }

    br.close();
  }

  private static void solve() {

    // 0번 노드를 전체 루트 노드로 설정 / 0번 노드 - 발전소 -> union
    for (int i = 0; i < K; i++) {
      union(0, tower[i]);
    }

    edges.sort(Comparator.naturalOrder()); // Kruskal 알고리즘을 위한 오름차순 정렬

    int cnt = 1;
    int total = 0;
    for (Edge edge : edges) {
      if (find(edge.x) != find(edge.y)) {
        union(edge.x, edge.y);
        cnt++;
        // 모든 노드를 탐색했다면 -> 탐색 중지
        if (cnt > N) {
          break;
        }
        total += edge.cost;
      }
    }

    // 정답 출력
    System.out.println(total);
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

    uf[yRoot] = xRoot;
  }

  private static int find(int x) {
    if (uf[x] != x) {
      uf[x] = find(uf[x]);
    }
    return uf[x];
  }

}
