package week7.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1939_중량제한 {

    private static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static int N, M;
    private static List<Edge>[] graph;
    private static int start, end, minWeight, maxWeight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 섬의 개수
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        minWeight = Integer.MAX_VALUE;
        maxWeight = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));

            minWeight = Math.min(minWeight, weight);
            maxWeight = Math.max(maxWeight, weight);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int left = minWeight;
        int right = maxWeight;
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canGo(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean canGo(int weight) {
        boolean[] visited = new boolean[N + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == end) {
                return true;
            }

            for (Edge next : graph[now]) {
                if (!visited[next.to] && next.weight >= weight) {
                    visited[next.to] = true;
                    queue.offer(next.to);
                }
            }
        }

        return false;
    }
}
