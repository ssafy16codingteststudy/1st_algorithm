import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14611_월요병 {
    	static int N, M; //지도 사이즈(1~300)
	static int[][] map; // 지도 상황 및 비용 -1 벽을 못세우는 곳, -2 벽이 있는 곳 0~ -> 벽을 세우는 비용.
	static long[][] min;
	static List<int[]> start = new ArrayList<>();
	static final long max = 90_000L * 1_000_000_000;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static long ans = max;
	
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		min = new long[N][M]; for(int i=0; i<N; i++) Arrays.fill(min[i], max);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		PriorityQueue<long[]> pq = new PriorityQueue<>((p1, p2) -> {
			return Long.compare(p1[2], p2[2]);
		});
		for(int i=1; i<M; i++) { //위쪽 면에서 시작하는 벽 찾기
			if(map[0][i] == -2 || map[0][i] == 0) {
				min[0][i] = 0;
				pq.add(new long[] {0, i, 0});
			} else if(map[0][i] > 0) {
				min[0][i] = map[0][i];
				pq.add(new long[] {0, i, map[0][i]});
			}
			
		}
		for(int i=1; i<N-1; i++) { //오른쪽 면에서 시작하는 벽 찾기
			if(map[i][M-1] == -2 || map[i][M-1] == 0) {
				min[i][M-1] = 0;
				pq.add(new long[] {i, M-1,  map[i][M-1]});
			} else if(map[i][M-1] >= 0) {
				min[i][M-1] = map[i][M-1];
				pq.add(new long[] {i, M-1, map[i][M-1]});
			}
		}
		
		while(!pq.isEmpty()) {
			long[] cur = pq.poll();
			int x = (int)cur[0];
			int y = (int)cur[1];
			
			if(min[x][y] < map[x][y]) continue;
			
			for(int i=0; i<8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<M) {
					if(map[nx][ny] >= 0) {
						if(min[nx][ny] <= min[x][y] + map[nx][ny]) continue;
						
						min[nx][ny] = min[x][y] + map[nx][ny];
						pq.add(new long[] {nx, ny, min[nx][ny]});
					} else if (map[nx][ny] == -2) {
						if(min[nx][ny] <= min[x][y]) continue;
						
						min[nx][ny] = min[x][y];
						pq.add(new long[] {nx, ny, min[nx][ny]});
					}
				}
			}
		}
		for(int i=1; i<N; i++) {
			ans = Math.min(ans, min[i][0]);
		}
		
		for(int i=0; i<M-1; i++) {
			ans = Math.min(ans, min[N-1][i]);
		}
		if(ans == max) {
			ans = -1;
		}
		System.out.print(ans);
	}	
}
