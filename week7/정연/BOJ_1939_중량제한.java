import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static List<List<Node>> graph;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		dist = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
//		System.out.println(graph);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Node(w, e));
			graph.get(e).add(new Node(w, s));
		}
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		djik(s, e);
		System.out.println(dist[e]);
	}

	static void djik(int start, int end) {
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o2.w - o1.w);

		for (int i = 0; i <= N; i++) {
			dist[i] = 0;
		}
		queue.offer(new Node(Integer.MAX_VALUE, start));
		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			if (curNode.idx == end)
				return;
			for (Node n : graph.get(curNode.idx)) {
				int small = Math.min(curNode.w, n.w);
				if (dist[n.idx] < small) {
					dist[n.idx] = small;
					queue.offer(new Node(small, n.idx));
				}
			}
		}
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
