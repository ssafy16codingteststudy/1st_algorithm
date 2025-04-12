package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16724_피리부는사나이 {

    static int N, M; // 지도의 행과 열, 1~1000
    static char[][] map;
    static int[] p; // 루트노드 저장 또는 랭크 저장.
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 }; // U D L R, 상하좌우
    static int[] dy = { 0, 0, -1, 1 };
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        p = new int[N * M];
        Arrays.fill(p, -1);

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // for(int i=0; i<N; i++) { //입력확인.
        // for(int j=0; j<M; j++) {
        // System.out.print(map[i][j] + " ");
        // }
        // System.out.println();
        // }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j])
                    continue;

                solve(i, j);
            }
        }
        for (int i = 0; i < N * M; i++) {
            // System.out.print(p[i] + " ");
            if (p[i] < 0) {
                ans++;
            }
        }
        System.out.print(ans);
        br.close();
    }

    public static int find(int x) {
        if (p[x] < 0)
            return x;

        return p[x] = find(p[x]);
    }

    public static void union(int x, int y) {
        int u = find(x);
        int v = find(y);

        if (u == v)
            return;

        if (p[u] > p[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        if (p[u] == p[v])
            p[u] -= 1;

        p[v] = u;
    }

    public static void solve(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { x, y });
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int dr = 0;
            if (map[now[0]][now[1]] == 'U') {
                dr = 0;
            } else if (map[now[0]][now[1]] == 'D') {
                dr = 1;
            } else if (map[now[0]][now[1]] == 'L') {
                dr = 2;
            } else {
                dr = 3;
            }
            int nx = now[0] + dx[dr];
            int ny = now[1] + dy[dr];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                int a = now[0] * M + now[1]; // p를 1차원 배열로 이용하기 위해서 좌표를 0~N*M-1 로 나열.
                int b = nx * M + ny;
                union(a, b);
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[] { nx, ny });
                    // System.out.println(nx + " " + ny);
                }
            }
        }
    }
}
/*
 * 0 0
 * 1 0
 * 2 0
 * 2 1
 * 2 2
 * 2 3
 * 1 3
 * 0 3
 * 0 2
 * 0 1
 * 1 1
 * 1 2
 */
