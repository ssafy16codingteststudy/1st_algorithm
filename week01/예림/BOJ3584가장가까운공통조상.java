package 예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ3584가장가까운공통조상 {

    private static List<Integer>[] tree;
    private static boolean[] visited;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            tree = new ArrayList[N + 1];
            visited = new boolean[N + 1];
            for (int i = 0; i <= N; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                tree[b].add(a);
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dfs(a);
            dfs(b);

            sb.append(answer)
                    .append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int now) {
        visited[now] = true;
        for (int next : tree[now]) {
            if (visited[next]) {
                answer = next;
                return;
            }
            dfs(next);
        }
    }
}
