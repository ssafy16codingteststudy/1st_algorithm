import java.io.*;
import java.util.*;

public class Main {

	private static String inputs;
	private static String angel, devil;

	private static int answer;

	private static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
//		StringTokenizer st = new StringTokenizer(br.readLine());

		inputs = br.readLine();
		angel = br.readLine();
		devil = br.readLine();

		int len = angel.length();
		int inputLen = inputs.length();

		dp = new int[len][inputLen][2];

		// 초기값 -1로 설정 (계산되지 않은 값)
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < inputLen; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		// 첫 번째 글자를 angel에서 시작할 경우
		for (int i = 0; i < len; i++) {
			if (angel.charAt(i) == inputs.charAt(0)) {
				answer += dfs(i + 1, 1, 1); // 다음 depth, 다음 입력 index, side 전환
			}
		}
		// 첫 번째 글자를 devil에서 시작할 경우
		for (int i = 0; i < len; i++) {
			if (devil.charAt(i) == inputs.charAt(0)) {
				answer += dfs(i + 1, 1, 0); // 다음 depth, 다음 입력 index, side 전환
			}
		}

		sb.append(answer);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static int dfs(int depth, int count, int side) {
		if (count == inputs.length()) {
			return 1;
		}
		if (depth == angel.length()) {
			return 0;
		}

		if (dp[depth][count][side] != -1) {
			return dp[depth][count][side];
		}

		int res = 0;
		String currLine = (side == 0) ? angel : devil;

		for (int i = depth; i < currLine.length(); i++) {
			if (currLine.charAt(i) == inputs.charAt(count)) {
				res += dfs(i + 1, count + 1, (side + 1) % 2);
			}
		}

		dp[depth][count][side] = res;
		return res;
	}

}
