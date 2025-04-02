package 용범;

import java.io.*;
import java.util.*;

public class BOJ4195_친구네트워크 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static final int MAX = 100_000;
  static int T, F;
  static int[] uf;
  static Map<String, Integer> map;
  static Map<Integer, Set<Integer>> network;
  static String A, B;

  public static void main(String[] args) throws IOException {

    T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      init();
      solve();
    }

    // 정답 출력 부분
    System.out.println(sb);
    br.close();
  }

  private static void init() throws IOException {

    // uf 배열 초기화
    uf = new int[(2 * MAX) + 1];
    for (int i = 0; i <= 2 * MAX; i++) {
      uf[i] = i;
    }

    // 입력 부분
    F = Integer.parseInt(br.readLine()); // F: 친구 관계의 수
    map = new HashMap<>();
    network = new HashMap<>();
  }


  private static void solve() throws IOException {

    int idx = 0; // 문자열 개수

    for (int i = 0; i < F; i++) {
      // 문자열 입력 부분
      st = new StringTokenizer(br.readLine());
      A = st.nextToken();
      B = st.nextToken();
      if (!map.containsKey(A)) {
        map.put(A, idx++);
      }

      if (!map.containsKey(B)) {
        map.put(B, idx++);
      }

      int x = map.get(A);
      int y = map.get(B);

      int xRoot = find(x);
      int yRoot = find(y);

      // 두 집합이 같은 집합이 아니라면 -> 합집합
      if (xRoot != yRoot) {

        if (xRoot > yRoot) {
          int tmp = xRoot;
          xRoot = yRoot;
          yRoot = tmp;
        }

        if (!network.containsKey(xRoot)) {
          network.put(xRoot, new HashSet<>());
        }
        network.get(xRoot).add(x);
        network.get(xRoot).add(y);

        for (Integer friend : network.getOrDefault(yRoot, new HashSet<>())) {
          network.get(xRoot).add(friend);
        }
        union(xRoot, yRoot); // x <-> y 합집합
      }
      sb.append(network.get(xRoot).size()).append("\n");
    }
  }

  private static void union(int xRoot, int yRoot) {

    if (xRoot == yRoot) {
      return;
    }

    uf[yRoot] = xRoot; // 작은 노드를 부모 노드로 설정
  }

  private static int find(int x) {
    if (uf[x] != x) {
      uf[x] = find(uf[x]);
    }
    return uf[x];
  }

}