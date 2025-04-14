import java.io.*;
import java.util.*;

public class BOJ_23300_웹브라우저2 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int Q = Integer.parseInt(st.nextToken());
    	Deque<Integer> stBack = new ArrayDeque<>();
    	Deque<Integer> stForward = new ArrayDeque<>();
    	int now = 0;
    	
    	for (int cnt = 0;cnt < Q; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		String cmd = st.nextToken();
    		if (cmd.equals("A")) {
    			int page = Integer.parseInt(st.nextToken());
    			if (now != 0)
    				stBack.addFirst(now);
    			stForward.clear();
    			now = page;
    		} else {
    			if (cmd.equals("B")) {
    				if (stBack.size() > 0) {
    					stForward.addFirst(now);
    					now = stBack.pollFirst();
    				}
    			} else if (cmd.equals("F")) {
    				if (stForward.size() > 0) {
    					stBack.addFirst(now);
    					now = stForward.pollFirst();
    				}
    			} else if (cmd.equals("C")) {
    				Deque<Integer> temp = new ArrayDeque<>();
    				int prev = 0;
    				while (stBack.size() > 0) {
	    				if (temp.size() == 0 || prev != stBack.peekFirst()){
	    					prev = stBack.pollFirst();
	    					temp.addLast(prev);
	    				} else if (prev == stBack.peekFirst()) {
	    					prev = stBack.pollFirst();
	    				}
    				}
    				stBack = temp;
    			}
    		}
    		
    	}
    	
    	System.out.println(now);
    	if (stBack.size() == 0)
    		System.out.println("-1");
    	else {
    		while (stBack.size() > 0)
    			System.out.printf("%d ", stBack.pollFirst());
    		System.out.println();
    	}
    	if (stForward.size() == 0)
    		System.out.println("-1");
    	else {
    		while (stForward.size() > 0)
    			System.out.printf("%d ", stForward.pollFirst());
    		System.out.println();
    	}
    }
}