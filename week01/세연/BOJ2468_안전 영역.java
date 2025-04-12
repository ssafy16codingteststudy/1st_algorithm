import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int minHeight = 101, maxHeight = 0;
    static int [][] table;
    static final int [] dx = {-1, 1, 0, 0};
    static final int [] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 입력 받기
        n = Integer.parseInt(br.readLine());
        table = new int[n][n];
        StringTokenizer st;
        for (int x = 0; x < n; x++){
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < n; y++){
                table[x][y] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, table[x][y]);
                maxHeight = Math.max(maxHeight, table[x][y]);
            }
        }
        // 안전 영역 최대값 탐색
        int answer = 1;
        for (int h = minHeight; h < maxHeight; h++){
            int [][] visited = new int[n][n];
            int cnt = 0;
            for (int x = 0; x < n; x++){
                for (int y = 0; y < n; y++){
                    if (visited[x][y] == 0 && table[x][y] > h) {
                        cnt++;
                        // 안전한 영역 하나를 bfs 방식으로 탐색
                        visited[x][y] = 1;
                        Queue<int[]> q = new LinkedList<>();
                        q.add(new int[]{x, y});
                        int[] curr;
                        while (!q.isEmpty()){
                            curr = q.poll();
                            for (int i = 0; i < 4; i++){
                                int mx = curr[0]+dx[i], my = curr[1]+dy[i];
                                if (mx>=0 && mx < n && my>=0 && my < n && visited[mx][my] == 0 && table[mx][my] > h){
                                    visited[mx][my] = 1;
                                    q.add(new int[]{mx,my});
                                }
                            }
                        }
                    }
                    else visited[x][y] = 1;
                }
            }
            if (cnt > answer) answer = cnt;
        }
        System.out.println(answer);
    }
}