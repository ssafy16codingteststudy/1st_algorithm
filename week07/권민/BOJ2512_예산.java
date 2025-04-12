import java.util.*;
import java.io.*;

public class Main {

	private static int N, M, cost[];
	private static int left = 1, right = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		cost = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());

			right = right > cost[i] ? right : cost[i];
		}
		Arrays.sort(cost);

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		while (left <= right) {
			int mid = (left + right) / 2;

			int tmpValue = getValue(mid);
			if (tmpValue > M) {
				right = mid - 1;
			} else if (tmpValue < M) {
				left = mid + 1;
			} else {
				left = mid + 1;
				right = mid;
			}
		}
		sb.append(right);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static int getValue(int value) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += (cost[i] > value ? value : cost[i]);
		}
		return sum;
	}
}
