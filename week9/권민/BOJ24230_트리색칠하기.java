import java.io.*;
import java.util.*;

public class Main {

	private static int N, answer;
	private static int[] colors;
	private static boolean[] visited;
	private static List<Integer>[] connections;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		colors = new int[N + 1];
		visited = new boolean[N + 1];
		connections = new ArrayList[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
			connections[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			connections[a].add(b);
			connections[b].add(a);
		}

		dfs(1, 0);
		sb.append(answer);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

  // 현재 점에 색칠을 한다면, 밑의 모든 점들이 색칠된다는 것을 활용
  // 이전의 점과 현재 점의 색이 다르다면, 새롭게 색칠했다는 것을 알 수 있음
	private static void dfs(int cur, int prevColor) {
		if (prevColor != colors[cur]) {
			answer++;
		}

		visited[cur] = true;
		for (int next : connections[cur]) {
			if (visited[next])
				continue;

			dfs(next, colors[cur]);
		}
	}

}
