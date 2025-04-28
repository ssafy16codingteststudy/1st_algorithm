import java.io.*;
import java.util.*;



public class BOJ19598_최소회의실개수 {
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int[][] times = new int[N][2];
    	
    	for (int i=0;i<N;i++) {
    		st = new StringTokenizer(br.readLine());
    		times[i][0] = Integer.parseInt(st.nextToken());
    		times[i][1] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(times, (a, b) ->{
    		int cmp = Integer.compare(a[0], b[0]);
    		if (cmp == 0)
    			cmp = Integer.compare(a[1], b[1]);
    		return cmp;
    	});
    	
    	List<Integer> halls = new ArrayList<>();
    	halls.add(0);
    	for (int i=0;i<N;i++) {
    		boolean put = false;
    		for (int idx=0;idx<halls.size();idx++) {
    			if (halls.get(idx) <= times[i][0]) {
    				halls.remove(idx);
    				halls.add(times[i][1]);
    				put = true;
    				break;
    			}
    		}
    		if (!put) {
    			halls.add(times[i][1]);
    		}
    	}
    	
    	System.out.println(halls.size());
    }
}
