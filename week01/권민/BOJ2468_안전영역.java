import java.util.*;
import java.io.*;

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N + 1][N + 1];

		int minHeight = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

				minHeight = Math.min(minHeight, arr[i][j]);
				maxHeight = Math.max(maxHeight, arr[i][j]);
			}
		}

		Queue<Pos> q = new LinkedList<>();
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		int answer = 0;
		for (int water = minHeight; water <= maxHeight; water++) {
			int[][] visited = new int[N + 1][N + 1];
			int dist = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] >= water && visited[i][j] == 0) {
						q.add(new Pos(i, j));
						visited[i][j] = ++dist;
						while (!q.isEmpty()) {
							Pos c = q.poll();
							int x = c.x, y = c.y;
							
							for(int d = 0; d<4; d++) {
								int nx = x + dx[d], ny = y + dy[d];
								
								if(nx < 0 || nx >= N || ny < 0 || ny >= N)continue;
								if(arr[nx][ny] < water)continue;
								if(visited[nx][ny] != 0)continue;
								
								q.add(new Pos(nx, ny));
								visited[nx][ny] = dist;
							}
						}
					}
				}
			}
			answer = Math.max(answer, dist);
		}
		bw.write(String.valueOf(answer) + '\n');
		bw.close();
	}
}
