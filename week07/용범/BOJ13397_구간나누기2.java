import java.io.*;
import java.util.*;

public class BOJ13397_구간나누기2 {

  static int N, M;
  static int[] arr;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    br.close();
  }

  private static void solve() {

    int left = 0;
    int right = 9_999;
    int ans = 0;

    while (left <= right) {
      int mid = (left + right) / 2;
      int cnt = getInterval(mid);
      // 구간의 개수가 M 이하인 경우 -> 상한을 낮춘다.
      if (cnt <= M) {
        right = mid - 1;
        ans = mid;
      }
      // 구간의 개수가 M 초과인 경우 -> 상한을 높인다.
      else {
        left = mid + 1;
      }
    }

    System.out.println(ans);
  }

  private static int getInterval(int mid) {

    int cnt = 1, MAX = 0, MIN = 10_000;
    for (int i = 0; i < N; i++) {
      MAX = Math.max(MAX, arr[i]);
      MIN = Math.min(MIN, arr[i]);
      if (MAX - MIN > mid) {
        MAX = 0;
        MIN = 10_000;
        cnt++;
        i--;
      }
    }

    return cnt;
  }
}
