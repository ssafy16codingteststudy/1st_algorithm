import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static String[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;

		while ((input = br.readLine()) != null) {
			N = Integer.parseInt(input);
			arr = new String[(int) Math.pow(3, N)];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = "-";
			}
			dfs(0, 0, arr.length - 1);

			System.out.println(String.join("", arr));
		}
	}

	static void dfs(int count, int start, int end) {
		if (count == N) {
			return;
		}
		int len = end - start + 1;
		int leftEnd = start + len / 3 - 1;
		int rightStart = start + 2 * (len / 3);

		// 가운데 구간을 공백으로 처리
		for (int i = start + len / 3; i < rightStart; i++) {
			arr[i] = " ";
		}

		dfs(count + 1, start, leftEnd);
		dfs(count + 1, rightStart, end);
	}
}