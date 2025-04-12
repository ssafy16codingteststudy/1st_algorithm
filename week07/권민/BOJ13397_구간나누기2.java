import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, answer = 0;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		int mx = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			mx = mx < arr[i] ? arr[i] : mx;
		}

		int left = 0, right = mx, answer = 0;
		while (left <= right) {
			int mid = (left + right) / 2;

			if (!check(mid)) {
				left = mid + 1;
			} else {
				answer = mid;
				right = mid - 1;
			}
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean check(int mv) {

		int mn = Integer.MAX_VALUE, mx = 0, count = 0;
		for (int i = 0; i < N; i++) {
			mn = mn > arr[i] ? arr[i] : mn;
			mx = mx > arr[i] ? mx : arr[i];

			if (mx - mn > mv) {
				count++;

				if (count >= M)
					return false;

				mn = mx = arr[i];
			}
		}

		return true;
	}

}
