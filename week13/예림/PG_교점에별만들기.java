import java.util.*;

class Solution {
    
    private class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Point)) {
                return false;
            }
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    public String[] solution(int[][] line) {
        Set<Point> points = new HashSet<>();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        
        for (int i = 0; i < line.length; i++) {
            long A = line[i][0], B = line[i][1], E = line[i][2];
            for (int j = i + 1; j < line.length; j++) {
                long C = line[j][0], D = line[j][1], F = line[j][2];
                
                long denominator = A * D - B * C;
                if (denominator == 0) {
                    continue;
                }
                
                long xNumerator = B * F - E * D;
                long yNumerator = E * C - A * F;
                
                if (xNumerator % denominator != 0 || yNumerator % denominator != 0) {
                    continue;
                }
                
                int x = (int) (xNumerator / denominator);
                int y = (int) (yNumerator / denominator);
                
                points.add(new Point(x, y));
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }
        
        int width = maxX - minX + 1;
        int height = maxY - minY + 1;
        
        char[][] grid = new char[height][width];
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }
        
        for (Point p : points) {
            int x = p.x - minX;
            int y = maxY - p.y;
            grid[y][x] = '*';
        }
        
        String[] answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(grid[i]);
        }
        return answer;
    }
}
