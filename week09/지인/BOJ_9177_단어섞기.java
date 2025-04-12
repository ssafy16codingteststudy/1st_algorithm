import java.io.*;
import java.util.*;


public class BOJ_9177_단어섞기 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder sb = new StringBuilder();
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	
    	for (int loop=1;loop<=N; loop++) {
    		st = new StringTokenizer(br.readLine());
    		String A = st.nextToken();
    		String B = st.nextToken();
    		String Make = st.nextToken();
    		
    		boolean[][] check = new boolean[A.length() + 1][B.length() + 1];
    		check[0][0] = true;
    		for (int i=0;i<=A.length();i++) {
    			for (int j=0;j<=B.length();j++) {
    				if (check[i][j] == true) {
    					if (i < A.length() && Make.charAt(i + j) == A.charAt(i))
    						check[i + 1][j] = true;
    					if (j < B.length() && Make.charAt(i + j) == B.charAt(j))
    						check[i][j + 1] = true;
    				}
    			}
    		}
    		
    		sb.append("Data set ").append(loop).append(": ");
    		if (check[A.length()][B.length()])
    			sb.append("yes\n");
    		else
    			sb.append("no\n");
    		
    		
    	}
    
    	bw.write(sb.toString());
    	bw.flush(); 
    }
}