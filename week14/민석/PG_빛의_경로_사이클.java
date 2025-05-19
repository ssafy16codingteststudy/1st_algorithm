import java.util.*;

class Solution {
    static boolean[][][] visited;
    static int[] dx = {0, 1, 0, -1}; // 상우하좌
    static int[] dy = {-1, 0, 1, 0};
    static int N, M;

    public int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        visited = new boolean[N][M][4];
        List<Integer> answer = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[y][x][d]) {
                        int len = simulate(grid, y, x, d);
                        answer.add(len);
                    }
                }
            }
        }

        return answer.stream().sorted().mapToInt(i -> i).toArray();
    }

    static int simulate(String[] grid, int y, int x, int dir) {
        int len = 0;

        while (!visited[y][x][dir]) {
            visited[y][x][dir] = true;
            len++;

            char c = grid[y].charAt(x);
            if (c == 'L') {
                dir = (dir + 3) % 4; // 좌회전
            } else if (c == 'R') {
                dir = (dir + 1) % 4; // 우회전
            }

            y = (y + dy[dir] + N) % N;
            x = (x + dx[dir] + M) % M;
        }

        return len;
    }
}
