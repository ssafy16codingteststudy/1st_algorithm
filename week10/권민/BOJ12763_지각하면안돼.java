import java.io.*;
import java.util.*;

class Path implements Comparable<Path> {
	int to;
	int money;
	int time;

	Path(int to, int time, int money) {
		this.to = to;
		this.time = time;
		this.money = money;
	}

	@Override
	public int compareTo(Path p) {
		return this.money - p.money;
	}
}

public class Main {

	private static int N;
	private static int T, M, L;
	private static int[] money, time;

	private static List<Path>[] pathes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		money = new int[N + 1];
		time = new int[N + 1];
		pathes = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			money[i] = time[i] = Integer.MAX_VALUE;
			pathes[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());

			pathes[from].add(new Path(to, time, money));
			pathes[to].add(new Path(from, time, money));
		}

		PriorityQueue<Path> pq = new PriorityQueue<>();
		pq.offer(new Path(1, 0, 0));
		money[1] = time[1] = 0;

		int answer = -1;
		while (!pq.isEmpty()) {
			Path cur = pq.poll();

			if (cur.to == N) {
				answer = cur.money;
				break;
			}

			for (Path next : pathes[cur.to]) {
				int nextTime = cur.time + next.time;
				if (nextTime > T)
					continue;

				// 문제 조건 잘 안 읽고, 최대 비용이 정해져 있는지 몰랐음..
				// 문제 잘 읽기
				int nextMoney = cur.money + next.money;
				if (nextMoney > M)
					continue;

				if (money[next.to] > nextMoney || time[next.to] > nextTime) {
					money[next.to] = nextMoney;
					time[next.to] = nextTime;
					pq.offer(new Path(next.to, nextTime, nextMoney));
				}
			}
		}

		sb.append(answer);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
