import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    // 그래프의 노드는 1~N*N 으로 표현
    static int[] arr;
    static int N;
    static int[][] sunny;
    static int[][] storm;
    static int result = 1;
    static int maxH = 1;
    static int minH = 100;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int localResult;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N * 2 + 1];
        sunny = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(input[j]);
                sunny[i][j] = v;
                if (maxH < v)
                    maxH = v;
                if (minH > v) {
                    minH = v;
                }
            }
        }

        for (int i = minH; i < maxH; i++) {
            doStorm(i);
            localResult = 0;
            // printStorm();
            // System.out.println(i + " (rain H)------------------------------");
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (storm[x][y] == 0)
                        continue;

                    localResult++;
                    storm[x][y] = 0;
                    dfs(x, y);
                }
            }
            // printStorm();
            // System.out.println(i + " (rain H<complete>)------------------------------");
            if (result < localResult) {
                result = localResult;
            }
        }
        System.out.println(result);
    }

    private static void printStorm() {
        for (int[] s : storm) {
            System.out.println(Arrays.toString(s));
        }
    }

    // 순회 돌며 잠기는 지점 0으로 만들기(깊은 복사로 사용하기)
    private static void doStorm(int level) {
        storm = new int[N][N];
        for (int i = 0; i < N; i++) {
            storm[i] = sunny[i].clone();
            for (int j = 0; j < N; j++) {
                if (storm[i][j] <= level)
                    storm[i][j] = 0;
            }
        }
    }

    // 순회 돌며 graph 만들기

    // 탐색으로 graph 개수 count
    private static void dfs(int x, int y) {

        for (int k = 0; k < 4; k++) {
            int nx = dx[k] + x;
            int ny = dy[k] + y;
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || storm[nx][ny] == 0)
                continue;
            storm[nx][ny] = 0;
            dfs(nx, ny);
        }
    }
}
