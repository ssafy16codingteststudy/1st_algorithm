package 용범;

import java.io.*;
import java.util.*;

public class BOJ11952_좀비 {

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

  static final int start = 1;
  static final long INF = Long.MAX_VALUE;
  static int N, M, K, S, p, q, a, b;
  static Set<Integer> zombies;
  static ArrayList<ArrayList<Integer>> vertex;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // N: 도시의 수
    M = Integer.parseInt(st.nextToken()); // M: 길의 수
    K = Integer.parseInt(st.nextToken()); // K: 좀비에게 점령당한 도시의 수
    S = Integer.parseInt(st.nextToken()); // S: 위험한 도시의 범위

    st = new StringTokenizer(br.readLine());
    p = Integer.parseInt(st.nextToken()); // p: 안전한 도시 숙박비
    q = Integer.parseInt(st.nextToken()); // q: 위험한 도시 숙박비

    zombies = new HashSet<>();
    for (int i = 0; i < K; i++) {
      zombies.add(Integer.parseInt(br.readLine()));
    }

    vertex = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      vertex.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      // a <-> b: 양방향 그래프
      vertex.get(a).add(b);
      vertex.get(b).add(a);
    }
    br.close();
  }

  private static void solve() {

    // 위험한 도시 리스트 찾기
    Set<Integer> dangerousCities = getDangerousCity();

    long[] dist = new long[N + 1];
    Arrays.fill(dist, INF);
    dist[start] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(start, 0));

    while (!pq.isEmpty()) {

      Node curr = pq.poll();

      for (Integer nxt : vertex.get(curr.v)) {
        // 좀비가 점령한 도시라면 -> 탐색하지 않는다.
        if (zombies.contains(nxt)) {
          continue;
        }

        long nxtCost = dangerousCities.contains(nxt) ? q : p;
        long newDist = dist[curr.v] + nxtCost;

        if (newDist < dist[nxt]) {
          dist[nxt] = newDist;
          pq.add(new Node(nxt, newDist));
        }
      }
    }

    System.out.print(dist[N] - (dangerousCities.contains(N) ? q : p));
  }

  private static Set<Integer> getDangerousCity() {

    Set<Integer> set = new HashSet<>();
    ArrayDeque<int[]> dq = new ArrayDeque<>();
    boolean[] visited = new boolean[N + 1];

    for (int zombie : zombies) {
      dq.offer(new int[]{zombie, 0});
      visited[zombie] = true;
    }

    while (!dq.isEmpty()) {
      int[] cur = dq.poll();

      // 현재까지 S번 이상 이동하여 도착한 경우 -> 안전한 도시이므로 break
      if (cur[1] >= S) {
        break;
      }

      for (Integer nxt : vertex.get(cur[0])) {
        // 이미 방문한 정점이라면 -> 탐색하지 않는다.
        if (visited[nxt]) {
          continue;
        }

        set.add(nxt);
        visited[nxt] = true;
        dq.offer(new int[]{nxt, cur[1] + 1});
      }
    }

    return set;
  }

}
