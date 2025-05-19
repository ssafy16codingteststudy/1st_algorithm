package week14.세연;

import java.util.*;

class PG_빛의경로사이클 {
    char [][] map;
    int [][][] visited;
    int row, col;
    
    public int[] solution(String[] grid) {
        List<Integer> result = new ArrayList<>();
        row = grid.length; col = grid[0].length();
        map = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                map[i][j]= grid[i].charAt(j);
        }
        visited = new int[row][col][4]; // 왼 위 오 아 순서
        
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                for (int d = 0; d < 4; d++) {
                    if (visited[x][y][d] != 0) continue;
                    result.add(getCycle(x, y, d));
                }
            }
        }
        return result.stream()
                        .mapToInt(Integer::intValue)
                        .sorted()  // 오름차순 정렬
                        .toArray();
    }
    
    int getCycle(int x, int y, int dir) {
        int cnt = 1;
        while (visited[x][y][dir] == 0) { // 이미 방문했던 노드에 재방문 하는 경우 종료
            visited[x][y][dir] = cnt++; // 방문까지 몇칸 이동했는지 저장
            
            // 방문 노드(S, L, R) 에 맞게 방향 수정
            if (map[x][y] == 'L') { dir = (dir + 3) % 4; }
            else if (map[x][y] == 'R') { dir = (dir + 1) % 4; }
            
            // 다음 방문 노드의 좌표 구하기
            if (dir == 0) y = (y + 1) % col;
            else if (dir == 1) x = (x + 1) % row;
            else if (dir == 2) y = (col + y - 1) % col;
            else x = (row + x - 1) % row;
        }
        
        return cnt - visited[x][y][dir]; // 이전에 방문했던 칸 까지의 이동 거리를 전체 이동거리에서 빼줌
    }

}