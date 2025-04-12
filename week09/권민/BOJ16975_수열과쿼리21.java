import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int M;
	private static int[] A;
	private static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		A = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		tree = new long[4 * N];
		init(1, N, 1);

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int c = Integer.parseInt(st.nextToken());

			if (c == 1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());

				update(a, b, 1, N, 1, k);
			} else if (c == 2) {
				int x = Integer.parseInt(st.nextToken());

				sb.append(get(1, N, 1, x)).append('\n');
			}
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static void init(int s, int e, int index) {
		if (s == e) {
			tree[index] = A[s];
			return;
		}

		int m = (s + e) / 2;
		init(s, m, index * 2);
		init(m + 1, e, index * 2 + 1);
	}

	private static void update(int l, int r, int s, int e, int index, int v) {
		if (r < s || e < l) {
			return;
		}
		if (l <= s && e <= r) {
			tree[index] += v;
			return;
		}

		int m = (s + e) / 2;
		update(l, r, s, m, index * 2, v);
		update(l, r, m + 1, e, index * 2 + 1, v);
	}

	private static long get(int s, int e, int index, int x) {
		int m = (s + e) / 2;

		long v = tree[index];
		if (s == e) {
			return v;
		} else if (x <= m) {
			v += get(s, m, index * 2, x);
		} else if (x > m) {
			v += get(m + 1, e, index * 2 + 1, x);
		}

		return v;
	}
}
