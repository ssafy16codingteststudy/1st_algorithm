package 용범;

import java.io.*;
import java.util.*;
import org.w3c.dom.Node;

public class BOJ14621_나만안되는연애 {

  static class Node implements Comparable<Node> {

    int v, cost;

    Node(int v, int cost) {
      this.v = v;
      this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
      return this.cost - o.cost; // 비용을 기준으로 오름차순 정렬
    }
  }

  static int N, M, u, v, d;
  static char G;
  static Map<Integer, Integer> school;
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
    N = Integer.parseInt(st.nextToken()); // N: 학교의 수
    M = Integer.parseInt(st.nextToken()); // M: 도로의 개수

    school = new HashMap<>();
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      G = st.nextToken().charAt(0); // G: 성별(남초 / 여초)
      school.put(i, (G == 'M' ? 1 : -1)); // 남: 1 / 여: -1 -> 더하면 0
    }

    // vertex 리스트 초기화
    vertex = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      vertex.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      // u <-> v: d 양방향 도로
      u = Integer.parseInt(st.nextToken());
      v = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());

      // 양방향 연결
      vertex.get(u).add(new Node(v, d));
      vertex.get(v).add(new Node(u, d));
    }

    br.close();
  }

  private static void solve() {
    // 두 정점의 성별이 달라야 한다. 즉, 정점 기준인 Prim 알고리즘 구현
    int ans = prim(1); // 시작 정점은 1로 설정

    System.out.print(ans);
  }

  private static int prim(int s) {

    boolean[] visited = new boolean[N + 1];

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(s, 0));

    int cnt = 0; // 탐색한 노드 개수
    int total = 0; // 탐색하면서 얻은 가중치
    while (!pq.isEmpty()) {

      Node curr = pq.poll();

      // 이미 해당 정점을 방문한 경우 -> continue
      if (visited[curr.v]) {
        continue;
      }

      visited[curr.v] = true; // 방문 처리
      total += curr.cost;
      cnt++;

      for (Node nxt : vertex.get(curr.v)) {
        // 방문한 적이 없고, 현재 학교와 다음 학교의 성별이 반대인 경우 -> pq.add()
        if (!visited[nxt.v] && school.get(curr.v) + school.get(nxt.v) == 0) {
          pq.add(nxt);
        }
      }
    }

    return cnt == N ? total : -1;
  }
}
