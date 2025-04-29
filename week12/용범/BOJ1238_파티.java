package 용범;

import java.io.*;
import java.util.*;

public class BOJ1238_파티 {

  static final int INF = 987_654_321;
  static int N, M, X, a, b, t;
  static ArrayList<ArrayList<int[]>> vertex;
  static ArrayList<ArrayList<int[]>> reverseVertex;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // N: 학생의 수
    M = Integer.parseInt(st.nextToken()); // M: 도로의 개수
    X = Integer.parseInt(st.nextToken()); // X: 파티를 벌일 집

    vertex = new ArrayList<>();
    reverseVertex = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      vertex.add(new ArrayList<>());
      reverseVertex.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      // a -> b: t 단방향 도로
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      t = Integer.parseInt(st.nextToken());
      vertex.get(a).add(new int[]{b, t});
      reverseVertex.get(b).add(new int[]{a, t});
    }

    br.close();
  }

  private static void solve() {

    int[] dist = dijkstra(X, vertex);
    int[] reverseDist = dijkstra(X, reverseVertex);

    int MAX = 0;
    for (int i = 1; i <= N; i++) {
      int d = dist[i] + reverseDist[i];
      if (d > INF) {
        continue;
      }
      MAX = Math.max(MAX, d);
    }
    System.out.print(MAX);
  }

  private static int[] dijkstra(int s, ArrayList<ArrayList<int[]>> v) {

    int[] dist = new int[N + 1];
    Arrays.fill(dist, INF);

    dist[s] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>(
        (a, b) -> Integer.compare(a[1], b[1]) // 비용을 기준으로 오름차순 정렬
    );
    pq.offer(new int[]{s, 0});

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();

      if (dist[cur[0]] != cur[1]) {
        continue;
      }

      for (int[] nxt : v.get(cur[0])) {
        int newDist = dist[cur[0]] + nxt[1];
        if (newDist < dist[nxt[0]]) {
          dist[nxt[0]] = newDist;
          pq.offer(new int[]{nxt[0], newDist});
        }
      }
    }

    return dist;
  }
}
