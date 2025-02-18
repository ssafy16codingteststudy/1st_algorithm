package algo;

import java.io.*;
import java.util.*;

class Main { 
    static int N;
    static int[][] map;
    static int[][] dist;
    static int problemCount = 1;
    static int[] dy = {-1, 1, 0, 0}; 
    static int[] dx = {0, 0, -1, 1}; 
    
    static void bfs(int i , int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {i, j});
        dist[i][j] = map[i][j]; 
        
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int cy = node[0];
            int cx = node[1];

            for (int d = 0; d < 4; d++) { 
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (0 <= ny && ny < N && 0 <= nx && nx < N) {
                    int newRupee = dist[cy][cx] + map[ny][nx]; 
                    if (newRupee < dist[ny][nx]) {
                        dist[ny][nx] = newRupee;
                        queue.add(new int[] {ny, nx});
                    }
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break; 

            map = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE; 
                }
            }

            bfs(0, 0);
            System.out.println("Problem " + (problemCount++) + ": " + dist[N-1][N-1]);
        }
    }
}
