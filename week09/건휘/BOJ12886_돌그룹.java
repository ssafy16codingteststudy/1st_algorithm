package 건휘;

import java.util.*;
import java.io.*;

public class BOJ12886_돌그룹 {

	static int a, b, c, total = 0;
	static boolean[][] visited = new boolean[1501][1501];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		total = a + b + c;
		if(total % 3 != 0) {
			System.out.println("0");
			return;
		}
		
		bfs(a, b, c);
	}
	
	static void bfs(int a, int b, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {a, b});
		visited[a][b] = true;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0];
			int y = current[1];
			int z = total - x - y;
		
			if(x == y && y == z) {
				System.out.println("1");
				return;
			}
			
			int[] stone = {x, y, z};
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(i == j) continue;
					if(stone[i] < stone[j]) {
						int[] next = stone.clone();
						next[i] = stone[i] * 2;
						next[j] = stone[j] - stone[i];
						
						int nx = next[0];
						int ny = next[1];
						int nz = next[2];
						
						int[] sorted = {nx, ny, nz};
						Arrays.sort(sorted);
						
						int a1 = sorted[0];
						int b1 = sorted[1];
						
						if(!visited[a1][b1]) {
							visited[a1][b1] = true;
							queue.offer(new int[] {a1, b1});
						}
					}
				}
			}
		}
		
		System.out.println("0");
	}
}
