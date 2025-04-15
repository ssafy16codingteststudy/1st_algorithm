package april_week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1305_광고 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(bf.readLine());
		String s = bf.readLine();

		int[] table = new int[L];
		int j = 0;
		for(int i = 1; i < L; i++) {
			while(j > 0 && s.charAt(i) != s.charAt(j)) {
				j = table[j - 1];
			}
			if(s.charAt(i) == s.charAt(j)) {
				table[i] = ++j;
			}
		}

		System.out.println(L - table[L-1]);

		
		
		bf.close();

	}

}
