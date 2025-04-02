import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Path implements Comparable<Path> {
	int from;
	int to;
	int cost;

	public Path(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Path o) {
		return this.cost - o.cost;
	}

}

public class Main {

	private static int V, E, answer, parents[];
	private static Path[] pathes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		pathes = new Path[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			pathes[i] = new Path(a, b, c);
		}
		Arrays.sort(pathes);

		make();
		int count = 0;
		for (int i = 0; i < E; i++) {
			if (union(pathes[i].from, pathes[i].to)) {
				answer += pathes[i].cost;
				if (++count == V - 1) {
					break;
				}
			}
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static void make() {
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA != rootB) {
			parents[rootA] = rootB;
			return true;
		}

		return false;
	}

	private static int find(int a) {
		if (a == parents[a])
			return a;

		return parents[a] = find(parents[a]);
	}

}
