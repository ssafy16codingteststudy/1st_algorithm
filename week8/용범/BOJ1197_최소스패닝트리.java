package 용범;

import java.io.*;
import java.util.*;

public class BOJ1197_최소스패닝트리 {

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

  static int V, E, A, B, C;
  static ArrayList<ArrayList<Node>> vertex;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    vertex = new ArrayList<ArrayList<Node>>();
    for (int i = 0; i < V + 1; i++) {
      vertex.add(new ArrayList<>());
    }

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      A = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());

      vertex.get(A).add(new Node(B, C));
      vertex.get(B).add(new Node(A, C));
    }

    br.close();
  }

  private static void solve() {
    long total = prim(1);
    System.out.println(total);
  }

  private static long prim(int s) {

    boolean[] visited = new boolean[V + 1];
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(s, 0));

    int cnt = 0;
    long total = 0;
    while (!pq.isEmpty()) {

      Node curr = pq.poll();

      if (visited[curr.v]) {
        continue;
      }

      visited[curr.v] = true; // 방문 처리
      total += curr.cost;
      cnt++;

      if (cnt == V) {
        break;
      }

      for (Node nxt : vertex.get(curr.v)) {
        if (visited[nxt.v]) {
          continue;
        }
        pq.offer(nxt);
      }
    }

    return total;
  }
}
