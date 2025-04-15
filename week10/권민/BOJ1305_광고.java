import java.io.*;
import java.util.*;

public class Main {

	private static int N, pattern[];
	private static String ad;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		pattern = new int[N];
		ad = br.readLine();
		for (int i = 1, j = 0; i < N; i++) {
			while (j > 0 && ad.charAt(i) != ad.charAt(j)) {
				j = pattern[j - 1];
			}

			if (ad.charAt(i) == ad.charAt(j)) {
				pattern[i] = ++j;
			}
		}
		sb.append(N - pattern[N - 1]);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}
}
