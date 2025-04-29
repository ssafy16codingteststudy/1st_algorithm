package 일우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238_파티 {
    // 인접 리스트용 노드
    static class Node {
        int vertex, weight;
        Node next;
        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }

    // Dijkstra 우선순위 큐용
    static class Vertex implements Comparable<Vertex> {
        int no, dist;
        public Vertex(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }
        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static final int INF = Integer.MAX_VALUE;

    /**
     * Dijkstra 알고리즘
     * @param graph 인접 리스트 (1번부터 n번까지)
     * @param n 정점 개수
     * @param start 출발 정점
     * @return start → i 까지 최단거리 배열 (index 1..n)
     */
    static int[] dijkstra(Node[] graph, int n, int start) {
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist, INF);

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Vertex(start, 0));

        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            if (visited[cur.no]) continue;
            visited[cur.no] = true;

            for (Node nxt = graph[cur.no]; nxt != null; nxt = nxt.next) {
                if (!visited[nxt.vertex] && dist[nxt.vertex] > cur.dist + nxt.weight) {
                    dist[nxt.vertex] = cur.dist + nxt.weight;
                    pq.offer(new Vertex(nxt.vertex, dist[nxt.vertex]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 마을(정점) 수
        int m = Integer.parseInt(st.nextToken());  // 도로(간선) 수
        int x = Integer.parseInt(st.nextToken());  // 파티장 마을 번호

        // 원래 그래프와 역방향 그래프
        Node[] edges = new Node[n+1];
        Node[] revEdges = new Node[n+1];

        // 입력을 받으면서 양쪽 그래프에 모두 추가
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from   = Integer.parseInt(st.nextToken());
            int to     = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            // from → to
            edges[from]    = new Node(to, weight, edges[from]);
            // to → from (역방향)
            revEdges[to]   = new Node(from, weight, revEdges[to]);
        }

        // 1) 파티장 X → 각 마을 (집으로 돌아가는 최단 거리)
        int[] distToHome    = dijkstra(edges,    n, x);
        // 2) 각 마을 → 파티장 X (파티장으로 가는 최단 거리)
        int[] distToParty   = dijkstra(revEdges, n, x);

        // 가장 오래 걸리는 왕복 시간 계산
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            // INF 체크는 사실 문제에서 보장해 주기 때문에 생략 가능
            if (distToHome[i] < INF && distToParty[i] < INF) {
                answer = Math.max(answer, distToParty[i] + distToHome[i]);
            }
        }

        System.out.println(answer);
    }
}
