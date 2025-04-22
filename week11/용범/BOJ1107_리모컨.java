package 용범;

import java.io.*;
import java.util.*;

public class BOJ1107_리모컨 {

  static final int MAX = 500_000;

  static String N;
  static int M, LEN, MIN = 987_654_321, closeNumber = 987_654_321;
  static Set<Integer> regard;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 입력 부분 
    N = br.readLine();
    LEN = N.length(); // 채널의 길이
    M = Integer.parseInt(br.readLine()); // M: 고장난 버튼의 개수

    regard = new HashSet<>(); // 무시해야 할 버튼 추가
    // 무시해야 할 버튼이 있는 경우에만 -> 입력을 받는다.
    if (M > 0) {
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < M; i++) {
        regard.add(Integer.parseInt(st.nextToken()));
      }
    }
    br.close();
  }

  private static void solve() {

    getCloseNumber("");
    int click = Math.abs(Integer.parseInt(N) - 100); // ++ 또는 -- 클릭으로만 가는 방법 수
    int remote = String.valueOf(closeNumber).length() +
                 Math.abs(closeNumber - Integer.parseInt(N)); // 숫자를 눌러야 하는 방법 수

    System.out.print(Math.min(click, remote));
  }

  private static void getCloseNumber(String str) {

    if (str.length() > 6) {
      return;
    }

    if (!str.isEmpty()) {
      int diff = Math.abs(Integer.parseInt(str) - Integer.parseInt(N));
      // 차이가 더 적은 수가 나오는 경우 -> 갱신
      if (MIN > diff) {
        MIN = diff; // 차이 최솟값 갱신
        closeNumber = Integer.parseInt(str); // 가능한 가장 가까운 숫자 갱신
      }
      // 차이가 같은 경우 -> 자릿수가 더 짧은 수로 갱신
      else if (MIN == diff) {
        int closeNumberLength = String.valueOf(closeNumber).length();
        int currNumLength = str.length();

        if (closeNumberLength > currNumLength) {
          closeNumber = Integer.parseInt(str);
        }
      }
    }

    for (int i = 0; i < 10; i++) {
      // 이미 방문한 경우 -> 탐색하지 않는다.
      if (regard.contains(i)) {
        continue;
      }
      str = str.concat(String.valueOf(i));
      getCloseNumber(str);
      str = str.substring(0, str.length() - 1);
    }
  }
}
