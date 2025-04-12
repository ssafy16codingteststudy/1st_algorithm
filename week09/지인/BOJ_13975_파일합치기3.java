import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder sb = new StringBuilder();
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	for (int loop=0;loop<N;loop++) {
    		st = new StringTokenizer(br.readLine());
    		int K = Integer.parseInt(st.nextToken());
    		int[] files = new int[K];
    		PriorityQueue<Long> pq = new PriorityQueue<>();
    		st = new StringTokenizer(br.readLine());
    		for (int i=0;i<K;i++) {
    			files[i] = Integer.parseInt(st.nextToken()); 
    			pq.add((long) files[i]);
    		}
    		
    		long answer = 0;
    		
    		while (pq.size() != 1) {
    			long A = pq.poll();
    			long B = pq.poll();
    			long next = A + B;
    			answer += next;
    			pq.add(next);
    		}
    		
    		System.out.println(answer);
    	}
    }
}