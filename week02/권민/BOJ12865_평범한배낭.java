import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Bag {
	int W;
	int V;

	Bag(int W, int V) {
		this.W = W;
		this.V = V;
	}
}

public class Main {
	private static int N, K;
	private static Bag[] knapsack;
	// dp 배열이 가지는 값: 행 -> 물건의 번호, 열 -> 담을 수 있는 제한 무게,
	// 값 -> 현재 제한 무게에 담을 수 있는 최대 가치 (현재 제한 무게가 8일때 6의 무게가 최대이면 그 값을 저장할 수 있다.)
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		knapsack = new Bag[N + 1];
		dp = new int[N + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());

			knapsack[i] = new Bag(W, V);
		}

		for (int i = 1; i <= N; i++) {
			int W = knapsack[i].W;
			int V = knapsack[i].V;
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i - 1][j];
				if (W <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W] + V);
				}
			}
		}

		bw.write(String.valueOf(dp[N][K]));

		bw.close();
		br.close();
	}

}