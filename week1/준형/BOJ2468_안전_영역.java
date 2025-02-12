package algo;

import java.io.*;
import java.util.*;

class Main { 
	
	static int N;
	static int[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static void bfs(int i, int j, int waterH, boolean[][] visited) {
		Queue<int[]> queue = new ArrayDeque<>();
		visited[i][j] = true;
		queue.add(new int[]{i, j});
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			
			int cy = temp[0];
			int cx = temp[1];
			
			for (int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if (0 <= ny && ny < N && 0 <= nx && nx < N) {
					if (!visited[ny][nx] && map[ny][nx] > waterH) {
						visited[ny][nx] = true;
						queue.add(new int[] {ny, nx});
					}
				}
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		
		int maxH = 1;
		int minH = 101;
		
		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, map[i][j]);
				minH = Math.min(minH, map[i][j]);
			}
		}
		
		// process
		int maxSafezones = 1;
		
		for (int waterH = minH; waterH < maxH; waterH++) {
			
			int tempSafezones = 0;
			boolean[][] visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > waterH && !visited[i][j]) {
						bfs(i, j, waterH, visited);
						tempSafezones += 1;
					}
				}
			}
			maxSafezones = Math.max(maxSafezones, tempSafezones);
		}
		
		System.out.println(maxSafezones);
		
	}
}
