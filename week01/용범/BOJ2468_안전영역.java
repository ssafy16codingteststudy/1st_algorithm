package 용범;

import java.io.*;
import java.util.*;

public class BOJ2468_안전영역 {

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, MAX_HEIGHT, nxtY, nxtX;
    static int[][] map;
    static boolean[][] visited;
    static ArrayDeque<Node> dq = new ArrayDeque<>();
    static int[] dys = {-1, 1, 0, 0}, dxs = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        input();

        solve();
    }

    private static void solve() {

        // 풀이 부분
        int MAX = 0;
        // 물의 높이: 0 ~ MAX_HEIGHT
        for (int h = 0; h <= MAX_HEIGHT; h++) {
            visited = new boolean[N][N];
            int area = 0;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    // 방문한 적이 없고, 안정 영역에 해당된다면 -> BFS 탐색
                    if (!visited[y][x] && map[y][x] > h) {
                        area++;
                        bfs(y, x, h);
                    }
                }
            }

            MAX = Math.max(MAX, area);
        }

        // 출력 부분
        System.out.println(MAX);
    }

    private static void bfs(int y, int x, int h) {

        visited[y][x] = true; // 시작점 방문 처리
        dq.offer(new Node(y, x));

        while (!dq.isEmpty()) {

            Node curr = dq.poll();

            for (int i = 0; i < 4; i++) {
                nxtY = curr.y + dys[i];
                nxtX = curr.x + dxs[i];

                if (inRange(nxtY, nxtX) && !visited[nxtY][nxtX] && map[nxtY][nxtX] > h) {
                    visited[nxtY][nxtX] = true; // 방문 처리
                    dq.offer(new Node(nxtY, nxtX));
                }
            }
        }
    }

    private static void input() throws IOException {

        // 입력 부분
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 최대 높이 저장
                if (MAX_HEIGHT < map[i][j])
                    MAX_HEIGHT = map[i][j];
            }
        }

        br.close();
    }

    private static boolean inRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}
