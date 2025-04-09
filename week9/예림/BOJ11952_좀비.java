import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N, M, K, S, p, q;
    private static List<Integer>[] graph;
    private static boolean[] isOccupied;
    private static boolean[] isDangerous;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시 수
        M = Integer.parseInt(st.nextToken()); // 길의 수
        K = Integer.parseInt(st.nextToken()); // 점령당한 도시 수
        S = Integer.parseInt(st.nextToken()); // 위험한 도시 범위

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken()); // 안전한 도시 비용
        q = Integer.parseInt(st.nextToken()); // 위험한 도시 비용

        isOccupied = new boolean[N + 1];
        isDangerous = new boolean[N + 1];

        for (int i = 0; i < K; i++) {
            isOccupied[Integer.parseInt(br.readLine())] = true;
        }

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        markDangerousCities();
        long minDistance = dijkstra();

        System.out.println(minDistance);
    }

    private static void markDangerousCities() {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (isOccupied[i]) {
                queue.offer(new int[]{i, 0}); // 정점, 깊이
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowV = now[0];
            int depth = now[1];

            if (depth >= S) { // 범위 넘어서면 넘어가기
                continue;
            }

            for (int next : graph[nowV]) {
                if (!visited[next] && !isOccupied[next]) {
                    isDangerous[next] = true;
                    visited[next] = true;
                    queue.offer(new int[]{next, depth + 1});
                }
            }
        }
    }

    private static long dijkstra() {
        long[] distances = new long[N + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[1] = 0;

        Queue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e[1]));
        pq.offer(new long[]{1, 0});

        while (!pq.isEmpty()) {
            long[] now = pq.poll();
            int nowV = (int) now[0];
            long cost = now[1];

            if (cost > distances[nowV]) { // 지금 경로 비용이 원래 비용보다 크다면
                continue;
            }

            for (int next : graph[nowV]) {
                if (isOccupied[next]) {
                    continue;
                }

                int moveCost;
                if (next == N) {
                    moveCost = 0;
                } else if (isDangerous[next]) {
                    moveCost = q;
                } else {
                    moveCost = p;
                }

                long nextCost = cost + moveCost;
                if (distances[next] > nextCost) {
                    distances[next] = nextCost;
                    pq.offer(new long[]{next, nextCost});
                }
            }
        }
        return distances[N];
    }
}
