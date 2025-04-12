import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Path {
	int to;
	int weight;

	public Path(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

}

public class Main {

	private static int N, M;
	private static int dest, dept;
	private static List<Path>[] pathes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pathes = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			pathes[i] = new ArrayList<>();
		}

		int left = 1, right = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			pathes[A].add(new Path(B, C));
			pathes[B].add(new Path(A, C));

			right = right < C ? C : right;
		}

		st = new StringTokenizer(br.readLine());
		dept = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());

		int answer = 0;
		right++;
		while (left <= right) {
			int mid = (left + right) / 2;

			if (bfs(mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean bfs(int weight) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean visited[] = new boolean[N + 1];
		q.offer(dept);
		visited[dept] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == dest)
				return true;

			for (Path next : pathes[cur]) {
				if (next.weight < weight)
					continue;
				if (visited[next.to])
					continue;

				q.offer(next.to);
				visited[next.to] = true;
			}
		}

		return false;
	}

}
