
import java.util.*;


public class Main {	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int problem = 0;
		
		while (true) {
			int N = sc.nextInt();
			
			if (N <= 0) break;
			problem++;
			
			int[][] cave = new int[N][N];
			int[][] minval = new int[N][N];
			
			for (int i=0;i<N;i++) {
				for (int j=0;j<N;j++) {
					cave[i][j] = sc.nextInt();
					minval[i][j] = 125 * 125 * 9 + 1; // INF
				}
			}
			
			minval[0][0] = cave[0][0];
			
			int[] dx = {0,1,0,-1};
			int[] dy = {1,0,-1,0};
			
			Deque<int[]> q = new ArrayDeque<>();
			
			q.addLast(new int[] {0,0});
			
			while (!q.isEmpty()) {
				int[] now = q.pollFirst();
				
				for (int i=0;i<4;i++) {
					int nx = now[0] + dx[i];
					int ny = now[1] + dy[i];
					
					if (nx >= 0 && nx < N && ny >= 0 && ny < N && minval[nx][ny] >(minval[now[0]][now[1]] + cave[nx][ny])) {
						minval[nx][ny] = minval[now[0]][now[1]] + cave[nx][ny]; // 새로운 루트
						q.addLast(new int[] {nx, ny});
					}
				}
			}
			System.out.printf("Problem %d: %d\n", problem, minval[N-1][N-1]);
		}
		
	}	
}



