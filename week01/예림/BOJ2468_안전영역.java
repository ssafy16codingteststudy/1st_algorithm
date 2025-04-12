package 예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ2468_안전영역 {

    private static final int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    private static final int[] dy = {0, 0, -1, 1};
    private static int N;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int maxHeight = 0;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        // 핵심 로직
        for (int h = 0; h <= maxHeight; h++) {
            visited = new boolean[N][N];
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > h) {
                        count++;
                        bfs(i, j, h);
                    }
                }
            }
            answer = Math.max(answer, count);
        }

        // 출력
        System.out.println(answer);
    }

    private static void bfs(int x, int y, int h) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = node[0] + dx[d];
                int ny = node[1] + dy[d];
                if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] > h) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}