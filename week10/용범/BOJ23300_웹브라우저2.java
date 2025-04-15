package 용범;

import java.io.*;
import java.util.*;

public class BOJ23300_웹브라우저2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N, Q;
  static ArrayDeque<Integer> front;
  static LinkedList<Integer> back;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    Q = Integer.parseInt(st.nextToken());

    front = new ArrayDeque<>();
    back = new LinkedList<>();
  }

  private static void solve() throws IOException {

    int currPage = -1;
    for (int i = 0; i < Q; i++) {
      st = new StringTokenizer(br.readLine());
      char command = st.nextToken().charAt(0);

      switch (command) {
        case 'B':
          if (back.isEmpty()) {
            continue;
          }
          front.offerFirst(currPage);
          currPage = back.pollLast();
          break;
        case 'F':
          if (front.isEmpty()) {
            continue;
          }
          back.offerLast(currPage);
          currPage = front.pollFirst();
          break;
        case 'A':
          front.clear();
          int nxtPage = Integer.parseInt(st.nextToken());
          if (currPage != -1) {
            back.offerLast(currPage);
          }
          currPage = nxtPage;
          break;
        case 'C':
          for (int idx = back.size() - 1; idx >= 1; idx--) {
            int curr = back.get(idx);
            int nxt = back.get(idx - 1);
            if (curr == nxt) {
              back.remove(idx);
            }
          }
          break;
      }
    }

    // 정답 양식 저장
    sb.append(currPage).append("\n");
    if (back.isEmpty()) {
      sb.append("-1").append("\n");
    } else {
      while (!back.isEmpty()) {
        sb.append(back.pollLast()).append(" ");
      }
      sb.append("\n");
    }

    if (front.isEmpty()) {
      sb.append("-1").append("\n");
    } else {
      while (!front.isEmpty()) {
        sb.append(front.pollFirst()).append(" ");
      }
      sb.append("\n");
    }

    // 정답 출력
    System.out.println(sb);
    br.close();
  }

}
