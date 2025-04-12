package 용범;

import java.io.*;
import java.util.*;

public class BOJ18436_수열과쿼리37 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N, M, t, x, r, l, k, SIZE;
  static int[][] tree;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    N = Integer.parseInt(br.readLine());
    SIZE = getTreeSize();
    tree = new int[SIZE][2];
    treeInit();
  }

  private static void solve() throws IOException {

    M = Integer.parseInt(br.readLine());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      switch (command) {
        case 1:
          t = Integer.parseInt(st.nextToken());
          x = Integer.parseInt(st.nextToken());
          update(t, x);
          break;
        case 2:
          l = Integer.parseInt(st.nextToken()) + (1 << k) - 1;
          r = Integer.parseInt(st.nextToken()) + (1 << k) - 1;
          sb.append(queryEven(l, r)).append("\n");
          break;
        case 3:
          l = Integer.parseInt(st.nextToken()) + (1 << k) - 1;
          r = Integer.parseInt(st.nextToken()) + (1 << k) - 1;
          sb.append(queryOdd(l, r)).append("\n");
          break;
      }
    }

    System.out.print(sb);
    br.close();
  }

  private static int getTreeSize() {

    k = 0;
    int len = 1;
    while (len < N) {
      len *= 2;
      k++;
    }

    // 2^k >= N
    return len * 2;
  }

  private static void treeInit() throws IOException {
    int start = 1 << k;
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(st.nextToken());
      int idx = start + i;
      if (num % 2 == 0) {
        tree[idx][0] = 1;
      } else {
        tree[idx][1] = 1;
      }
    }
    for (int i = start - 1; i > 0; i--) {
      tree[i][0] = tree[i * 2][0] + tree[i * 2 + 1][0];
      tree[i][1] = tree[i * 2][1] + tree[i * 2 + 1][1];
    }
  }

  private static void update(int i, int x) {

    // 리프노드 인덱스로 변경
    int idx = i + (1 << k) - 1;
    tree[idx] = new int[]{x % 2 == 0 ? 1 : 0, x % 2 == 1 ? 1 : 0};

    // 자식 노드의 합산을 부모 노드에 반영
    while (idx > 1) {
      idx /= 2;
      tree[idx][0] = tree[idx * 2][0] + tree[idx * 2 + 1][0];
      tree[idx][1] = tree[idx * 2][1] + tree[idx * 2 + 1][1];
    }
  }

  private static int queryEven(int l, int r) {

    int even = 0;
    while (l <= r) {
      if (l % 2 == 1) {
        even += tree[l][0];
        l++;
      }

      if (r % 2 == 0) {
        even += tree[r][0];
        r--;
      }

      l /= 2;
      r /= 2;
    }

    return even;
  }

  private static int queryOdd(int l, int r) {

    int odd = 0;
    while (l <= r) {
      if (l % 2 == 1) {
        odd += tree[l][1];
        l++;
      }

      if (r % 2 == 0) {
        odd += tree[r][1];
        r--;
      }

      l /= 2;
      r /= 2;
    }

    return odd;
  }
}
