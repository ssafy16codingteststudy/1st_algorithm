package week4.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// O(N) ---분할과 정복---> O(log N)
public class BOJ11444_피보나치수6 {

    private static final long MOD = 1_000_000_007L;
    private static final int SIZE = 2;

    // 자연수 n <= 1,000,000,000,000,000,000
    // n번째 피보나치 수를 1,000,000,007으로 나눈 나머지
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.println(fibonacci(n));
    }

    private static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        }
        long[][] matrix = {{1, 1}, {1, 0}};
        return powerMatrix(matrix, n - 1)[0][0];
    }

    private static long[][] powerMatrix(long[][] matrix, long exp) {
        if (exp == 1) {
            return matrix;
        }

        long[][] half = powerMatrix(matrix, exp / 2);
        half = multiplyMatrix(half, half);

        if (exp % 2 == 0) {
            return half;
        }
        return multiplyMatrix(half, matrix);
    }

    private static long[][] multiplyMatrix(long[][] a, long[][] b) {
        long[][] result = new long[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return result;
    }
}
