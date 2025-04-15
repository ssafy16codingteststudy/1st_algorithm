import java.io.*;
import java.util.*;

public class Main {

	private static String T, P;
	private static int[] pattern;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		T = br.readLine();
		P = br.readLine();
		pattern = new int[P.length()];

		for (int i = 1, j = 0; i < P.length(); i++) {
			while (j > 0 && P.charAt(i) != P.charAt(j)) {
				j = pattern[j - 1];
			}

			if (P.charAt(i) == P.charAt(j)) {
				pattern[i] = ++j;
			}
		}

		int count = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0, j = 0; i < T.length(); i++) {
			while (j > 0 && T.charAt(i) != P.charAt(j)) {
				j = pattern[j - 1];
			}

			if (T.charAt(i) == P.charAt(j)) {
				if (j == P.length() - 1) {
					count++;
					list.add(i - j + 1);
                    // 다음 패턴을 위해서 최대 가능한만큼 패턴 매칭시키기
					j = pattern[j];
				} else {
					j++;
				}
			}
		}

		sb.append(count).append('\n');
		for (int n : list) {
			sb.append(n).append(' ');
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

}
