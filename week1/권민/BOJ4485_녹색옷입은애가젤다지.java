import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
	int v;
	int x;
	int y;

	Node(int v, int x, int y) {
		this.v = v;
		this.x = x;
		this.y = y;
	}

}

class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		return o1.v - o2.v;
	}

}

public class Main {
	private static int N;
	private static int[][] arr;
	private static int[][] dist;
	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int count = 1;
		do {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Integer.MAX_VALUE;
				}
			}

			dijk();
			bw.write(String.format("Problem %d: %d\n", count++, dist[N - 1][N - 1]));
			bw.flush();
		} while (true);

		bw.close();
		br.close();
	}

	public static void dijk() {
		PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

		dist[0][0] = arr[0][0];
		pq.offer(new Node(dist[0][0], 0, 0));

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int x = n.x;
			int y = n.y;
			int v = n.v;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				if (dist[nx][ny] > v + arr[nx][ny]) {
					dist[nx][ny] = v + arr[nx][ny];
					pq.offer(new Node(dist[nx][ny], nx, ny));
				}
			}
		}
	}
}