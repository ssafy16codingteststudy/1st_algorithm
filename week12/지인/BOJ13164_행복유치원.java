import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	int[] babies = new int[N];
    	int[] diffs = new int[N-1];
    	st = new StringTokenizer(br.readLine());
    	for (int i=0;i<N;i++) {
    		babies[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	int end = babies[N-1] - babies[0];
    	if (K == 1) {
    		System.out.println(end);
    		return;
    	}
    	
    	int sum = 0;
    	
    	for (int i=1;i<N;i++) {
    		diffs[i-1] = babies[i] - babies[i-1];
    		sum += diffs[i-1];
    	}
    	
    	Arrays.sort(diffs);
    	
    	for (int i=diffs.length - 1;i >= diffs.length - (K-1) ;i--) {
    		sum -= diffs[i];
    	}
    	
    	System.out.println(sum);
    }
}
