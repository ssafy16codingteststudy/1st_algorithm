import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static final int MAX = 10_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		int high = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			high = Math.max(high, arr[i]);
		}

		int low = 0;
		int mid = (high + low) / 2;

		int result = 0;
		while (high >= low) {
			mid = (high + low) / 2;
			if (isPossible(mid)) {
				// 가능 : 더 작아도 가능한지 확인해봐야함
				result = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		System.out.println(result);
	}

	static boolean isPossible(int mid) {
		// 배열의 시작부터 하나씩 구간에 포함시켜본다.
		// 구간에 포함시킬 때 최소, 최대값이 누구인지 확인한다.
		// 차이(최대 - 최소)가 mid보다 큰지 매번 비교한다
		// 차이 > mid 인 경우 가장 마지막으로 구간에 넣었던 값이 차이를 크게 벌린 원인임으로
		// 직전까지의 원소를 구간으로 인정하고 새로운 구간을 시작한다. (구간 개수 +1)
		// 구간 갯수가 처음 제시한 M 보다 커지는 순간 false를 리턴한다.

		int cnt = 1; // 구간 개수
		int max = 0;
		int min = MAX;
		for (int i = 0; i < N; i++) {
			max = Math.max(arr[i], max);
			min = Math.min(arr[i], min);
			if (max - min > mid) {
				cnt++;
				if (cnt > M)
					return false;
				max = 0;
				min = MAX;
				i--;
			}
		}
		if (cnt <= M)
			return true;
		else
			return false;
	}
}
