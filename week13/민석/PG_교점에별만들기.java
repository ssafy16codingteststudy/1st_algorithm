import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        List<int[]> points = new ArrayList<>();
        long xMin = Long.MAX_VALUE, xMax = Long.MIN_VALUE;
        long yMin = Long.MAX_VALUE, yMax = Long.MIN_VALUE;

        for (int i = 0; i < line.length; i++) {
            long A = line[i][0], B = line[i][1], E = line[i][2];
            for (int j = i + 1; j < line.length; j++) {
                long C = line[j][0], D = line[j][1], F = line[j][2];
                long bm = A * D - B * C;
                if (bm == 0) continue; //교점 없을 때

                long nx = B * F - E * D;
                long ny = E * C - A * F;

                int x = (int)(nx / bm);
                int y = (int)(ny / bm);
                points.add(new int[]{x, y});

                xMin = Math.min(xMin, x);
                xMax = Math.max(xMax, x);
                yMin = Math.min(yMin, y);
                yMax = Math.max(yMax, y);
            }
        }

        int height = (int)(yMax - yMin + 1);
        int width = (int)(xMax - xMin + 1);
        char[][] grid = new char[height][width];
        for (char[] row : grid) Arrays.fill(row, '.');

        for (int[] p : points) {
            int x = p[0], y = p[1];
            int row = (int)(yMax - y); // y값 큰 게 위로
            int col = (int)(x - xMin);
            grid[row][col] = '*';
        }

        String[] answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(grid[i]);
        }
        return answer;
    }
}
