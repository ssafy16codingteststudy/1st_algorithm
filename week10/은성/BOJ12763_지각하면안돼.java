import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ12763_지각하면안돼 {

    private static int N, T, M, answer;
    private static boolean[] visited;
    private static List<Edge>[] lists;

    /**
     * 12763 지각하면 안 돼
     * 시간과 금액의 상태를 관리해야하는 다익스트라 문제
     * 인 것 같았지만 그냥 dfs 가지치기로 풀림...
     * 난이도 기여에 보니 "최소 비용을 위치 및 걸리는 시간에 따라 저장하는 2차원 배열로 만들어서 관리하는 게 핵심" 이라고 함...
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = Integer.MAX_VALUE;
        visited = new boolean[N + 1];
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

            lists[a].add(new Edge(b, time, cost));
            lists[b].add(new Edge(a, time, cost));
        }

        visited[1] = true;
        dfs(1, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void dfs(int now, int time, int cost) {
        if (now == N) {
            answer = Math.min(answer, cost);
            return;
        }

        for (Edge edge : lists[now]) {
            if (visited[edge.next] || time + edge.time > T || cost + edge.cost > M) {
                continue;
            }

            visited[edge.next] = true;
            dfs(edge.next, time + edge.time, cost + edge.cost);
            visited[edge.next] = false;
        }
    }

    private static class Edge {
        int next, time, cost;

        public Edge(int next, int time, int cost) {
            this.next = next;
            this.time = time;
            this.cost = cost;
        }
    }
}