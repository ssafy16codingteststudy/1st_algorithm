import java.io.*;
import java.util.*;


public class BOJ25947_선물할인 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int b = Integer.parseInt(st.nextToken());
    	int a = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	int[] prices = new int[n];
    	long[] sum = new long[n];
    	
    	for (int i=0;i<n;i++) {
    		prices[i] = Integer.parseInt(st.nextToken());
    	}
    	Arrays.sort(prices);
    	sum[0] = prices[0];
    	for (int i=1;i<n;i++) {
    		sum[i] = sum[i-1] + prices[i];
    	}
    	
    	int start = 0;
    	int end = n;
    	
    	while (start < end) {
    		int mid = (start + end) / 2;
    		long sumAll = sum[mid];
    		for (int i=mid;i > mid - a && i >= 0; i--) {
    			sumAll -= (prices[i]/2);
    		}
    		if (sumAll > b) {
    			end = mid;
    		} else {
    			start = mid + 1;
    		}
    		
    	}
    	
    	System.out.println(start);
    	
    }
}