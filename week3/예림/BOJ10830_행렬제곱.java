package week3.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10830_행렬제곱 {

    // 2 <= N <= 5
    // 1 <= B <= 100,000,000,000
    // 0 <= 행렬의 각 원소 <= 1,000
    private static final int MOD = 1_000;
    private static int n;
    private static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] answer = solution(matrix, b);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int[][] solution(int[][] matrix, long b) {
        if (b == 1) {
            int[][] result = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[i][j] = matrix[i][j] % MOD;
                }
            }
            return result;
        }

        int[][] half = solution(matrix, b / 2);
        if (b % 2 == 0) { // 짝수: half * half
            return matrixMultiply(half, half);
        } else { // 홀수: half * half * matrix
            return matrixMultiply(matrixMultiply(half, half), matrix);
        }
    }

    private static int[][] matrixMultiply(int[][] a, int[][] b) {
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += (a[i][k] * b[k][j]) % MOD;
                    sum %= MOD;
                }
                result[i][j] = sum;
            }
        }

        return result;
    }
}
