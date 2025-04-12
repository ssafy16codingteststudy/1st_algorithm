import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4479_칸토어집합 {	
	
	private static StringBuilder sb = new StringBuilder();
	private static String[] dp;			// - 을 담을 dp 
	private static String[] spaceDp;	// 공백을 담을 dp


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = "";
		dp = new String[13];
		spaceDp = new String[13];
		
		while ((str = br.readLine()) != null) {
			int n = Integer.parseInt(str);
			
			int num = 1;
			for (int i = 0; i < n; i++) {
				num *= 3;
			}

			sb.append(dfs(num, n)).append("\n");			
			//System.out.println(dfs(num, n));
		}
		System.out.println(sb);
	}
	
	
	private static String dfs(int length, int n) {
		if(length == 1) {
			return "-";
		}
		
		// 이미 저장한 값이 있다면 바로 꺼내오기
		if(dp[n] != null) {
			return dp[n];
		}

		int sub  = length / 3;
		
		// 분할하면서 dp 에 저장
		return dp[n] = dfs(sub, n - 1) + fill(sub, n - 1) + dfs(sub, n - 1);
	}
	
	private static String fill(int length, int n) {
		if(length == 1) {
			return " ";
		}
		
		if(spaceDp[n] != null) {
			return spaceDp[n];
		}
		
		int sub = length / 3;
		
		return spaceDp[n] = fill(sub, n - 1) + fill(sub, n - 1) + fill(sub, n - 1);
	}
}