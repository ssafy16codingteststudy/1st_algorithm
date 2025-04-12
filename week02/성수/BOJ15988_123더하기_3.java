//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class BOJ15988_123더하기_3 {
//
//	static int cnt = 0;
//	static final int MAX = 1000000009;
//	static int result;
//	static Queue<int[]> q =new ArrayDeque<>();
//	static StringBuilder sb = new StringBuilder();
//	
//	public static void main(String[] args) throws IOException {
//		//x +2y +3z = N; z = 0 -> y = 0  or 1 or 2 ...  -> y가 정해지면 x도 정해짐
//		//				 z = 1 -> y = 0 or 1 or 2 ... 
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
//		
//		for(int i=0; i<T; i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int N = Integer.parseInt(st.nextToken());
//			
//
//			for(int z=0; N-3*z >=0; z++) {
//				for(int y=0; N-3*z-2*y>=0; y++){
//					q.add(new int[]{N-3*z-2*y, y, z}); // 자리 비뀌는 연산을 사용하려 했으나 사용하게 되면 너무 큰 팩토리얼 계산을 해야함.
//				}
//			}
//			
//			result = cnt % MAX;
//			sb.append(result).append("\n");
//
//		}
//		System.out.print(sb.toString());
//		br.close();
//	}
//
//}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15988_123더하기_3 {
	
	static int maxN = 1_000_001;
	static long[] dp = new long[maxN];
	static final int MOD = 1_000_000_009;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		
		dp[1] = 1; //1
		dp[2] = 2; //1 1, 2
		dp[3] = 4; //1 1 1, 1 2, 2 1, 3
 		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int i=0; i<T; i++) {
			int n = Integer.parseInt(br.readLine());
			for(int j=4; j<=n; j++) {
				dp[j] = (dp[j-3] + dp[j-2] + dp[j-1])%MOD;
//				n을 만드는 조합 중 마지막이 +1인 경우 -> (n-1을 만드는 조합) + 1 -> dp[n-1]
//				n을 만드는 조합 중 마지막이 +2인 경우 -> (n-2를 만드는 조합) + 2 -> dp[n-2]
//				n을 만드는 조합 중 마지막이 +3인 경우 -> (n-3을 만드는 조합) + 3 -> dp[n-3]
			}
			sb.append(dp[n]).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
		
	}
}
