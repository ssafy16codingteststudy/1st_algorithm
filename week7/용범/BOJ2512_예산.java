import java.io.*;
import java.util.*;

public class BOJ2512_예산 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int N, M;
  static int[] arr;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {
    N = Integer.parseInt(br.readLine()); // N: 지방의 수
    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    M = Integer.parseInt(br.readLine()); // M: 총 예산
  }

  private static void solve() {
    Arrays.sort(arr); // 이분 탐색을 위한 오름차순 정렬
    System.out.println(bs());
  }

  private static int bs() {

    int left = 0;
    int right = arr[N - 1];
    int ans = -1;

    while (left <= right) {

      int mid = (left + right) / 2;
      int sum = 0;

      for (int i = 0; i < N; i++) {
        sum += Math.min(arr[i], mid);
      }

      if (sum <= M) {
        ans = Math.max(ans, mid);
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return ans;
  }
}


