package week9.성수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N; // 퍼즐의 크기(2~1,000)
    static ArrayList<Integer>[] graph;
    // static char[][] hori; //가로 부등호 저장.
    // static char[][] vert; //세로 부등호 저장.
    static int[] in;
    static Queue<Integer> q = new ArrayDeque<>();
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N * N];
        ans = new int[N * N];
        in = new int[N * N];
        // hori = new char[N][N-1];
        // vert = new char[N-1][N];
        for (int i = 0; i < N * N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N - 1; j++) {
                char h = st.nextToken().charAt(0);
                int u = i * N + j;
                int v = i * N + j + 1;
                if (h == '<') {
                    in[v] += 1;
                    graph[u].add(v); // u -> v; u < v;
                } else {
                    in[u] += 1;
                    graph[v].add(u); // u <- v; u > v;
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                char h = st.nextToken().charAt(0);
                int u = i * N + j;
                int v = (i + 1) * N + j;
                if (h == '<') {
                    in[v] += 1;
                    graph[u].add(v); // u -> v; u < v;
                } else {
                    in[u] += 1;
                    graph[v].add(u); // u <- v; u > v;
                }

            }
        }

        // for(int i=0; i<N*N; i++) {
        // System.out.print(i + ": ");
        // for(int n : graph[i]) {
        // System.out.print(n + " ");
        // }
        // System.out.println();
        // }

        for (int i = 0; i < N * N; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }
        solve();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(ans[i * N + j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    private static int solve() {
        int cnt = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            ans[now] = cnt++;
            for (int next : graph[now]) {
                in[next] -= 1;
                if (in[next] == 0) {
                    q.add(next);
                }
            }
        }
        return cnt;
    }
}
