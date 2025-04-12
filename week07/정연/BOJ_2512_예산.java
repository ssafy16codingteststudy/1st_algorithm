import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, budget, limit;
	static int[] needs;

	public static void main(String[] args) throws IOException {
		// 입력을 받기 위한 BufferedReader 설정
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		needs = new int[N];
		for (int i = 0; i < N; i++) {
			needs[i] = Integer.parseInt(st.nextToken());
			limit = Math.max(limit, needs[i]);
		}
		budget = Integer.parseInt(br.readLine());
		int start = 0;
		int end = limit;
		int mid = (start + end) / 2;
		while (end >= start) {
			mid = (start + end) / 2;
			if (ispossible(mid)) {
				// 예산 상한 올려도 됨
				limit = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		System.out.println(limit);
	}

	static boolean ispossible(int mid) {
		int localSum = 0;
		for (int i : needs) {
			localSum += Math.min(mid, i);
		}
		if (budget >= localSum)
			return true;
		return false;
	}
}
