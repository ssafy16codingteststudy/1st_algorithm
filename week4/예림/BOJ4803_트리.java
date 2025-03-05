package week4.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ4803_트리 {

    private static boolean[] visited;
    private static List<Integer>[] graph;
    private static int nodeCount, edgeCount;
    private static boolean isCycle;

    // 각 연결 요소가 트리인지 아닌지 판별하는 문제
    // 1. 사이클이 없어야 한다
    // 2. 간선 수 = 노드 수 - 1여야 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int round = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 정점의 개수
            int m = Integer.parseInt(st.nextToken()); // 간선의 개수

            if (n == 0 && m == 0) {
                break;
            }

            int count = 0;
            visited = new boolean[n + 1];

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    continue;
                }
                nodeCount = 0;
                edgeCount = 0;
                isCycle = false;
                dfs(i, 0);
                if (!isCycle && nodeCount - 1 == edgeCount) {
                    count++;
                }
            }

            sb.append("Case ").append(round++).append(": ");
            if (count == 0) {
                sb.append("No trees.");
            } else if (count == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of ").append(count).append(" trees.");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int now, int prev) {
        nodeCount++;
        visited[now] = true;
        for (int next : graph[now]) {
            if (visited[next] && next != prev) {
                isCycle = true;
            } else if (!visited[next]) {
                edgeCount++;
                dfs(next, now);
            }
        }
    }
}
