
import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
	int to;
	long weight;

	public Edge(int to, long weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Long.compare(this.weight, o.weight);
	}
}

public class Main {

	private static int n, m;
	private static int start, end;
	private static long[] dist;
	private static int[] where;
	private static List<List<Edge>> list;
	private static Stack<Integer> s = new Stack<>();;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		where = new int[n + 1];
		dist = new long[n + 1];
		list = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
			dist[i] = Long.MAX_VALUE;
		}

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			int from, to, weight;
			st = new StringTokenizer(br.readLine());

			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());

			list.get(from).add(new Edge(to, weight));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijk();

		int cnt = 1;
		for (int i = end; i != start; i = where[i]) {
			s.add(i);
			cnt++;
		}
		s.add(start);

		sb.append(dist[end]).append("\n").append(cnt).append("\n");
		while (!s.isEmpty()) {
			sb.append(s.pop()).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static void dijk() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		pq.offer(new Edge(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			int curNode = cur.to;
			long curCost = cur.weight;

			if (curNode == end)
				break;

			for (Edge e : list.get(curNode)) {
				int nextNode = e.to;
				long nextCost = curCost + e.weight;

				if (dist[nextNode] > nextCost) {
					dist[nextNode] = nextCost;
					where[nextNode] = curNode;
					pq.offer(new Edge(nextNode, nextCost));
				}
			}
		}
	}

}
