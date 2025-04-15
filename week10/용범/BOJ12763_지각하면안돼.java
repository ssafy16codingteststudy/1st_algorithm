package 용범;

import java.io.*;
import java.util.*;

public class BOJ12763_지각하면안돼 {

  static class Node implements Comparable<Node> {

    int v, time, cost;

    Node(int v, int time, int cost) {
      this.v = v;
      this.time = time;
      this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
      if (this.time != o.time) {
        return this.time - o.time; // 최우선: 시간 순 오름차순
      }
      return this.cost - o.cost; // 차선: 요금 순 오름차순
    }
  }

  static int N, T, M, L, v1, v2, time, cost;
  static ArrayList<ArrayList<Node>> vertex;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine()); // N: 건물의 개수
    vertex = new ArrayList<ArrayList<Node>>();
    for (int i = 0; i <= N; i++) {
      vertex.add(new ArrayList<>());
    }

    st = new StringTokenizer(br.readLine());
    T = Integer.parseInt(st.nextToken()); // T: 남은 시간
    M = Integer.parseInt(st.nextToken()); // M: 남은 돈

    L = Integer.parseInt(br.readLine());
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      v1 = Integer.parseInt(st.nextToken());
      v2 = Integer.parseInt(st.nextToken());
      time = Integer.parseInt(st.nextToken()); // time: 걸리는 시간
      cost = Integer.parseInt(st.nextToken()); // cost: 택시 요금
      // 양방향 연결
      vertex.get(v1).add(new Node(v2, time, cost));
      vertex.get(v2).add(new Node(v1, time, cost));
    }
    br.close();
  }

  private static void solve() {
    int ans = dijkstra(1);
    System.out.print(ans);
  }

  private static int dijkstra(int start) {

    final int INF = 987_654_321;
    int[][] dist = new int[N + 1][10_001];
    for (int i = 0; i <= N; i++) {
      Arrays.fill(dist[i], INF);
    }
    dist[start][0] = 0; // 시작점 초기화

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0, 0));

    while (!pq.isEmpty()) {
      Node curr = pq.poll();

      // 이미 갱신이 되어있는 경우 -> continue
      if (dist[curr.v][curr.cost] != curr.time) {
        continue;
      }

      for (Node nxt : vertex.get(curr.v)) {
        int newCost = curr.cost + nxt.cost;
        int newTime = curr.time + nxt.time;
        // 비용과 시간이 제한된 범위를 넘는다면 -> continue;
        if (newCost > M || newTime > T) {
          continue;
        }

        if (newTime < dist[nxt.v][newCost]) {
          dist[nxt.v][newCost] = newTime;
          pq.offer(new Node(nxt.v, newTime, newCost));
        }
      }
    }

    int ans = -1;
    for (int i = 0; i <= M; i++) {
      if (dist[N][i] <= T) {
        ans = i;
        break;
      }
    }

    return ans;
  }
}
