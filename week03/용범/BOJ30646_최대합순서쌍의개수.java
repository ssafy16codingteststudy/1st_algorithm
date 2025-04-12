package 용범;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class BOJ30646_최대합순서쌍의개수 {

  static int N, CNT, left, right;
  static long SUM, MAX;
  static int[] arr;
  static long[] prefix;
  static Map<Integer, ArrayList<Integer>> cache = new HashMap<>();

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    // 입력 부분
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine()); // N: 배열의 크기
    arr = new int[N];
    cache = new HashMap<>(); // HashMap 초기화
    st = new StringTokenizer(br.readLine());

    MAX = 0;
    CNT = 0;
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      MAX = Math.max(MAX, arr[i]);
      // 이미 해당 키값이 존재한다면 -> 인덱스 추가
      if (cache.containsKey(arr[i])) {
        cache.get(arr[i]).add(i);
      }
      // 해당 키값이 존재하지 않으면 -> 새로운 ArrayList 생성 후, 인덱스 추가
      else {
        cache.put(arr[i], new ArrayList<>());
        cache.get(arr[i]).add(i);
      }
    }

    // 누적합 초기화
    prefix = new long[N + 1];
    prefix[1] = arr[0];
    for (int i = 2; i <= N; i++) {
      prefix[i] = prefix[i - 1] + arr[i - 1];
    }

    br.close();
  }

  private static void solve() {

    for (Entry<Integer, ArrayList<Integer>> entry : cache.entrySet()) {
      ArrayList<Integer> idx = entry.getValue();
      left = idx.get(0);
      right = idx.get(idx.size() - 1);
      SUM = prefix[right + 1] - prefix[left];

      // 최댓값을 갱신할 수 있다면 -> 최댓값 갱신 및 개수 초기화
      if (MAX < SUM) {
        CNT = 1;
        MAX = SUM;
      }
      // 최댓값과 같은 경우라면 -> 개수 초기화
      else if (MAX == SUM) {
        CNT++;
      }
    }

    System.out.printf("%d %d\n", MAX, CNT);
  }

}
