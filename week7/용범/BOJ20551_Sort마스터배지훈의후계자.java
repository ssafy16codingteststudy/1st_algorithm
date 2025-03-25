import java.io.*;
import java.util.*;

public class BOJ20551_Sort마스터배지훈의후계자 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  static int N, M, num;
  static int[] arr;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    // 이분 탐색을 위한 오름차순 정렬
    Arrays.sort(arr);
  }

  private static void solve() throws IOException {

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < M; i++) {
      num = Integer.parseInt(br.readLine());
      // 인덱스를 찾기 위한 이분탐색 진행
      sb.append(bs(num)).append("\n");
    }

    // 정답 출력 및 버퍼 닫기
    System.out.println(sb);
    br.close();
  }

  private static int bs(int target) {

    int left = 0;
    int right = N - 1;
    int ans = -1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (target <= arr[mid]) {
        right = mid - 1;
        // 주어진 target 값과 일치한다면 -> 처음 마주친 인덱스 저장
        if (target == arr[mid]) {
          ans = mid;
        }
      } else {
        left = mid + 1;
      }
    }

    return ans;
  }
}
