import java.io.*;
import java.util.*;


public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	long[] val = new long[N];
    	for (int i=0;i<N;i++) {
    		val[i] = Long.parseLong(st.nextToken());
    	}
    	Arrays.sort(val);
    	
    	int start = 0;
    	int end = N-1;
    	
    	long answer = Long.MAX_VALUE;
    	int idx1 = start;
    	int idx2 = end;
    	// -3 -2 2 4
    	// 2 3 4 9
    	//-7 -4 -2
    	while (start < end) {
    		if (answer == 0)
    			break;
    		if (Math.abs(val[start] + val[end]) < answer) {
    			idx1 = start;
    			idx2 = end;
    			answer = Math.abs(val[start] + val[end]);
    		}
    		
    		if (val[start] + val[end] > 0) {
    			end--;
    		} else if (val[start] + val[end] < 0) {
    			start++;
    		}
    	}
    	System.out.println(val[idx1] + " " + val[idx2]);
    	
    }
}