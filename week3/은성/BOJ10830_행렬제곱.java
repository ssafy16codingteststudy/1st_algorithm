import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10830_행렬제곱 {
    public static int MOD = 1000;
    public static int N;
    public static Map<Long, int[][]> dp = new HashMap<>();  // 중간 결과값을 저장하는 dp

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        long b = Long.parseLong(split[1]);

        int[][] arr = new int[N][N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }
        dp.put(1L, arr);

        division(arr, b);
        int[][] answer = dp.get(b);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println(answer[i][N - 1]);
        }
    }

    private static int[][] division(int[][] arr, long b) {
        if (b == 1) {
            return arr;
        }

        // 이미 저장한 중간 값이 있다면 꺼내오기
        if(dp.containsKey(b)) {
            return dp.get(b);
        }

        // 홀수라면 짝수 만들기
        if (b % 2 == 1) {
            return multiple(arr, 1, division(arr, b - 1), b - 1);
        }

        // 분할하여 곱셈 시행
        return multiple(division(arr, b / 2), b / 2, division(arr, b / 2), b / 2);
    }

    // 행렬 곱셈(arr1, 지수1, arr2, 지수2)
    private static int[][] multiple(int[][] a, long index_a, int[][] b, long index_b) {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    tmp[i][j] += a[i][k] * b[k][j];
                }
                tmp[i][j] %= MOD;
            }
        }

        // 지수값을 key 값으로 활용하여 중간값을 map에 저장
        dp.put((index_a + index_b), tmp);
        return tmp;
    }
}