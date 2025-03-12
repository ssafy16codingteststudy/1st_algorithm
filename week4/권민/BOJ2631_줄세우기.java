import java.io.*;
import java.util.*;

public class Main {

	private static int N, answer;

	private static int[] arr;
	private static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		dp = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
          // 현재 위치 i보다 앞에 있는 위치 j의 dp 값들을 보면서
          // 나보다 arr값이 작은데 dp값 + 1이 더 큰 경우 선택
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			answer = Math.max(answer, dp[i]);
		}

		sb.append(N - (answer + 1));
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();

	}
}
