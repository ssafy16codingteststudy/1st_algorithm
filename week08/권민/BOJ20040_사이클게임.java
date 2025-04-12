import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	private static int[] parents;
	private static int n, m, answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		make();

		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (answer != 0)
				continue;

			union(a, b, i + 1);
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static void make() {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	private static boolean union(int a, int b, int count) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA != rootB) {
			parents[rootA] = rootB;
			return true;
		}

		answer = count;
		return false;
	}

	private static int find(int a) {
		if (a == parents[a])
			return a;

		return parents[a] = find(parents[a]);
	}

}
