package april_week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BOJ1786_찾기 {
	
	public static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		String T = bf.readLine();
		String P = bf.readLine();
		
		int pSize = P.length();
		int[] table = new int[pSize];
		int j = 0;
		for (int i = 1; i < pSize; i++) {
			while (j > 0 && P.charAt(i) != P.charAt(j)) {
				j = table[j - 1];
			}
			if (P.charAt(i) == P.charAt(j)) {
				table[i] = ++j;
			}
		}

		int count = 0;
		List<Integer> index = new ArrayList<>();
		int k = -1;
		int tSize = T.length();
		for (int i = 0; i < tSize; i++) {
			if (k == pSize - 1) {
				count++;
				k = table[pSize - 1] - 1;
				index.add(i - pSize + 1);

			}

			while (k > -1 && T.charAt(i) != P.charAt(k + 1)) {
				if (table[k] == 0) {
					k = -1;
				}
				else {
					k = table[k] - 1;
				}
			}
			if (T.charAt(i) == P.charAt(k + 1)) {
				k++;
			}
			
		}

		if (k == pSize - 1) {
			count++;
			k = table[pSize - 1];
			index.add(tSize - pSize + 1);

		}
		sb.append(count).append("\n");
		for(int i = 0; i < index.size(); i++){
			sb.append(index.get(i)).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		bf.close();
	}
}
