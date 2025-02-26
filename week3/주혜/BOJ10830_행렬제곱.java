import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");

        N = Integer.parseInt(tokens[0]);
        Long B = Long.parseLong(tokens[1]);

        int [][] matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(tokens[j]) % 1000;
            }
        }

        int[][] result = answer(matrix, B);

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] answer(int [][] matrix, Long B) {
        if (B == 1) return matrix;

        int [][] half = answer(matrix, B / 2);
        int[][] result = multiply(half, half);

        if (B % 2 == 1) {
            result = multiply(result, matrix);
        }
        return result;

    }

    static int[][] multiply(int[][] A, int[][] B) {
        int[][] C = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                    C[i][j] %= 1000;
                }
            }
        }
        return C;
    }
}
