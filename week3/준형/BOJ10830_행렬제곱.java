import java.io.*;
import java.util.*;

// 행렬 제곱을 잘 몰라서 GPT 도움 받음 -> 자세히는 모름
public class Main {
    static int N;
    static long B;
    static int[][] map;
    static final int MOD = 1000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        map = new int[N][N];

        // 행렬 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // B제곱 계산
        int[][] result = power(map, B);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // 행렬 제곱 함수: 분할 정복을 사용
    static int[][] power(int[][] matrix, long exponent) {
        // 기저 사례: 지수가 1이면, 각 원소를 모듈로 연산하여 반환
        if (exponent == 1) {
            return modMatrix(matrix);
        }

        // 재귀로 반으로 나누어 계산
        int[][] half = power(matrix, exponent / 2);
        int[][] halfSquared = multiply(half, half);

        // 지수가 홀수이면, 한 번 더 곱해줘야 함
        if (exponent % 2 == 1) {
            halfSquared = multiply(halfSquared, matrix);
        }

        return halfSquared;
    }

    // 두 행렬 곱셈 함수 (모듈로 연산 포함)
    static int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 누적합을 계산하면서 MOD 적용
                for (int k = 0; k < N; k++) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return result;
    }

    // 행렬의 모든 원소에 대해 MOD 연산 수행 (문제의 조건에 맞게)
    static int[][] modMatrix(int[][] matrix) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = matrix[i][j] % MOD;
            }
        }
        return result;
    }
}
