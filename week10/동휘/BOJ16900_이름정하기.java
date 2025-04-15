package april_week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16900_이름정하기 {
public static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		String S = st.nextToken();
		int K = Integer.parseInt(st.nextToken());
		
		int sSize = S.length();
		int[] table = new int[sSize];
		int j = 0;
		for (int i = 1; i < sSize; i++) {
			while (j > 0 && S.charAt(i) != S.charAt(j)) {
				j = table[j - 1];
			}
			if (S.charAt(i) == S.charAt(j)) {
				table[i] = ++j;
			}
		}
		
		int k = -1;
		for (int i = 1; i < sSize; i++) {
			while (k > -1 && S.charAt(i) != S.charAt(k+1)) {
				if (table[k] == 0) {
					k = -1;
				}
				else {
					k = table[k] - 1;
				}
			}
			if (S.charAt(i) == S.charAt(k+1)) {
				k++;
			}
		}
		
		long border = table[sSize - 1];
		long a = (sSize - (k + 1));

		long size = sSize + a * (K - 1);

		sb.append(size);
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		bf.close();
	}
}
