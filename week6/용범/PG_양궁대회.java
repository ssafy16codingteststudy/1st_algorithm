package 용범;

import java.util.*;

class Solution {

  static int n, peach, sum, LEN, MAX;
  static ArrayList<Integer> res;
  static boolean[] visited;
  static int[] info, ans;

  public int[] solution(int n, int[] info) {

    this.n = n;
    this.info = info;

    LEN = info.length;
    visited = new boolean[LEN];
    res = new ArrayList<Integer>();

    // 현재 어피치 과녁의 점수 계산
    peach = 0;
    for (int i = 0; i < info.length; i++) {
      if (info[i] != 0) {
        peach += 10 - i;
      }
    }

    MAX = -1; // MAX: 최대 차이
    bt(0, peach, 0, new ArrayList<Integer>());

    // 차이를 갱신하지 못하는 경우 -> [-1] return
    if (MAX == -1) {
      return new int[]{-1};
    }

    ans = new int[LEN];
    sum = 0;
    for (Integer idx : res) {
      ans[idx] = info[idx] + 1;
      sum += ans[idx];
    }

    if (sum > n) {
      ans[LEN - 1] = ans[LEN - 1] - (sum - n);
    }

    return ans;
  }

  private static void bt(int cnt, int peach, int lion, ArrayList<Integer> lst) {

    // 과녁에 화살을 모두 발사한 경우
    if (cnt == n) {
      // 라이온의 점수가 더 큰 경우
      if (lion > peach) {
        int diff = lion - peach;
        // 최고 차이값 갱신이 가능한 경우
        if (MAX < diff) {
          MAX = diff;
          res = new ArrayList<Integer>();
          res.addAll(lst);
        }

        // 최고 차이값이 같은 경우 -> 낮은 점수를 더 많이 맞힌 경우를 return
        else if (MAX == diff) {
          int[] before = new int[LEN];
          for (Integer idx : res) {
            before[idx] = info[idx] + 1;
          }

          int[] after = new int[LEN];
          for (Integer idx : lst) {
            after[idx] = info[idx] + 1;
          }

          for (int i = LEN - 1; i >= 0; i--) {
            // 라이온의 낮은 점수 분포가 더 좋은 경우 -> 정답 갱신
            if (before[i] < after[i]) {
              res = new ArrayList<Integer>();
              res.addAll(lst);
              break;
            }
            // 어피치의 낮은 점수 분포가 더 좋은 경우 -> 어피치 승리
            else if (before[i] > after[i]) {
              break;
            }
            // 낮은 점수 분포가 동등한 경우 -> 그 다음 낮은 점수로 continue
            else {
              continue;
            }
          }
        }
      }

      return;
    }

    for (int i = 0; i < LEN; i++) {
      // 오름차순 순으로 탐색 진행 -> 최적화
      if (!lst.isEmpty() && lst.get(lst.size() - 1) >= i) {
        continue;
      }

      // 이미 방문한 경우 -> 탐색하지 않는다.
      if (visited[i]) {
        continue;
      }

      // 해당 과녁에서 어피치를 이기지 못하면 -> 탐색하지 않는다.
      if (i != LEN - 1 && info[i] >= n - cnt) {
        continue;
      }

      lst.add(i);
      visited[i] = true; // 방문 처리
      int weight = info[i] != 0 ? 10 - i : 0; // weight: 라이온의 화살로 인하여 깎이는 어피치의 점수
      int up = (i == LEN - 1) ? n - cnt : info[i] + 1; // up: 어피치를 이기기 위해서 쏴야할 화살 수
      bt(cnt + up, peach - weight, lion + (10 - i), lst);
      lst.remove(lst.size() - 1);
      visited[i] = false; // 복원 처리
    }
  }

}
