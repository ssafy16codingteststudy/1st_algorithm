import java.io.*;
import java.util.*;

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
    
}

public class Main {

	private static int answer;

	private static int N, M, K;
	private static int[][][] dp;

	private static String toFind;
	private static char[][] map;

	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		toFind = br.readLine();

		dp = new int[N][M][toFind.length()];
		for (int[][] layer : dp) {
			for (int[] row : layer) {
				Arrays.fill(row, -1); // 초기값 -1로 설정
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == toFind.charAt(0)) {
					answer += dfs(new Pos(i, j), 1);
				}
			}
		}

		sb.append(answer);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static int dfs(Pos cur, int depth) {
		// 종료 조건을 설정할 때, 끝점인지 먼저 확인하고 넘어가기
		if (depth == toFind.length()) {
			return 1; // 끝까지 찾았으니 1가지 경우
		}

		if (dp[cur.x][cur.y][depth] != -1) {
			return dp[cur.x][cur.y][depth];
		}

		int value = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= K; j++) {
				int nx = cur.x + dx[i] * j, ny = cur.y + dy[i] * j;

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (map[nx][ny] != toFind.charAt(depth))
					continue;
				// 기존에는 여기에서 dp[nx][ny]가 방문한 곳이라면 그 값을 더해주는 방식으로 풀이했음
				// 하지만 그렇게 하면 다음 점이 끝점인지, 중간에 거치는 점인지 알 수 없음
				// 다음 점이 끝점이라면 새로운 길을 개척한 것인데,해당 점의 값을 가져오게 되면 원래 구하려는 길이보다 더 긴 길이를 구하게 됨

				value += dfs(new Pos(nx, ny), depth + 1);
			}
		}

		return dp[cur.x][cur.y][depth] = value;
	}

}
