import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder sb = new StringBuilder();
//    	Scanner sc = new Scanner(System.in);
    	int N = Integer.parseInt(br.readLine());
    	int[] nums = new int[N];
    	Map<Integer, Integer> count = new HashMap<>();
    	
    	StringTokenizer stok = new StringTokenizer(br.readLine());
    	for (int i=0;i<N;i++) {
    		nums[i] = Integer.parseInt(stok.nextToken());
    		if (count.get(nums[i]) == null) {
    			count.put(nums[i], 1);
    		} else {
    			count.put(nums[i], count.get(nums[i]) + 1);
    		}	
    	}
    	
    	int[] answers = new int[N];
    	Deque<int[]> st = new ArrayDeque<>();
    	
    	for (int i=N-1;i>=0;i--) {
    	    while (!st.isEmpty() && count.get(st.peek()[1]) <= count.get(nums[i])) {
    	        st.pop();
    	    }
    	    if (st.isEmpty()) {
    	        answers[i] = -1;
    	    } else {
    	        answers[i] = st.peek()[1];
    	    }
    	    st.push(new int[] {count.get(nums[i]), nums[i]});
    	}
    	for (int a: answers)
    		sb.append(a).append(" ");
    	bw.write(sb.toString());
    	bw.flush(); 
    }
}