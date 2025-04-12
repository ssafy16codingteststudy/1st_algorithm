package 용범;

import java.io.*;
import java.util.*;

public class BOJ16562_친구비 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  static int N, M, k, v, w;
  static int[] A, uf;
  static Map<Integer, ArrayList<Integer>> map;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    A = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }

    // union-find 배열 초기화
    uf = new int[N + 1];
    for (int i = 0; i <= N; i++) {
      uf[i] = i;
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      v = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());

      union(v, w); // 두 친구 관계 -> 합집합
    }
  }

  private static void solve() {

    map = new HashMap<>();
    for (int i = 0; i < N; i++) {
      int parent = find(i + 1);
      // 해당 부모 노드가 없다면 -> 새롭게 추가
      if (!map.containsKey(parent)) {
        map.put(parent, new ArrayList<>());
      }
      map.get(parent).add(A[i]); // 친구비를 저장한다.
    }

    int total = 0;
    for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
      entry.getValue().sort(Comparator.naturalOrder()); // 해당 집합의 친구비를 오름차순 정렬
      total += entry.getValue().get(0); // 가장 저렴한 친구비를 더한다.
    }

    if (total <= k) {
      System.out.println(total);
    } else {
      System.out.println("Oh no");
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
