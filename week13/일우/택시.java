package week13.일우;

public class 택시 {
    import java.util.*;

    public class Solution {
        // 내부 클래스: 간선 정보
        static class Edge {
            int to, cost;
            Edge(int _to, int _cost) { to = _to; cost = _cost; }
        }

        public int solution(int n, int s, int a, int b, int[][] fares) {
            // 1. 그래프 생성 (1-indexed)
            List<Edge>[] graph = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
            for (int[] f : fares) {
                int u = f[0], v = f[1], w = f[2];
                graph[u].add(new Edge(v, w));
                graph[v].add(new Edge(u, w));
            }

            // 2. 다익스트라 3회
            long[] distS = dijkstra(n, s, graph);
            long[] distA = dijkstra(n, a, graph);
            long[] distB = dijkstra(n, b, graph);

            // 3. 분기점 i를 모두 검사
            long answer = Long.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                // s→i + i→a + i→b
                long cost = distS[i] + distA[i] + distB[i];
                if (cost < answer) answer = cost;
            }

            return (int) answer;
        }

        // 다익스트라: 1부터 n까지, 시작 정점 src
        private long[] dijkstra(int n, int src, List<Edge>[] graph) {
            final long INF = Long.MAX_VALUE / 3;
            long[] dist = new long[n+1];
            Arrays.fill(dist, INF);
            dist[src] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    Comparator.comparingLong(o -> o[1])
            );
            // {정점, 현재까지 비용}
            pq.offer(new int[]{src, 0});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int u = cur[0];
                long costU = cur[1];
                if (costU > dist[u]) continue;

                for (Edge e : graph[u]) {
                    int v = e.to;
                    long nc = costU + e.cost;
                    if (nc < dist[v]) {
                        dist[v] = nc;
                        pq.offer(new int[]{v, (int)nc});
                    }
                }
            }
            return dist;
        }
    }

}
