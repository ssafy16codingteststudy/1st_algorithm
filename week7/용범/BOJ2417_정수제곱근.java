import java.io.*;
import java.util.*;

public class BOJ2417_정수제곱근 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static long N;

  public static void main(String[] args) throws IOException {

    // 입력 부분
    N = Long.parseLong(br.readLine());

    long ans = bs(); // 파라매트릭 서치 진행 -> 이분탐색 구현
    System.out.println(ans);
  }

  private static long bs() {

    long left = 0;
    long right = N - 1;
    long mid = -1;

    while (left <= right) {
      mid = (left + right) / 2;
      if (Math.pow(mid, 2) < N) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return left;
  }
}
