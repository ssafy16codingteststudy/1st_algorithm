package April_week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1563_개근상 {

	static final int MOD = 1000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(bf.readLine());
		int[] nums = new int[N+1]; // L없이, AAA 없이 만들 수 있는 경우의 수
		nums[0] = 1;
		if (N >= 1) nums[1] = 2;
		if (N >= 2) nums[2] = 4;
		for (int i = 3; i <= N; i++) {
			// n-1자리에 맨 뒤에 o 추가, n-2자리에 oa 추가, n-3자리에 oaa추가 => n자리 가능한 수 
		    nums[i] = ((nums[i-1] + nums[i-2]) % MOD + nums[i-3]) % MOD;
		}
		
		long answer = 0;
		for (int k = 0; k <= N-1; k++) { //L의 위치를 정함
			//k자리 가능....L....N-1-k자리 가능 => k 자리 가능한 수 * N-1-k자리 가능한 수
			answer = (answer + (long)nums[k] * nums[N-1-k]) % MOD;
		}

		answer = (nums[N] + answer) % MOD;
		System.out.println(answer);
		bf.close();
	}
}
