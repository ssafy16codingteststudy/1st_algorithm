package 용범;

import java.io.*;
import java.util.*;

public class BOJ2470_두용액 {

  static int N;
  static int[] arr;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 입력 부분
    N = Integer.parseInt(br.readLine()); // N: 용액의 수
    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    br.close();
  }

  private static void solve() {

    Arrays.sort(arr); // 정렬

    // 모두 양수인 경우 -> 처음 두 숫자가 정답
    if (arr[0] > 0) {
      System.out.printf("%d %d", arr[0], arr[1]);
    }
    // 모두 음수인 경우 -> 마지막 두 숫자가 정답
    else if (arr[arr.length - 1] < 0) {
      System.out.printf("%d %d", arr[arr.length - 2], arr[arr.length - 1]);
    }
    // 양수와 음수가 섞여있는 경우 -> 투포인터 적용
    else {
      int[] ans = bs();
      System.out.printf("%d %d", ans[0], ans[1]);
    }
  }

  private static int[] bs() {

    int left = 0;
    int right = arr.length - 1;

    int MIN = Integer.MAX_VALUE;
    int[] res = new int[]{arr[left], arr[right]};
    while (left < right) {
      int diff = (arr[left] + arr[right]);
      // 0과의 거리가 갱신된다면 -> 갱신한다.
      if (Math.abs(diff) < MIN) {
        MIN = Math.abs(diff);
        res[0] = arr[left];
        res[1] = arr[right];
      }

      // 차이가 음수라면 -> 음수를 줄인다.
      if (diff < 0) {
        left++;
      }
      // 차이가 음이 아닌 수라면 -> 양수를 줄인다.
      else {
        right--;
      }
    }

    return res;
  }

}
