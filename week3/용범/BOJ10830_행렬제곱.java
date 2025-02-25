package 용범;

import java.io.*;
import java.util.*;

public class BOJ10830_행렬제곱 {

  static final int MOD = 1_000;

  static int N;
  static long B;
  static int[][] A, res, ans, tmp;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    // 입력 부분
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // N: 행렬의 크기
    B = Long.parseLong(st.nextToken()); // B: 제곱 횟수

    A = new int[N][N]; // mat: N x N 행렬
    tmp = new int[N][N]; // tmp: 계산을 도와주는 용도의 행렬
    // 행렬 정보 저장
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    br.close();
  }

  private static void solve() {

    StringBuilder sb = new StringBuilder();

    ans = divide(B);

    // 정답 양식 저장
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        sb.append(ans[i][j] % MOD).append(" ");
      }
      sb.append("\n");
    }

    // 정답 출력
    System.out.println(sb.toString());
  }

  private static int[][] divide(long cnt) {

    if (cnt == 1) {
      return A;
    }

    // 제곱 횟수가 홀수인 경우 -> A를 한번 더 곱해줘야 한다.
    if (cnt % 2 == 1) {
      tmp = divide(cnt / 2);
      tmp = matrixMultiply(tmp, tmp); // A^cnt * A^cnt = A^2*cnt
      tmp = matrixMultiply(tmp, A); // A^(2*cnt + 1)
    } else {
      tmp = divide(cnt / 2);
      tmp = matrixMultiply(tmp, tmp);
    }

    return tmp;
  }


  private static int[][] matrixMultiply(int[][] a, int[][] b) {

    res = new int[N][N];
    // 행렬 곱 구현
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < N; k++) {
          res[i][j] += a[i][k] * b[k][j];
        }
        res[i][j] %= MOD;
      }
    }

    return res;
  }

}
