import java.io.*;
import java.util.*;

public class Main {

    private static class Edge {
        int to, time, fee;

        Edge(int to, int time, int fee) {
            this.to = to;
            this.time = time;
            this.fee = fee;
        }
    }

    private static final int INF = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 건물 수 (1 ~ N)
        
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken()); // 남은 시간
        int M = Integer.parseInt(st.nextToken()); // 남은 돈

        int L = Integer.parseInt(br.readLine()); // 길 수

        List<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken()); // 이동 시간
            int fee = Integer.parseInt(st.nextToken()); // 택시비

            graph[u].add(new Edge(v, time, fee));
            graph[v].add(new Edge(u, time, fee));
        }

        int start = 1, end = N;
        
        int[][] minFee = new int[N + 1][T + 1]; // [i][j]: i 노드에 j 시간에 들어왔을 때 최소 비용
        for (int i = 1; i <= N; i++) {
            Arrays.fill(minFee[i], INF);
        }
        minFee[start][0] = 0;

        Deque<Edge> queue = new ArrayDeque<>();
        queue.add(new Edge(start, 0, 0)); // to time fee
        
        while (!queue.isEmpty()) {
            Edge now = queue.poll();

            for (Edge next : graph[now.to]) {
                int nextTime = now.time + next.time;
                int nextFee = now.fee + next.fee;
                
                if (nextTime > T || nextFee > M) {
                    continue;
                }

                if (minFee[next.to][nextTime] > nextFee) {
                    minFee[next.to][nextTime] = nextFee;
                    queue.add(new Edge(next.to, nextTime, nextFee));
                }
            }
        }
        
        // 1호관 -> N호관 T분 내 최소 지출 (불가능 시 -1)
        int answer = INF;
        for (int time = 0; time <= T; time++) {
            answer = Math.min(answer, minFee[end][time]);
        }
        System.out.println((answer == INF) ? -1 : answer);
    }
}
