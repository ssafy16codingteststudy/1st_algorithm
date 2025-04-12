package 용범;

import java.io.*;
import java.util.*;

public class BOJ16724_피리부는사나이 {

  static int N, M;
  static boolean[][] visited;
  static char[][] map;
  static int[] uf;
  static Map<Character, int[]> cache;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 입력 부분
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // N: 행의 수
    M = Integer.parseInt(st.nextToken()); // M: 열의 수

    map = new char[N][M];
    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = line.charAt(j);
      }
    }

    // union-find 배열 초기화
    uf = new int[N * M];
    for (int i = 0; i < N * M; i++) {
      uf[i] = i;
    }

    cache = new HashMap<>();
    cache.put('D', new int[]{1, 0});
    cache.put('U', new int[]{-1, 0});
    cache.put('L', new int[]{0, -1});
    cache.put('R', new int[]{0, 1});

    br.close();
  }

  private static void solve() {

    visited = new boolean[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        // 이미 방문한 경우 -> 탐색하지 않는다.
        if (visited[i][j]) {
          continue;
        }
        visited[i][j] = true;
        dfs(i, j);
      }
    }

    // 모든 부모의 인덱스를 추출
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < N * M; i++) {
      set.add(find(i));
    }

    // 정답 출력
    System.out.println(set.size());
  }

  private static void dfs(int y, int x) {

    int nxtY = y + cache.get(map[y][x])[0];
    int nxtX = x + cache.get(map[y][x])[1];

    // 현재 정점과 다음 정점이 이미 같은 집합이라면 -> 싸이클 발생 return
    if (find(y * M + x) == find(nxtY * M + nxtX)) {
      return;
    }

    union(y * M + x, nxtY * M + nxtX); // 합집합
    visited[nxtY][nxtX] = true; // 방문 처리
    dfs(nxtY, nxtX);
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
