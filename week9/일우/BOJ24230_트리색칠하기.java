package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ24230 {
    static int N, ans;
    static int[] color;
    static boolean[] visited;
    static List<List<Integer>> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine());
        color = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            color[i] = Integer.parseInt(st.nextToken());
        }

        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) tree.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        // 1번이 색칠해야하면 카운트를 1부터
        if (color[1] != 0) ans = 1;
        else ans = 0;

        // 루트노드
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1);
        System.out.println(ans);
    }

    static void dfs(int n) {
        for (int i = 0; i < tree.get(n).size(); i++) {
            int child = tree.get(n).get(i);
            if (!visited[child]) {
                visited[child] = true;
                if (color[n] != color[child]) {
                    // 부모 자식간의 칠해야하는 컬러가 다르면
                    // 트리를 순회하며 부모노드와 자식노드의 색이 다른 경우 개수를 세주면 된다.
                    // 부모노드의 색을 자식노드가 따라간 경우 색칠하지 않아도 되는데,
                    // 자식노드의 색이 다르면 그 자식노드부터 색칠한 것이기 떄문
                    ans++;
                }
                dfs(child);
            }
        }
    }
}
