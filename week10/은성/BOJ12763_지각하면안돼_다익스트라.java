import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ12763_지각하면안돼_다익스트라 {

    private static final int INF = 987654321;

    private static int N, T, M;
    private static List<Route>[] lists;
    /**
     * 12763 지각하면 안 돼
     * 이차원 다익스트라 배열을 사용한 정석적인 풀이
     * dist[node][timeUsed] = 해당 (node, timeUsed) 상태에서의 최소 비용
     * 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lists = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            lists[a].add(new Route(b, time, cost));
            lists[b].add(new Route(a, time, cost));
        }

        System.out.println(dijkstra(1));
    }

    private static int dijkstra(int start) {
        PriorityQueue<Route> queue = new PriorityQueue<>();
        int[][] dist = new int[N + 1][T + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dist[i], INF);
        }

        queue.add(new Route(start, 0, 0));
        dist[start][0] = 0;

        while(!queue.isEmpty()) {
            Route r =  queue.poll();

            if(dist[r.node][r.time] < r.cost) {
                continue;
            }

            for (Route next : lists[r.node]) {
                int newTime = r.time + next.time;
                int newCost = r.cost + next.cost;

                if(newTime > T) {
                    continue;
                }

                if(newCost > M) {
                    continue;
                }

                if(dist[next.node][newTime] > newCost) {
                    dist[next.node][newTime] = newCost;
                    queue.add(new Route(next.node, newTime, newCost));
                }
            }
        }

        int answer = INF;
        for (int i = 1; i < T + 1; i++) {
            answer = Math.min(answer, dist[N][i]);
        }
        return answer == INF ? -1 : answer;
    }

    private static class Route implements Comparable<Route> {
        int node, time, cost;

        public Route(int node, int time, int cost) {
            super();
            this.node = node;
            this.time = time;
            this.cost = cost;
        }

        @Override
        public int compareTo(Route o) {
            // TODO Auto-generated method stub
            return this.cost - o.cost;
        }

    }
}