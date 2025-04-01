package 용범;

import java.io.*;
import java.util.*;

public class BOJ1717_집합의표현 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int n, m, c, a, b;
  static int[] uf;

  public static void main(String[] args) throws IOException {
    init();
    solve();

    System.out.print(sb);
    br.close();
  }

  private static void init() throws IOException {

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
  }

  private static void solve() throws IOException {

    uf = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      uf[i] = i;
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      c = Integer.parseInt(st.nextToken()); // c: 0 - 합집합 / 1 - 같은 집합 확인
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      // c == 0 인 경우 -> 합집합 실행
      if (c == 0) {
        union(a, b);
      }
      // c == 1 인 경우 -> 같은 집합인지 확인
      else {
        if (find(a) == find(b)) {
          sb.append("YES").append("\n");
        } else {
          sb.append("NO").append("\n");
        }
      }
    }
  }

  private static void union(int x, int y) {

    int xRoot = find(x);
    int yRoot = find(y);
    if (xRoot == yRoot) {
      return;
    }

    if (xRoot > yRoot) {
      int tmp = xRoot;
      xRoot = yRoot;
      yRoot = tmp;
    }

    uf[yRoot] = xRoot;
  }

  private static int find(int x) {
    if (uf[x] != x) {
      uf[x] = find(uf[x]);
    }
    return uf[x];
  }
}
