import java.util.*;

public class 빛의_경로_사이클 {
    public int[] solution(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        // 위,우,아,좌 순서
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        boolean[][][] visited = new boolean[n][m][4];
        List<Integer> cycles = new ArrayList<>();

        // 모든 시작점(i,j)과 방향(d)에 대해
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[i][j][d]) {
                        cycles.add(traverse(grid, i, j, d, visited, dx, dy));
                    }
                }
            }
        }

        // 1. 리스트 정렬
        Collections.sort(cycles);
        // 또는 Java 8 이상이면:
        // cycles.sort(null);

        // 2. int[] 로 변환
        int[] answer = new int[cycles.size()];
        for (int i = 0; i < cycles.size(); i++) {
            answer[i] = cycles.get(i);
        }

        return answer;
    }

    // (i,j,d)에서 사이클 길이를 계산하고, 경로 방문 표시까지 마친 뒤 길이를 반환
    private int traverse(String[] grid, int i, int j, int d,
                         boolean[][][] visited, int[] dx, int[] dy) {
        int n = grid.length;
        int m = grid[0].length();
        int length = 0;
        int x = i, y = j, dir = d;

        // 사이클이 완성될 때까지
        while (!visited[x][y][dir]) {
            visited[x][y][dir] = true;
            length++;

            // 현재 칸의 문자에 따라 방향 전환
            char c = grid[x].charAt(y);
            if (c == 'L') {
                dir = (dir + 3) % 4;  // 왼쪽 회전
            } else if (c == 'R') {
                dir = (dir + 1) % 4;  // 오른쪽 회전
            }
            // 'S'면 dir 유지

            // 다음 위치로 이동 (토러스 형태)
            x = (x + dx[dir] + n) % n;
            y = (y + dy[dir] + m) % m;
        }
        return length;
    }
}