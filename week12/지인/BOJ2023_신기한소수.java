import java.io.*;
import java.util.*;


public class BOJ2023_신기한소수 {
    
static boolean sosu(int n) {
		if (n == 1)
			return false;
		if (n == 2 || n == 3)
			return true;
		
		for (int i=2;i <= Math.ceil(Math.sqrt(n) + 1); i++) {
			if (n % i == 0)
				return false;
		}
		
		return true;
	}
	
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int[] nums = new int[] {1,2,3,5,7,9};
    	List<Integer> sosus = new ArrayList<>();
    	for (int num: nums)
    		if (sosu(num))
    			sosus.add(num);
    	for (int i = 1; i < N;i++) {
    		List<Integer> newsosu = new ArrayList<>();
    		for (int idx =0; idx < sosus.size();idx++) {
    			for (int num: nums) {
    				int now = sosus.get(idx) * 10 + num;
    				if (sosu(now)) {
    					newsosu.add(now);
    				}
    			}
    		}
    		sosus = newsosu;
    	}
    	StringBuilder sb = new StringBuilder();
    	for (int ans: sosus)
    		sb.append(ans).append("\n");
    	System.out.println(sb);
    }
}