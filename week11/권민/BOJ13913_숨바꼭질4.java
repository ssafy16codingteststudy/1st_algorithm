import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N, K;
	private static int[] pos;
	private static int[] before;
	private static Queue<Integer> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		pos = new int[100001];
		for (int i = 0; i < pos.length; i++) {
			pos[i] = -1;
		}

		q = new ArrayDeque<>();
		q.offer(N);
		pos[N] = 0;

		before = new int[100001];
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == K)
				break;

			int[] d = { cur * 2, cur - 1, cur + 1 };
			for (int i = 0; i < 3; i++) {
				if (d[i] < 0 || d[i] > 100000)
					continue;

				if (pos[d[i]] != -1)
					continue;

				q.offer(d[i]);
				pos[d[i]] = pos[N] + 1;
				before[d[i]] = cur;
			}
		}

		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for (int next = K; next != N; next = before[next]) {
			stack.offerLast(next);
		}

		sb.append(stack.size()).append('\n');

		stack.offerLast(N);
		while (!stack.isEmpty()) {
			sb.append(stack.pollLast()).append(' ');
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
