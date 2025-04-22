package 용범;

import java.io.*;
import java.util.*;

public class BOJ5052_전화번호목록 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();

  static int T, N;
  static String[] books;

  public static void main(String[] args) throws IOException {

    T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      init();
      solve();
    }

    // 정답 출력
    System.out.print(sb);
    br.close();
  }

  private static void init() throws IOException {

    // 입력 부분
    N = Integer.parseInt(br.readLine());
    books = new String[N];
    for (int i = 0; i < N; i++) {
      books[i] = br.readLine();
    }
  }

  private static void solve() {

    Arrays.sort(books);
    for (int i = 0; i < books.length - 1; i++) {
      String curr = books[i];
      String next = books[i + 1];

      if (next.startsWith(curr)) {
        sb.append("NO").append("\n");
        return;
      }
    }
    sb.append("YES").append("\n");
  }

}
