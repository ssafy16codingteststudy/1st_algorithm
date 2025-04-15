import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	long[] words = new long[N];
    	long[] alphabets = new long[26];
    	long nowMemorized = 0;
    	for (int i=0;i<26;i++) {
    		alphabets[i] = 1 << i;
    		nowMemorized += alphabets[i];
    	}
    	
    	for (int i=0;i<N;i++) {
    		st = new StringTokenizer(br.readLine());
    		String now = st.nextToken();
    		long nowVal = 0;
    		for (int m=0;m<now.length();m++) {
    			if ((nowVal & alphabets[now.charAt(m) - 'a']) != (alphabets[now.charAt(m) - 'a'])) {
    				nowVal += alphabets[now.charAt(m) - 'a'];
    			}
    		}
    		words[i] = nowVal;
    	}
    	
    	for (int i=0;i<M;i++) {
    		st = new StringTokenizer(br.readLine());
    		int o = Integer.parseInt(st.nextToken());
    		String w = st.nextToken();
    		if (o == 1) { //잊기
    			nowMemorized -= alphabets[w.charAt(0) - 'a'];
    		} else { //기억하기
    			nowMemorized += alphabets[w.charAt(0) - 'a'];
    		}
    		int cnt = 0;
			for (long word: words) {
				if ((word & nowMemorized) == word)
					cnt++;
			}
			sb.append(cnt).append("\n");
    	}
    	System.out.println(sb);
    }
}