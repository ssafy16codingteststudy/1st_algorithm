
import java.util.*;


public class Main {	
	
	public static void printarr(int [][]ground, boolean[][]visited) {
		int N = ground.length;
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++)
				System.out.printf(" %d", ground[i][j]);
			
			System.out.println();
		}
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (visited[i][j])
					System.out.print(" O");
				else
					System.out.print(" X");
			}
			
			System.out.println();
		}
	}
	
	public static int bfs(int height, int[][] ground) {
//		System.out.println("\n\nNOW HEIGHT: " + height);
		int area = 0;
		int N = ground.length;
		boolean[][] visited = new boolean[N][N];
		
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		
		for (int i=0;i<N; i++) {
			for (int j=0;j<N;j++) {
				if (!visited[i][j]) {
					if (ground[i][j] <= height)
						visited[i][j] = true;
					else {
						Deque<int[]> q = new ArrayDeque<>();
						q.addLast(new int[]{i,j});
						visited[i][j] = true;
//						System.out.printf("x: %d y: %d\n",i,j);
						while (!q.isEmpty()) {
							int[] now = q.pollFirst();
							for (int k=0;k<4;k++) {
								int nx = now[0] + dx[k];
								int ny = now[1] + dy[k];
								
								if (nx >= 0 && nx < N && ny >= 0 && ny < N && ground[nx][ny] > height && visited[nx][ny] == false) {
									q.addLast(new int[] {nx, ny});
									visited[nx][ny] = true;
								}
							}
						}
//						printarr(ground, visited);
						area++;
					}
				}
			}
		}
		
		return area;
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		sc.nextLine();
		
		int[][] ground = new int[N][N];
		int maxHeight = 1;
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				ground[i][j] = sc.nextInt();
				maxHeight = Math.max(maxHeight, ground[i][j]);
			}
		}
		
		
		int height = 0;
		int maxSafe = 0;
		while (height < maxHeight) {
			maxSafe = Math.max(bfs(height, ground), maxSafe);
//			System.out.printf("height: %d safe: %d\n", height, maxSafe);
			height++;
		}

		System.out.println(maxSafe);
	}	
}
