package 준형;

import java.io.*;
import java.util.*;

public class BOJ3584_가장_가까운_공통_조상 {
    static int N;
    static List<Integer>[] tree;
    static int[] parent, depth;
    static boolean[] visited;

    static int findLCA(int u, int v) {
        while (depth[u] > depth[v]) u = parent[u];
        while (depth[v] > depth[u]) v = parent[v];

        while (u != v) {
            u = parent[u];
            v = parent[v];
        }

        return u;
    }

    static void dfs(int node, int d) {
        depth[node] = d;
        visited[node] = true;

        for (int child : tree[node]) {
            if (!visited[child]) {
                parent[child] = node;
                dfs(child, d + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            tree = new ArrayList[N + 1];
            parent = new int[N + 1];
            depth = new int[N + 1];
            visited = new boolean[N + 1];

            for (int i = 1; i <= N; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                tree[A].add(B);
                parent[B] = A;
            }

            int root = 1;
            for (int i = 1; i <= N; i++) {
                if (parent[i] == 0) {
                    root = i;
                    break;
                }
            }

            dfs(root, 0);

            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            System.out.println(findLCA(node1, node2));
        }
    }
}
