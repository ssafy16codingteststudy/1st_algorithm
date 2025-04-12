

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        long[] sum = new long[N + 1];
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;
        long maxVal = 0;
        
        for (int i=0;i<N;i++) {
        	arr[i] = sc.nextInt();
        	sum[i + 1] = sum[i] + arr[i]; 
        	map.putIfAbsent(arr[i], new ArrayList<>());
        	map.get(arr[i]).add(i);
        }
        
        
        for (int key: map.keySet()) {
        	List<Integer> keys = map.get(key);//sb 
        	if (keys.size() == 1) {  
//        		System.out.println(key + " " + keys.get(0) );
        		if (maxVal < arr[keys.get(0)]) {
        			maxVal = arr[keys.get(0)];
        			count = 1;
        		} else if ( maxVal ==  arr[keys.get(0)])
        			count++;
        			
        	} else {
        		int start = keys.get(0);
        		int end = keys.get(keys.size() -1);
        		long total = sum[end + 1] - sum[start];
        		if (maxVal < total) {
        			maxVal = total;
        			count = 1;
        		} else if (maxVal == total)
        			count++;
        	}
        }
        
        System.out.println(maxVal + " " + count);
        
        
    }
}

