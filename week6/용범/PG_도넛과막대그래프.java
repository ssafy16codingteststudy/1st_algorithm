package 용범;

import java.util.*;

class Solution {

  static int[] in_cnt;
  static int[] out_cnt;
  static int LEN;

  public int[] solution(int[][] edges) {
    int[] answer = {0, 0, 0, 0};

    LEN = 0; // 그래프의 최대 정점 숫자
    for (int[] v: edges) {
      int v1 = v[0];
      int v2 = v[1];
      LEN = Math.max(LEN, v1);
      LEN = Math.max(LEN, v2);
    }

    in_cnt = new int[LEN + 1];
    out_cnt = new int[LEN + 1];

    for (int[] v: edges) {
      int v1 = v[0]; // v1: 출발점
      int v2 = v[1]; // v2: 도착점
      out_cnt[v1]++;
      in_cnt[v2]++;
    }

    for (int i = 1; i < LEN + 1; i++) {
      // 생성 노드는 들어오는 노드가 2개 / 나가는 노드가 2개
      if (in_cnt[i] == 0 && out_cnt[i] >= 2) {
        answer[0] = i;
      }
      // 막대 그래프는 들어오는 노드가 0개 / 나가는 노드가 1개
      else if (in_cnt[i] >= 1 && out_cnt[i] == 0) {
        answer[2]++;
      }
      // 8자 그래프는 들어오는 노드가 2개 / 나가는 노드가 2개
      else if (in_cnt[i] >= 2 && out_cnt[i] == 2) {
        answer[3]++;
      }
      // 도넛 그래프는 남아있는 그래프의 개수
      int sum = answer[2] + answer[3];
      answer[1] = out_cnt[answer[0]] - sum;
    }

    return answer;
  }
}