import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, white, blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        slicer(arr);
        System.out.println(white);
        System.out.println(blue);

    }

    static void slicer(int[][] arr) {
        int n = arr.length;
        if (n == 1 || isSame(arr, n, arr[0][0])) {
            if (arr[0][0] == 0) {
                white++;
                return;
            } else {
                blue++;
                return;
            }
        }

        int[][][] pieces = new int[4][n / 2][n / 2];
        int[] nx = { 0, 0, n / 2, n / 2 };
        int[] ny = { 0, n / 2, 0, n / 2 };

        for (int piece = 0; piece < 4; piece++) {
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {

                    pieces[piece][i][j] = arr[i + nx[piece]][j + ny[piece]];
                }
            }
            slicer(pieces[piece]);
        }

    }

    static boolean isSame(int[][] arr, int n, int flag) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != flag) {
                    return false;
                }
            }
        }
        return true;
    }
}
