package week3.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630_색종이만들기 {

    private static int[] count;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        count = new int[2];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0, 0, n);

        System.out.println(count[0]);
        System.out.println(count[1]);
    }

    private static void solution(int r, int c, int size) {
        if (size == 1 || isSameColor(r, c, size)) {
            count[map[r][c]]++;
            return;
        }
        size /= 2;
        solution(r, c, size); // 1
        solution(r, c + size, size); // 2
        solution(r + size, c, size); // 3
        solution(r + size, c + size, size); // 4
    }

    private static boolean isSameColor(int r, int c, int size) {
        int color = map[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (color != map[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
