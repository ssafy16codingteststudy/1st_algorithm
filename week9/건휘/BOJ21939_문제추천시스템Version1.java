import java.util.*;
import java.io.*;

public class BOJ_21939 {

	static int n, m;
	static PriorityQueue<int[]> minPq = new PriorityQueue<>((a, b) -> {
		if (a[1] != b[1]) return a[1] - b[1];
		return a[0] - b[0];
	});
	static PriorityQueue<int[]> maxPq = new PriorityQueue<>((a, b) -> {
		if (a[1] != b[1]) return b[1] - a[1];
		return b[0] - a[0];
	});
	
	static Map<Integer, Integer> map = new HashMap<>(); // 문제 번호 → 현재 난이도

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			int diff = Integer.parseInt(st.nextToken());

			minPq.offer(new int[]{no, diff});
			maxPq.offer(new int[]{no, diff});
			map.put(no, diff);
		}

		m = Integer.parseInt(br.readLine());

		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();

			if (cmd.equals("add")) {
				int p = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());

				map.put(p, l);
				minPq.offer(new int[]{p, l});
				maxPq.offer(new int[]{p, l});

			} else if (cmd.equals("recommend")) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					while (true) {
						int[] cur = maxPq.peek();
						if (map.containsKey(cur[0]) && map.get(cur[0]) == cur[1]) {
							System.out.println(cur[0]);
							break;
						}
						maxPq.poll();
					}
				} else {
					while (true) {
						int[] cur = minPq.peek();
						if (map.containsKey(cur[0]) && map.get(cur[0]) == cur[1]) {
							System.out.println(cur[0]);
							break;
						}
						minPq.poll();
					}
				}

			} else if (cmd.equals("solved")) {
				int p = Integer.parseInt(st.nextToken());
				map.remove(p);
			}
		}
	}
}