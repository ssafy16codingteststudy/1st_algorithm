package ps.BOJ_1939_중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	static int N, M;
	static List<List<Node>> graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		int maxWeight = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Node(w, e));
			graph.get(e).add(new Node(w, s));
			maxWeight = Math.max(maxWeight, w);
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int low = 1;
		int high = maxWeight;
		int result = 0;

		while (low <= high) {
			int mid = (low + high) / 2;
			visited = new boolean[N + 1];
			if (isPossible(mid, start, end)) {
				result = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		System.out.println(result);
	}

	static boolean isPossible(int weight, int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (current == end)
				return true;

			for (Node next : graph.get(current)) {
				if (!visited[next.idx] && next.w >= weight) {
					visited[next.idx] = true;
					queue.offer(next.idx);
				}
			}
		}

		return false;
	}
}

class Node {
	int w;
	int idx;

	public Node(int w, int idx) {
		super();
		this.w = w;
		this.idx = idx;
	}

}
