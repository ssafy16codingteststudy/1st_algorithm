import java.io.*;
import java.util.*;

public class Main {

	private static int T;
	private static int K;
	private static long answer;
	private static PriorityQueue<Long> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());

			K = Integer.parseInt(st.nextToken());
			pq = new PriorityQueue<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				pq.offer(Long.parseLong(st.nextToken()));
			}

      // 그냥 계산해보니 그리디하게 할 수 있다는 것을 알 수 있었음
      // 현재 선택할 수 있는 모든 파일들 중에서 가장 적은 용량을 가진 파일을 계속해서 선택하면,
      // 최소비용을 구할 수 있음

      // 가장 적은 용량을 가진 파일을 선택하는 방법 -> Priority Queue를 사용
			answer = 0;
			while (true) {
				long a = pq.poll();
				long b = pq.poll();

				answer += a + b;

				if (pq.isEmpty())
					break;
				pq.offer(a + b);
			}
			sb.append(answer).append('\n');
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
