package 용범;

import java.io.*;
import java.util.*;

public class BOJ11582_치킨TOPN {

  static StringBuilder sb = new StringBuilder();
  static int N, K;
  static ArrayList<Integer> lst;


  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    // 입력 부분
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine()); // N: 원소의 개수
    lst = new ArrayList<>();
    // 배열 정보 입력
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      lst.add(Integer.parseInt(st.nextToken()));
    }
    K = Integer.parseInt(br.readLine());

    br.close();
  }

  private static void solve() {

    for (int i = 0; i < N; i += N / K) {
      subListSort(i, i + N / K);
    }

    // 정답 출력
    for (Integer num : lst) {
      sb.append(num).append(" ");
    }
    System.out.println(sb.toString());
  }

  private static void subListSort(int s, int e) {
    lst.subList(s, e).sort(Comparator.naturalOrder()); // [시점 - 중간] 부분 리스트 오름차순 정렬
  }

}
