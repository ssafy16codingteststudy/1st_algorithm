package May1;

import java.util.*;

public class PG_두큐합같게만들기 {
	

	class Solution {
	    public int solution(int[] queue1, int[] queue2) {
	        long sum1 = 0;
	        long sum2 = 0;
	        Queue<Integer> q1 = new ArrayDeque();
	        Queue<Integer> q2 = new ArrayDeque();
	        int n = queue1.length;
	        for(int i = 0; i < n; i++){
	            sum1 += queue1[i];
	            sum2 += queue2[i];
	            q1.add(queue1[i]);
	            q2.add(queue2[i]);
	        }
	        long mid = (sum1 + sum2) / 2;
	        int count = 0;
	        while(sum1 != mid && count < 4 * n){
	            if(sum1 > mid){
	                int i = q1.poll();
	                q2.add(i);
	                sum1 -= i;
	                sum2 += i;
	            }else{
	                int i = q2.poll();
	                q1.add(i);
	                sum2 -= i;
	                sum1 += i;
	            }
	            count++;
	        }
	        int answer = -1;
	        if(count < 4 * n){
	            answer = count;
	        }
	        return answer;
	    }
	}
}
