import java.io.*;
import java.util.*;

public class Main {
	
	public static boolean candivide(int[] nums, int range, int M) {
		int N = nums.length;
		int min = nums[0];
		int max = nums[0];
		int count = 1;
		
		for (int i=0;i<N;i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
			
			if (max - min > range) {
				count++;
				min = nums[i];
				max = nums[i];
			}
			if (count > M)
				return false;
		}
		
		return true;
	}
	
	
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        int[] nums = new int[N];
        int min = 10000;
        int max = 1;
        for (int i=0;i<N;i++) {
        	nums[i] = sc.nextInt();
        	min = Math.min(min, nums[i]);
        	max = Math.max(max, nums[i]);
        }
        
        
        int start = 0;
        int end = max;
        int mid = (start + end) / 2;
        while (start <= end) {
        	mid = (start + end) / 2;
        	if (candivide(nums, mid, M)) {
        		end = mid - 1;
        	} else {
        		start = mid + 1;
        	}
        }
        
        System.out.println(start);
        
    }
}
