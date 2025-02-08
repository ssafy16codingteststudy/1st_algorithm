import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static List<List<Integer>> parent = new ArrayList<>();
	private static int[] answer;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		answer = new int[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			parent.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			parent.get(a).add(b);
			parent.get(b).add(a);
		}

		solve(1);
		for (int i = 2; i <= N; i++) {
			bw.write(String.valueOf(answer[i]) + '\n');
			bw.flush();
		}

		bw.close();
	}

	private static void solve(int p) {
		visited[p] = true;
		for (int v : parent.get(p)) {
			if (!visited[v]) {
				answer[v] = p;
				solve(v);
			}
		}

	}

}
