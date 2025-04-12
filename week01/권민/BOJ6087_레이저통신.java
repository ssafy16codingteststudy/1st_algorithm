import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int v;
	int x;
	int y;
	int dir;

	Node(int v, int x, int y, int dir) {
		this.v = v;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

}

public class Main {
	private static int N;
	private static char[][] arr;
	private static int[][][] dist;
	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new char[M + 1][N + 1];
		dist = new int[M + 1][N + 1][4];

		Node node1 = null;
		Node node2 = null;

		for (int i = 0; i < M; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++)
					dist[i][j][k] = Integer.MAX_VALUE;
				arr[i][j] = input.charAt(j);
				if (arr[i][j] == 'C' && node1 == null) {
					node1 = new Node(0, i, j, -1);
				} else if (arr[i][j] == 'C') {
					node2 = new Node(0, i, j, -1);
				}
			}
		}

		Queue<Node> q = new ArrayDeque<>();
		q.offer(node1);

		while (!q.isEmpty()) {
			Node node = q.poll();
			int v = node.v;
			int x = node.x;
			int y = node.y;
			int dir = node.dir;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];

				if (nx < 0 || nx >= M || ny < 0 || ny >= N)
					continue;
				if (arr[nx][ny] == '*')
					continue;

				if (dist[nx][ny][i] > v || dist[nx][ny][i] > v + 1) {
					int tmp = (dir != i) ? v + 1 : v;
					q.offer(new Node(tmp, nx, ny, i));
					dist[nx][ny][i] = tmp;
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			answer = Math.min(answer, dist[node2.x][node2.y][i]);
		}

		bw.write(String.valueOf(answer - 1) + '\n');
		bw.flush();

		bw.close();
		br.close();
	}

}