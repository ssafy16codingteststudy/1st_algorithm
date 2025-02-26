import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] map;
	static int blue = 0;
	static int white = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// init
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// process
		divideArea(0, 0, N);

		System.out.println(white);
		System.out.println(blue);

	}

	static void divideArea(int starty, int startx, int size) {

		if (check(starty, startx, size)) {
			if (map[starty][startx] == 1 ) {
				blue += 1;
			} else {
				white += 1;
			}
			return;
		}

		int newSize = size / 2;

		divideArea(starty, startx, newSize);
		divideArea(starty, startx + newSize, newSize);
		divideArea(starty + newSize, startx, newSize);
		divideArea(starty + newSize, startx + newSize, newSize);
	}

	static boolean check(int starty, int startx, int size) {

		int color = map[starty][startx];
		for (int i = starty; i < starty + size; i++) {
			for (int j = startx; j < startx + size; j++) {
				if (map[i][j] != color) {
					return false;
				}
			}
		}
		return true;
	}
}