import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	String S = st.nextToken();
    	int K = Integer.parseInt(st.nextToken());
    	
    	int[] p = new int[S.length()];
    	
    	int idx = 0;
    	for (int ptr = 1; ptr < S.length(); ptr++) {
    		while (idx > 0 && S.charAt(idx) != S.charAt(ptr)) {
    			idx = p[idx - 1];
    		}
    		if (S.charAt(idx) == S.charAt(ptr)) {
    			idx++;
    			p[ptr] = idx;
    		}
    	}
    	
    	long ans = (long) S.length() + (long) (K - 1) * (S.length() - p[S.length() - 1]); //미친놈
    	System.out.println(ans);
    }
}