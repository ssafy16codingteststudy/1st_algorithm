package 예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11725_트리의부모찾기 {

    private static List<Integer>[] adjList;
    private static boolean[] visited;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 핵심 로직
        solution(n);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] solution(int n) {
        parent = new int[n + 1];
        visited = new boolean[n + 1];

        dfs(1);

        return parent;
    }

    private static void dfs(int now) {
        visited[now] = true;
        for (int next : adjList[now]) {
            if (!visited[next]) {
                parent[next] = now;
                dfs(next);
            }
        }
    }
}
