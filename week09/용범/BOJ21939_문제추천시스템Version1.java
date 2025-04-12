package 용범;

import java.io.*;
import java.util.*;

public class BOJ21939_문제추천시스템Version1 {

  static class Problem {

    int p, l;

    Problem(int p, int l) {
      this.p = p;
      this.l = l;
    }
  }

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N, P, L, M, x;
  static String command;
  static PriorityQueue<Problem> hard;
  static PriorityQueue<Problem> easy;
  static Map<Integer, Integer> problems;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    N = Integer.parseInt(br.readLine());

    problems = new HashMap<>();
    hard = new PriorityQueue<>(
        (a, b) -> {
          if (a.l != b.l) {
            return b.l - a.l; // 난이도 높은 순
          }
          return b.p - a.p;   // 문제 번호 큰 순
        }
    );

    easy = new PriorityQueue<>(
        (a, b) -> {
          if (a.l != b.l) {
            return a.l - b.l; // 난이도 낮은 순
          }
          return a.p - b.p;   // 문제 번호 작은 순
        }
    );

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      P = Integer.parseInt(st.nextToken()); // P: 문제 번호
      L = Integer.parseInt(st.nextToken()); // L: 문제 난이도
      hard.add(new Problem(P, L));
      easy.add(new Problem(P, L));
      problems.put(P, L);
    }

    M = Integer.parseInt(br.readLine()); // M: 명령문의 개수
  }

  private static void solve() throws IOException {

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      command = st.nextToken();
      switch (command) {
        // command 명령문인 경우
        case "recommend":
          x = Integer.parseInt(st.nextToken());
          // 추천 문제 리스트에서 가장 어려운 문제의 번호 출력
          if (x == 1) {
            while (true) {
              Problem problem = hard.poll();
              // 문제 번호와 난이도가 일치하는 경우 -> 출력값 저장
              if (problems.containsKey(problem.p) && problems.get(problem.p) == problem.l) {
                sb.append(problem.p).append("\n");
                hard.add(problem); // 다시 저장
                break;
              }
            }
          }
          // 추천 문제 리스트에서 가장 쉬운 문제 번호를 ㅊ출력
          else {
            while (true) {
              Problem problem = easy.poll();
              // 문제 번호와 난이도가 일치하는 경우 -> 출력값 저장
              if (problems.containsKey(problem.p) && problems.get(problem.p) == problem.l) {
                sb.append(problem.p).append("\n");
                easy.add(problem);
                break;
              }
            }
          }
          break;
        // add 명령문인 경우
        case "add":
          P = Integer.parseInt(st.nextToken());
          L = Integer.parseInt(st.nextToken());
          hard.add(new Problem(P, L));
          easy.add(new Problem(P, L));
          problems.put(P, L);
          break;
        // solved 명령문인 경우
        case "solved":
          P = Integer.parseInt(st.nextToken());
          problems.remove(P);
          break;
      }
    }

    // 정답 출력 및 입력 버퍼 close
    System.out.print(sb);
    br.close();
  }
}