import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Tree {
	int min;
	int max;

	public Tree(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public Tree() {
		super();
	}

}

public class Main {

	private static int N, M;
	private static int[] A;
	private static Tree[] segTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
		}

		segTree = new Tree[4 * N];
		for (int i = 0; i < 4 * N; i++) {
			segTree[i] = new Tree();
		}
		makeTree(1, N, 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			Tree answer = query(1, N, a, b, 1);
			sb.append(answer.min).append(" ").append(answer.max).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static Tree query(int l, int r, int s, int e, int index) {
		if (l > e || s > r) {
			return new Tree(Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
		if (s <= l && r <= e) {
			return segTree[index];
		}

		int m = (l + r) / 2;

		return getMinMax(query(l, m, s, e, index * 2), query(m + 1, r, s, e, index * 2 + 1));
	}

	private static Tree makeTree(int s, int e, int index) {
		if (s == e) {
			segTree[index].max = segTree[index].min = A[s];
			return segTree[index];
		}

		int m = (s + e) / 2;
		return segTree[index] = getMinMax(makeTree(s, m, 2 * index), makeTree(m + 1, e, 2 * index + 1));
	}

	private static Tree getMinMax(Tree a, Tree b) {
		Tree res = new Tree();

		res.max = Integer.max(a.max, b.max);
		res.min = Integer.min(a.min, b.min);

		return res;
	}
}
