import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static long [][] matrix;
    public static void main(String[] args) throws IOException {
        // 코드 작성

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        matrix = new long[2][2];

        matrix[0][0] = 1L;
        matrix[0][1] = 1L;
        matrix[1][0] = 1L;
        matrix[1][1] = 0;

        if (N == 0) {
            System.out.println(0);
            return;
        }

        long[][] result = answer(matrix, N - 1);

        System.out.println(result[0][0]);
    }

    static long[][] answer(long [][] matrix, long N) {

        if (N == 1 || N == 0) return matrix;

        long [][] half = answer(matrix, N / 2);
        long [][] result = cal(half, half);

        if (N % 2 == 1) {
            result = cal(result, matrix);
        }
        return result;
    }

    static long [][] cal(long[][] A, long[][] B) {

        long [][] result = new long[2][2];

        result[0][0] = (A[0][0] * B[0][0] % 1000000007 + A[0][1] * B[1][0] % 1000000007) % 1000000007;
        result[0][1] = (A[0][0] * B[0][1] % 1000000007 + A[0][1] * B[1][1] % 1000000007) % 1000000007;
        result[1][0] = (A[1][0] * B[0][0] % 1000000007 + A[1][1] * B[1][0] % 1000000007) % 1000000007;
        result[1][1] = (A[1][0] * B[0][1] % 1000000007 + A[1][1] * B[1][1] % 1000000007) % 1000000007;

        return result;
    }
}