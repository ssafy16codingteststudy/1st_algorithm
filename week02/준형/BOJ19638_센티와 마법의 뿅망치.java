import java.io.*;
import java.util.*;

public class Main {
    
	static int N;
	static int H;
	static int T;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < N; i++) {
        	pq.add(Integer.parseInt(br.readLine()));
        }
        
        int cnt = 0;
        
        for (int i = 0; i < T; i++) {
        	if (!pq.isEmpty()) {
        		int largeMan = pq.peek();
                if (largeMan < H) break;
        		if (largeMan > 1) {
        			pq.add(pq.poll() / 2);
        			cnt++;
        		}
        	}
        }
        
        if (pq.peek() >= H) {
        	System.out.println("NO");
        	System.out.println(pq.peek());
        } else {
        	System.out.println("YES");
        	System.out.println(cnt);
        }
    }
}
