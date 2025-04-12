import java.util.*;
import java.io.*;

public class Main {

	private static int N;
	private static int[] arr;
	// 0번 행 -> 정방향으로 가는 최대 연속합
	// 1번 행 -> 역방향으로 가는 최대 연속합
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		dp = new int[N + 1][2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp[0][0] = arr[0];
		int answer = dp[0][0];

		if (N > 1) {
			// 하나도 빼지 않았을 때의 연속합 = 정방향으로 가는 연속합
			for (int i = 1; i < N; i++) {
				dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
				answer = Math.max(answer, dp[i][0]);
			}

			dp[N - 1][1] = arr[N - 1];
			for (int i = N - 2; i >= 0; i--) {
				dp[i][1] = Math.max(dp[i + 1][1] + arr[i], arr[i]);
			}

			// 정방향 + 역방향의 합 = 하나를 뺐을 때의 최대값
			answer = Math.max(answer, dp[1][1]);
			for (int i = 1; i < N - 1; i++) {
				answer = Math.max(answer, dp[i - 1][0] + dp[i + 1][1]);
			}
			answer = Math.max(answer, dp[N - 2][0]);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(answer);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
