package codingTestStudy;

import java.util.*;

public class PG_도넛과막대그래프 {

    private Set<Integer> set;
    private int[][] node;

    public int[] solution(int[][] edges) {

        // 정점의 개수가 몇 개인지 모르기에 배열 길이 최대로 선언
        int n = 1_000_000;
        set = new HashSet<>();
        node = new int[n + 1][2];

        for (int i = 0; i < edges.length; i++) {
            int out = edges[i][0];
            int in = edges[i][1];

            // out ++
            node[out][1]++;
            // in ++
            node[in][0]++;

            // 정점 번호대로 순회하기 위해 set 에 저장
            set.add(in);
            set.add(out);
        }

        int start = 0, donut = 0, stick = 0, eight = 0;
        for (Integer i : set) {
            switch(node[i][1]) {
                // out 0 인 노드는 stick 뿐
                case 0 :
                    stick++;
                    break;
                // 평범한 노드
                case 1 :
                    break;
                // out 2 인 노드는 시작노드 or 8자 그래프
                case 2 :
                    // in 이 0 이면 그 노드는 시작 노드
                    if(node[i][0] == 0) {
                        start = i;
                    } else {
                        eight++;
                    }
                    break;
                // out 2 초과 라면 무조건 시작 노드
                default :
                    start = i;
                    break;
            }
            // 도넛 노드 : 시작 노드의 out 개수 - stick 개수 - 8자 그래프 개수
            donut = node[start][1] - stick - eight;
        }

        int[] answer = {start, donut, stick, eight};
        return answer;
    }
}