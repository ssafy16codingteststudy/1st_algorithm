import java.io.*;
import java.util.*;

public class Main {
	private static int T;
	private static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		arr = new long[1_000_001];
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;
		T = Integer.parseInt(br.readLine());
		for (int i = 4; i <= 1000000; i++) {
			// 
			arr[i] = (arr[i - 1] + arr[i - 2] + arr[i - 3]) % 1_000_000_009;
		}

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(arr[n]) + '\n');
		}
		bw.flush();

		bw.close();
		br.close();
	}

}