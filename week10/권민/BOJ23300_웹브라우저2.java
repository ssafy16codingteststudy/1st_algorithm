import java.io.*;
import java.util.*;

public class Main {

	private static int N, Q;
	private static ArrayDeque<Integer> frontStack;
	private static ArrayDeque<Integer> backStack;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		frontStack = new ArrayDeque<>();
		backStack = new ArrayDeque<>();

		int curPage = 0;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			String command = st.nextToken();
			if (command.equals("B")) {
				if (!backStack.isEmpty()) {
					frontStack.offerLast(curPage);
					curPage = backStack.pollLast();
				}
			} else if (command.equals("F")) {
				if (!frontStack.isEmpty()) {
					backStack.offerLast(curPage);
					curPage = frontStack.pollLast();
				}
			} else if (command.equals("A")) {
				frontStack.clear();

				if (curPage != 0) {
					backStack.offerLast(curPage);
				}
				curPage = Integer.parseInt(st.nextToken());
			} else if (command.equals("C")) {
				Queue<Integer> temp = new ArrayDeque<>();

				int before = -1;
				while (!backStack.isEmpty()) {
					int newBefore = backStack.poll();

					if (before != newBefore) {
						before = newBefore;
						temp.offer(newBefore);
					}
				}

				while (!temp.isEmpty()) {
					backStack.offerLast(temp.poll());
				}
			}
		}

		sb.append(curPage).append('\n');

		if (!backStack.isEmpty()) {
			while (!backStack.isEmpty()) {
				sb.append(backStack.pollLast()).append(' ');
			}
		} else {
			sb.append(-1);
		}
		sb.append('\n');

		if (!frontStack.isEmpty()) {
			while (!frontStack.isEmpty()) {
				sb.append(frontStack.pollLast()).append(' ');
			}
		} else {
			sb.append(-1);
		}
		sb.append('\n');

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
