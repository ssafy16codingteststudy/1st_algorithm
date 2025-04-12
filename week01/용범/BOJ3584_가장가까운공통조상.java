package 용범;

import java.io.*;
import java.util.*;

public class BOJ3584_가장가까운공통조상 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int T, N, A, B, v1, v2;
    static ArrayList<ArrayList<Integer>> vertex;
    static ArrayList<Integer> v1toRoot;
    static Set<Integer> v2toRoot;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            input();

            solve();

        }

        // 출력 부분
        br.close();
        System.out.println(sb.toString());
    }

    private static void solve() {

        v1toRoot = new ArrayList<>();
        dfs(v1, true); // flag: true -> List 저장

        v2toRoot = new HashSet<>();
        dfs(v2, false); // flag: false -> Set 저장

        for (Integer node : v1toRoot) {
            // 가장 가까운 노드가 Set에 존재하면 -> 공통 조상 발견 -> break
            if (v2toRoot.contains(node)) {
                sb.append(node).append("\n");
                break;
            }
        }
    }

    private static void dfs(int curr, boolean flag) {

        if (flag) v1toRoot.add(curr);
        else v2toRoot.add(curr);
        for (Integer nxt : vertex.get(curr)) {
            dfs(nxt, flag);
        }
    }


    private static void input() throws IOException {

        // 입력 부분
        N = Integer.parseInt(br.readLine()); // N: 노드의 개수

        vertex = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            vertex.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            // A: 부모, B: 자식 / B -> A: 단방향
            // 자식에서 DFS 실행 시 마지막 탐색 노드 = root
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            vertex.get(B).add(A);
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }
}
