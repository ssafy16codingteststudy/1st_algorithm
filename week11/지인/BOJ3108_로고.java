import java.io.*;
import java.util.*;

public class BOJ3108_로고 {
 	static int find(int[] p, int num) {
		if (p[num] != num)
			p[num] = find(p, p[num]);
		return p[num];
	}
	
	static boolean check(int[] s1, int[] s2) {
		if (s1[0] < s2[0] && s2[2] < s1[2] && s1[1] < s2[1] && s2[3] < s1[3])
			return false;
		if (s2[0] < s1[0] && s1[2] < s2[2] && s2[1] < s1[1] && s1[3] < s2[3])
			return false;
		if (s1[3] < s2[1] || s2[3] < s1[1])
			return false;
		if (s1[2] < s2[0] || s2[2] < s1[0])
			return false;
		
		return true;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int[][] squares = new int[N + 1][4];
    	squares[0] = new int[] {0,0,0,0};//안해도되긴함.
    	
    	int[] p = new int[N + 1];
    	for (int i=1;i<=N;i++) {
    		p[i] = i; 
    		st = new StringTokenizer(br.readLine());
    		for (int j=0;j<4;j++)
    			squares[i][j] = Integer.parseInt(st.nextToken());
    	}
    	
    	for (int i=0;i<=N;i++) {
    		for (int j=i+1;j<=N;j++) {
    			if (check(squares[i], squares[j])) {
    				int pi = find(p, i);
    				int pj = find(p, j);

    				p[pj] = pi;
    			}
    		}
    	}
    	
    	Set<Integer> sets = new HashSet<>();
    	for (int i = 0; i <= N; i++) {
    	    sets.add(find(p,i)); 
    	}

    	System.out.println(sets.size() - 1);
    	
    }
}