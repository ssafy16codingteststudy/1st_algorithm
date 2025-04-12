import java.io.*;
import java.util.*;

public class BOJ_21939_문제추천시스템Version1 {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	PriorityQueue<int[]> minQuest = new PriorityQueue<>((a, b) -> {
    		int cmp = Integer.compare(a[1], b[1]);
    		if (cmp == 0)
    			cmp = Integer.compare(a[0],b[0]);
    		return cmp;
    	});
    	
    	PriorityQueue<int[]> maxQuest = new PriorityQueue<>((a, b) -> {
    		int cmp = Integer.compare(b[1], a[1]);
    		if (cmp == 0)
    			cmp = Integer.compare(b[0],a[0]);
    		return cmp;
    	});
    	
    	Map<Integer, int[]> problems = new HashMap<>();
    	
    	for (int i=0;i<N;i++) {
    		st = new StringTokenizer(br.readLine());
    		int P = Integer.parseInt(st.nextToken());
    		int L = Integer.parseInt(st.nextToken());
    		
    		int[] toadd = new int[] {P, L};
    		minQuest.add(toadd);
    		maxQuest.add(toadd);
    		problems.put(P, toadd);
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	int M = Integer.parseInt(st.nextToken());
    	
    	for (int i=0;i<M;i++) {
    		st = new StringTokenizer(br.readLine());
    		String cmd = st.nextToken();
    		if (cmd.equals("add")) {
    			int P = Integer.parseInt(st.nextToken());
    			int L = Integer.parseInt(st.nextToken());
    			
    			int[] toadd = new int[] {P, L};
        		minQuest.add(toadd);
        		maxQuest.add(toadd);
        		problems.put(P, toadd);
    		} else if (cmd.equals("recommend")) {
    			int x = Integer.parseInt(st.nextToken());
    			if (x == 1) {
    				System.out.println(maxQuest.peek()[0]);
    			} else {
    				System.out.println(minQuest.peek()[0]);
    			}
    		} else if (cmd.equals("solved")) {
    			int P = Integer.parseInt(st.nextToken());
    			int[] toremove = problems.get(P);
    			problems.remove(P);
    			minQuest.remove(toremove);
    			maxQuest.remove(toremove);
    		}
    	}
    	
    }
}