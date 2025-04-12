package 용범;

import java.io.*;
import java.util.*;

public class BOJ2357_최솟값과최댓값 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N, M, MIN, MAX, s, e, k, SIZE;
  static int[] minTree;
  static int[] maxTree;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    SIZE = getTreeSize();

    treeInit();
  }

  private static void solve() throws IOException {

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      // 리프 노드 인덱스로 변경
      s = Integer.parseInt(st.nextToken()) + (int) Math.pow(2, k) - 1;
      e = Integer.parseInt(st.nextToken()) + (int) Math.pow(2, k) - 1;

      MIN = queryMinValue(s, e);
      MAX = queryMaxValue(s, e);
      sb.append(MIN).append(" ").append(MAX).append("\n");
    }

    // 정답 출력
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
    maxTree = new int[SIZE];
    minTree = new int[SIZE];
    Arrays.fill(minTree, Integer.MAX_VALUE);

    int start = (int) Math.pow(2, k);
    for (int i = start; i < start + N; i++) {
      int num = Integer.parseInt(br.readLine());
      int idx = i;
      minTree[idx] = num;
      maxTree[idx] = num;
      while (idx > 0) {
        maxTree[idx / 2] = Math.max(maxTree[idx], maxTree[idx / 2]);
        minTree[idx / 2] = Math.min(minTree[idx], minTree[idx / 2]);
        idx /= 2;
      }
    }
  }

  private static int queryMaxValue(int s, int e) {
    int max = 0;
    while (s <= e) {
      // 자식의 오른쪽 노드인 경우 -> 독립 노드이므로 해당 노드 선택
      if (s % 2 == 1) {
        max = Math.max(max, maxTree[s]);
        s += 1;
      }
      // 자식의 왼쪽 노드인 경우 -> 독립 노드이므로 해당 노드 선택
      if (e % 2 == 0) {
        max = Math.max(max, maxTree[e]);
        e -= 1;
      }

      s /= 2;
      e /= 2;
    }

    return max;
  }

  private static int queryMinValue(int s, int e) {
    int min = Integer.MAX_VALUE;
    while (s <= e) {
      // 자식의 오른쪽 노드인 경우 -> 독립 노드이므로 해당 노드 선택
      if (s % 2 == 1) {
        min = Math.min(min, minTree[s]);
        s += 1;
      }
      // 자식의 왼쪽 노드인 경우 -> 독립 노드이므로 해당 노드 선택
      if (e % 2 == 0) {
        min = Math.min(min, minTree[e]);
        e -= 1;
      }

      s /= 2;
      e /= 2;
    }

    return min;
  }

}
