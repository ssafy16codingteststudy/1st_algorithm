import java.io.*;
import java.util.*;

public class BOJ_16900 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String s = st.nextToken();
		int k = Integer.parseInt(st.nextToken());
		
		int[] pi = new int[s.length()];
		
		for(int i = 1, j = 0; i < s.length(); i++) {
			while(j > 0 && s.charAt(i) != s.charAt(j)) {
				j = pi[j-1];
			}
			if(s.charAt(i) == s.charAt(j)) {
				pi[i] = ++j;
			}
		}
		
		int str = pi[s.length() - 1];
		
		long result = (long)s.length() + (long)(s.length() - str) * (long)(k - 1);
		System.out.println(result);
	}
}