package May1;

public class PG_아방가르드타일링 {
	class Solution {
	    public int solution(int n) {
	        int answer = 0;
	        long[] count = new long[n+1];
	        long[] sum = new long[n+1];
		    count[0] = 1;
		    sum[0] = 1;
		    count[1] = 1; 
		    sum[1] = 1;
		    if(n >= 2){
		        count[2] = 3;
		        sum[2] = 3;
		    }
		    if(n >= 3){
		        count[3] = 10;
		        sum[3] = count[3] + sum[0];
		    } 
		     if(n >= 4){
		        count[4] = count[3] + 2 * count[2] + 5 * count[1] + count[0] * 2;
		         sum[4] = count[4] + sum[1];
		    }
		    if(n >= 5){
		        count[5] = count[4] + 2 * count[3] + 5 * count[2] + count[1] * 2 + count[0] * 2;
		        sum[5] = count[5] + sum[2];
		    }
		    if(n >= 6){
		        count[6] = count[5] + 2 * count[4] + 5 * count[3] + count[2] * 2 + count[1] * 2 + count[0] * 4;
		        sum[6] = count[6] + sum[3];
		    } 
		    for(int i = 7; i <= n; i++){
		        long a = count[i - 1] + 2 * count[i- 2] + 5 * count[i - 3] + 2 * sum[i - 4] + 2 * sum[i - 5] + 4 * sum[i - 6];
		        count[i] = a % 1000000007L;
		        sum[i] = (count[i] + sum[i-3]) % 1000000007L;
		    }
		    answer = (int)count[n];
		    return answer;
	    }
	}
}
