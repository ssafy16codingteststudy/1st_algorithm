package 용범;

import java.util.*;
import java.io.*;

public class BOJ11725_트리의부모찾기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, v1, v2;
    static ArrayList<ArrayList<Integer>> vertex;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        input();

        solve();

    }

    private static void dfs(int root) {

        for (Integer child : vertex.get(root)) {
            if (parent[child] == 0) {
                parent[child] = root;
                dfs(child);
            }
        }
    }

    private static void solve() {

        dfs(1);

        // 출력 부분
        for (int i = 2; i <= N; i++)
            sb.append(parent[i]).append("\n");

        System.out.println(sb.toString());
    }

    private static void input() throws IOException {

        // 입력 부분
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        vertex = new ArrayList<>();

        for (int i = 0; i <= N; i++)
            vertex.add(new ArrayList<Integer>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            // v1 <-> v2: 양방향 연결
            vertex.get(v1).add(v2);
            vertex.get(v2).add(v1);
        }

        br.close();
    }
}
