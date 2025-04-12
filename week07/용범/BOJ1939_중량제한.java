import java.io.*;
import java.util.*;

public class BOJ1939_중량제한 {

  static class Node implements Comparable<Node> {

    int v;
    long cost;

    Node(int v, long cost) {
      this.v = v;
      this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
      return Long.compare(this.cost, o.cost);
    }
  }

  static final long INF = Long.MAX_VALUE;
  static int N, M, A, B, v1, v2;
  static long C, MAX;
  static ArrayList<ArrayList<Node>> vertex;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // N: 섬의 개수
    M = Integer.parseInt(st.nextToken()); // M: 섬을 잇는 다리의 정보 개수

    vertex = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      vertex.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      // A <-C-> B: 양방향
      A = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());
      C = Long.parseLong(st.nextToken());
      vertex.get(A).add(new Node(B, C));
      vertex.get(B).add(new Node(A, C));
      MAX = Math.max(MAX, C);
    }

    st = new StringTokenizer(br.readLine());
    v1 = Integer.parseInt(st.nextToken());
    v2 = Integer.parseInt(st.nextToken());
  }

  private static void solve() {
    long ans = bs();
    System.out.print(ans);
  }

  static long bs() {
    long left = 0;
    long right = MAX + 1;
    long ans = 0;

    while (left <= right) {
      long mid = (left + right) / 2;
      boolean res = dijkstra(mid); // 중량: mid

      // 최대 mid 중량으로 도달했다면 -> 중량 증가
      if (res) {
        left = mid + 1;
        ans = mid;
      } else {
        right = mid - 1;
      }
    }

    return ans;
  }

  private static boolean dijkstra(long mid) {
    long[] dist = new long[N + 1];
    Arrays.fill(dist, INF);
    dist[v1] = 0; // 시작점 초기화
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(v1, 0));

    while (!pq.isEmpty()) {
      Node curr = pq.poll();

      // 이미 갱신이 된 상태라면 -> continue
      if (dist[curr.v] != curr.cost) {
        continue;
      }

      // 다른 공장에 도착했다면 -> 탐색 중지
      if (curr.v == v2) {
        break;
      }

      for (Node nxt : vertex.get(curr.v)) {
        // 아직 방문하지 않고, 중량 제한보다 무게가 작거나 같은 경우 -> 탐색 진행
        if (dist[nxt.v] == Long.MAX_VALUE && mid <= nxt.cost) {
          dist[nxt.v] = nxt.cost; // 방문 처리
          pq.add(new Node(nxt.v, nxt.cost));
        }
      }
    }

    return dist[v2] != Long.MAX_VALUE;
  }

}
