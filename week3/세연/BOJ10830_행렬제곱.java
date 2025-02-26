import java.io.*;
import java.util.*;

public class Main {
    static int A;
    static int [][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 입력 받기
        A = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        matrix = new int [A][A];
        for (int i = 0; i < A; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < A; j++) matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000; // 입력 받을 때 1000의 나머지 연산
        }
        // 행렬 제곱 계산
        matrix = powMatrix(B);
        // 결과 출력
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < A; j++)
                sb.append(matrix[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static int[][] powMatrix(long exponent) {
        if (exponent == 1) return matrix; // 재귀 종료 조건 : 1제곱은 초기 행렬을 반환
        else if (exponent == 2) return mulMatrix(matrix, matrix); // 재귀 종료 조건 : 2제곱은 직접 계산한 결과 행렬 반환
        else { // 재귀 조건 : 반으로 나누어 제곱함수 호출하고 결과를 합침
            int[][] tmp = powMatrix(exponent / 2); // 두개의 서브 태스크로 나눈 부분 계산(재귀 호출)
            return exponent%2 == 0 ? mulMatrix(tmp, tmp) : mulMatrix(matrix, mulMatrix(tmp, tmp)); // 짝수번 재곱할 경우 부분 연산 후 2제곱 계산. 홀수번 제곱할 경우 부분 연산+제곱 계산 후 원래 행렬 곱셈 추가로 수행
        }
    }

    static int[][] mulMatrix(int[][] m1, int[][] m2) { // 행렬 곱 함수
        int[][] result = new int[A][A];
        for (int i = 0; i < A; i++){
            for (int j = 0; j < A; j++){
                for (int k = 0; k < A; k++) result[i][j] += m1[i][k] * m2[k][j];
                result[i][j] %= 1000;
            }
        }
        return result;
    }
}
