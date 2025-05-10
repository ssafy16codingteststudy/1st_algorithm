import java.util.*;

public class PG_합승택시요금 {

    private final int INF = 100_000_000;
    private List<Integer[]>[] lists;
    /**
     * 합승 택시 요금
     * 첫 번째 접근 : A to B 까지의 다익스트라 구한 뒤, 방문한 노드에 대해 S 까지 다익스트라의 합
     * 이후 접근 : 다익스트라 세 번 (A, B, S) 한 뒤, 모든 노드에 대한 최단경로들의 합 중 최소인것 구함
     */
    public int solution(int n, int s, int a, int b, int[][] fares) {

        lists = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {
            int first = fare[0];
            int second = fare[1];
            int cost = fare[2];

            lists[first].add(new Integer[] {second, cost});
            lists[second].add(new Integer[] {first, cost});
        }

        int[] distS = new int[n + 1];
        Arrays.fill(distS, INF);
        dijkstra(s, distS);

        int[] distA = new int[n + 1];
        Arrays.fill(distA, INF);
        dijkstra(a, distA);

        int[] distB = new int[n + 1];
        Arrays.fill(distB, INF);
        dijkstra(b, distB);

        int answer = INF;
        for (int i = 1; i < n + 1; i++) {
            int dist = distS[i] + distA[i] + distB[i];
            answer = Math.min(answer, dist);
        }
        return answer;
    }

    private void dijkstra (int start, int[] dist) {
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        dist[start] = 0;
        pq.add(new Integer[] {start, 0});

        while(!pq.isEmpty()) {
            Integer[] poll = pq.poll();
            int now = poll[0];
            int cost = poll[1];

            if(dist[now] < cost) {
                continue;
            }

            for (Integer[] next : lists[now]) {
                int nextCost = cost + next[1];

                if(dist[next[0]] > nextCost) {
                    dist[next[0]] = nextCost;
                    pq.add(new Integer[] {next[0], nextCost});
                }
            }
        }
    }
}