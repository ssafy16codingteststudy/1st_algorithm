package 준형;

import java.io.*;
import java.util.*;

public class BOJ2468_안전_영역 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int minHeight = 101, maxHeight = 1;
        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, map[y][x]);
                maxHeight = Math.max(maxHeight, map[y][x]);
            }
        }

        int maxSafeZones = 1;

        for (int h = minHeight; h <= maxHeight; h++) {
            visited = new boolean[N][N];
            int safeZoneCount = 0;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (map[y][x] > h && !visited[y][x]) {
                        bfs(y, x, h);
                        safeZoneCount++;
                    }
                }
            }
            maxSafeZones = Math.max(maxSafeZones, safeZoneCount);
        }

        System.out.println(maxSafeZones);
    }

    static void bfs(int y, int x, int h) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int cy = node[0], cx = node[1];

            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny >= 0 && nx >= 0 && ny < N && nx < N) {
                    if (!visited[ny][nx] && map[ny][nx] > h) {
                        visited[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }
}
