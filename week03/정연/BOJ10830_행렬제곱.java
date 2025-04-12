import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder(); // 결과 출력을 위한 StringBuilder
    static int N; // 행렬의 크기
    static long B; // 거듭제곱 수
    static int[][] matrix; // 입력 행렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행렬 크기 입력
        B = Long.parseLong(st.nextToken()); // 제곱할 횟수 입력
        matrix = new int[N][N]; // 행렬 생성

        // 행렬 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행렬 A의 B제곱 계산
        int[][] result = POW(matrix, B);

        // 결과 출력 (모든 요소를 1000으로 나눈 나머지를 출력)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j] % 1000).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // 분할 정복을 이용한 거듭제곱 함수
    static int[][] POW(int[][] A, long B) {
        if (B == 1) { // B가 1이면 그대로 반환 (기저 조건)
            return A;
        }

        int[][] temp = POW(A, B / 2); // A^(B/2) 계산 (재귀 호출)

        if (B % 2 == 0) { // B가 짝수이면 temp * temp
            return MatrixMultiply(temp, temp);
        } else { // B가 홀수이면 A * temp * temp
            return MatrixMultiply(matrix, MatrixMultiply(temp, temp));
        }
    }

    // 행렬 곱셈 함수
    static int[][] MatrixMultiply(int[][] A, int[][] B) {
        int[][] result = new int[N][N]; // 결과 행렬

        // 행렬 곱셈 수행 (C[i][j] = sum(A[i][k] * B[k][j]))
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        // 각 원소를 1000으로 나눈 나머지로 변경
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] %= 1000;
            }
        }

        return result;
    }
}
