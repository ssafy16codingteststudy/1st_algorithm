import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] liquids;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		liquids = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(liquids);

		int answer = Integer.MAX_VALUE;
		int[] pair = new int[2];

		int left = 0, right = N - 1;
		while (left < right) {
			int l = liquids[left];
			int r = liquids[right];

			if (answer > Math.abs(l + r)) {
				answer = Math.abs(l + r);

				pair[0] = l;
				pair[1] = r;
			}

			if (Math.abs(l + liquids[right - 1]) < Math.abs(liquids[left + 1] + r)) {
				right--;
			} else {
				left++;
			}

		}

		sb.append(pair[0]).append(' ').append(pair[1]);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}
}
