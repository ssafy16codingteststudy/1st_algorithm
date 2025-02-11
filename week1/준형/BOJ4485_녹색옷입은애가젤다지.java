package 준형;

import java.io.*;
import java.util.*;

public class BOJ4485_녹색옷입은애가젤다지 {
    static int N;
    static int[][] cave;
    static int[][] dist;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int problemCount = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            cave = new int[N][N];
            dist = new int[N][N];

            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    cave[y][x] = Integer.parseInt(st.nextToken());
                    dist[y][x] = Integer.MAX_VALUE;
                }
            }

            // BFS 실행
            int result = bfs();
            System.out.println("Problem " + (problemCount++) + ": " + result);
        }
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, cave[0][0]});
        dist[0][0] = cave[0][0];

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int cy = node[0], cx = node[1], currentCost = node[2];

            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;

                int newCost = currentCost + cave[ny][nx];

                if (newCost < dist[ny][nx]) {
                    dist[ny][nx] = newCost;
                    queue.add(new int[]{ny, nx, newCost});
                }
            }
        }

        return dist[N - 1][N - 1]; // 도착점의 최소 비용 반환
    }
}
