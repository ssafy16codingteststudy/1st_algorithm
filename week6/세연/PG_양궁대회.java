package week6.세연;

public class PG_양궁대회 {
    static int[] answer = {-1};
    static int[] visited = new int[11];
    static int[] apeech;
    static int max = 0, arrow;

    public int[] solution(int n, int[] info) {
        apeech = info.clone();
        arrow = n;
        backtrack(0, 9);
        
        return answer;
    }

    public static void backtrack(int cnt, int idx ){
        if (cnt == arrow || idx < 0) { // 화살 다 쐈거나, 남은 화살 다 0점에 쏘는 경우
            int diff = calc(); // 점수 차 계산
            if (max < diff || (diff == max && answer[answer.length-1] < arrow-cnt)) {  // 지금 경우의 점수 차가 더 크거나
                                                                                       // 점수 차는 동일하지만 지금 경우에 0을 쏜 개수가 더 많은 경우
                max = diff; // 최대 차이 경우의수 업데이트
                answer = visited.clone();
                answer[10] = arrow-cnt; // 0점은 남은 화살을 다 넣어야하므로 종료조건에 별도 처리
            }
        }
        // 점수가 낮은 것 부터 조회 (점수 차 같을 때 낮은 점수가 많은 경우를 반환해야하기 때문)
        // 현재 점수 10-idx ~ 10점 까지 조회
        for (int i = idx; i >= 0; i--) { 
            if (apeech[i] >= arrow - cnt) {continue;}
            visited[i] = apeech[i] + 1; // i(점수 10-i) 땄을 때 처리
            backtrack(cnt + apeech[i] + 1, i-1); //  i(점수 10-i) 땄을 때의 가능한 경우의 수
            visited[i] = 0; // i(점수 10-i) 안 딴 상태로 변경
        }
    }

    public static int calc() {
        int score = 0;
        for (int i = 0; i < 11; i++) {
            if (visited[i] == 0 && apeech[i] == 0) continue;
            if (visited[i] > apeech[i]) score += (10 - i);
            else if (visited[i] <= apeech[i]) score -= (10 - i);
        }
        
        return score > 0? score:-1;
    }
}
