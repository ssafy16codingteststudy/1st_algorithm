import java.io.*;
import java.util.*;

public class BOJ19637_IF문좀대신써줘 {

  static class Info {

    String name;
    int limit;

    Info(String name, int limit) {
      this.name = name;
      this.limit = limit;
    }
  }

  static int N, M, limit, p;
  static String name;
  static ArrayList<Info> arr;
  static Set<Integer> cache;
  static int[] power;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    // 입력 부분
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    cache = new HashSet<>();
    arr = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      name = st.nextToken();
      limit = Integer.parseInt(st.nextToken());
      if (cache.contains(limit)) {
        continue;
      }
      cache.add(limit);
      arr.add(new Info(name, limit));
    }

    power = new int[M];
    for (int i = 0; i < M; i++) {
      p = Integer.parseInt(br.readLine());
      power[i] = p;
    }

    br.close();
  }

  private static void solve() {
    StringBuilder sb = new StringBuilder();

    for (int p : power) {
      int idx = bs(p);
      sb.append(arr.get(idx).name).append("\n");
    }

    // 정답 출력
    System.out.print(sb);
  }

  private static int bs(int target) {

    int left = 0;
    int right = arr.size() - 1;
    int ans = right;

    while (left <= right) {
      int mid = (left + right) / 2;

      // 전투력 상한값이 현재 전투력보다 크거나 같은 경우 -> 상한을 낮춘다.
      if (target <= arr.get(mid).limit) {
        right = mid - 1;
        ans = mid;
      }
      // 전투력 상한값이 현재 전투력보다 작거나 같은 경우 -> 상한을 높인다.
      else {
        left = mid + 1;
      }
    }

    return ans;
  }
}
