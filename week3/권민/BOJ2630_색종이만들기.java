import java.util.*;
import java.io.*;

public class Main {

	private static int N, c1, c2;
	private static int[][] paper;

	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		paper = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		p(0, 0, N);
		sb.append(c2).append("\n").append(c1);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static void p(int r, int c, int length) {
		if (length == 1) {
			if (paper[r][c] == 1) {
				c1++;
			} else {
				c2++;
			}

			return;
		}

		int count1 = 0, count2 = 0;
		for (int i = r; i < r + length; i++) {
			boolean flag = false;
			for (int j = c; j < c + length; j++) {
				if (count1 != 0 && count2 != 0) {
					flag = true;
					break;
				}
				if (paper[i][j] == 1) {
					count1++;
				} else {
					count2++;
				}
			}

			if (flag)
				break;
		}

		if (count1 == length * length) {
			c1++;
		} else if (count2 == length * length) {
			c2++;
		} else {
			int newLen = length / 2;
			p(r, c, newLen);
			p(r + newLen, c, newLen);
			p(r, c + newLen, newLen);
			p(r + newLen, c + newLen, newLen);
		}

	}

}
