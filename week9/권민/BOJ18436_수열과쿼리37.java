import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Count {
	int odd;
	int even;
}

public class Main {

	private static int N, M;
	private static int[] A;
	private static Count[] segTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		A = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		segTree = new Count[4 * N];
		for (int i = 0; i < 4 * N; i++) {
			segTree[i] = new Count();
		}
		makeTree(1, N, 1);

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int q = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (q == 1) {
				update(1, N, a, b, 1);
			} else if (q == 2) {
				sb.append(getEven(a, b, 1, N, 1)).append('\n');
			} else if (q == 3) {
				sb.append(getOdd(a, b, 1, N, 1)).append('\n');
			}
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static Count update(int s, int e, int target, int value, int index) {
		if (s > target || target > e) {
			return segTree[index];
		}
		if (s == e) {
			if (A[s] % 2 == 0) {
				segTree[index].even--;
			} else {
				segTree[index].odd--;
			}

			A[s] = value;
			if (A[s] % 2 == 0) {
				segTree[index].even++;
			} else {
				segTree[index].odd++;
			}

			return segTree[index];
		}

		int m = (s + e) / 2;
		return segTree[index] = getSum(update(s, m, target, value, 2 * index),
				update(m + 1, e, target, value, 2 * index + 1));
	}

	private static int getEven(int l, int r, int s, int e, int index) {
		if (l > e || r < s) {
			return 0;
		}
		if (l <= s && e <= r) {
			return segTree[index].even;
		}

		int m = (s + e) / 2;
		return getEven(l, r, s, m, index * 2) + getEven(l, r, m + 1, e, index * 2 + 1);
	}

	private static int getOdd(int l, int r, int s, int e, int index) {
		if (l > e || r < s) {
			return 0;
		}
		if (l <= s && e <= r) {
			return segTree[index].odd;
		}

		int m = (s + e) / 2;
		return getOdd(l, r, s, m, index * 2) + getOdd(l, r, m + 1, e, index * 2 + 1);
	}

	private static Count makeTree(int s, int e, int index) {
		if (s == e) {
			if (A[s] % 2 == 0) {
				segTree[index].even++;
			} else {
				segTree[index].odd++;
			}

			return segTree[index];
		}

		int m = (s + e) / 2;
		return segTree[index] = getSum(makeTree(s, m, 2 * index), makeTree(m + 1, e, 2 * index + 1));
	}

	private static Count getSum(Count a, Count b) {
		Count res = new Count();

		res.even = a.even + b.even;
		res.odd = a.odd + b.odd;

		return res;
	}
}
