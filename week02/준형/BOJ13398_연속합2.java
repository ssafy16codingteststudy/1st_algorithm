import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N][2]; // i까지 고려했을 때 삭제한 경우 / 삭제하지 않은 경우
		dp[0][0] = arr[0];
		dp[0][1] = arr[0];
		
		int ans = arr[0];
	
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]);
			
			dp[i][1] = Math.max(dp[i-1][1] + arr[i], dp[i-1][0]);
			
			ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
		}
		
		
		System.out.println(ans);
	}
}
-----------------------------------------------------------------------------------
public class Main {

	static int N;
	static int[] arr;
	static Long [][] dp;
	
	static long dfs(int i, int used) {
		
		if (i == 0) {
			return arr[0];
		}
		
		if (dp[i][used] != null) {
			return dp[i][used];
		}
		
		long res;
		
		if (used == 0) {
			res = Math.max(dfs(i - 1, 0) + arr[i], arr[i]);
		} else {
			res = Math.max(dfs(i - 1,  1) + arr[i], dfs(i - 1, 0));
		}
		
		dp[i][used] = res;
		return res;
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new Long[N][2]; // i까지 고려했을 때 삭제한 경우 / 삭제하지 않은 경우
		
		long ans = arr[0];
	
		for (int i = 1; i < N; i++) {
			ans = Math.max(ans, Math.max(dfs(i, 0), dfs(i, 1)));
		}
		
		
		System.out.println(ans);
	}
}
