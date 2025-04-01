package 용범;

import java.io.*;
import java.util.*;

public class BOJ20040_사이클게임 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  static int n, m, a, b;
  static int[] uf;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken()); // n: 점의 개수
    m = Integer.parseInt(st.nextToken()); // m: 진행된 차례의 수
  }

  private static void solve() throws IOException {

    uf = new int[n];
    for (int i = 0; i < n; i++) {
      uf[i] = i;
    }

    int cycle = 0;
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());

      // 부모 번호가 같다면 -> 싸이클이 생성되었다는 의미
      if (find(a) == find(b) && cycle == 0) {
        cycle = i + 1;
      }

      union(a, b);
    }

    System.out.println(cycle);
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
