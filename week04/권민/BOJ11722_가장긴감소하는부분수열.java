import java.util.*;
import java.io.*;

public class Main {

	private static int N, answer = 0;
	private static int[] arr, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 현재 인덱스의 값보다 큰 인덱스의 수를 저장
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] < arr[j])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}

			answer = Math.max(dp[i], answer);
		}

		sb.append(answer + 1);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();

	}

}
