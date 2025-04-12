package 건휘;

import java.util.*;
import java.io.*;

public class BOJ13975_파일합치기3 {

	static int t, k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			long result = 0;
			k = Integer.parseInt(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < k; i++) {
				pq.offer(Long.parseLong(st.nextToken()));
			}
			
			while(pq.size() > 1) {
				long sumA = pq.poll();
				long sumB = pq.poll();
				long sum = sumA + sumB;
				result += sum;
				pq.offer(sum);
			}
			
			System.out.println(result);
		}
	}
}