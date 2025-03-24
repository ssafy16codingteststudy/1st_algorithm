package week6.예림;

public class PG_양궁대회 {

    private static final int MAX_SCORE = 10;
    private static final int SIZE = 11;
    private int[] apeach;
    private int maxDiff;
    private int[] answer;

    public int[] solution(int n, int[] info) {
        this.apeach = info;
        this.maxDiff = 0;
        this.answer = new int[SIZE];

        int[] ryan = new int[SIZE];
        dfs(ryan, 0, n);

        return maxDiff == 0 ? new int[]{-1} : answer;
    }

    private void dfs(int[] ryan, int current, int remain) { // 남은 화살 수, 라이언 배열
        if (current == MAX_SCORE) {
            ryan[current] = remain;
            updateAnswer(ryan);
            return;
        }

        int arrowForWin = apeach[current] + 1;
        if (arrowForWin <= remain) { // 이기는 경우
            ryan[current] = arrowForWin;
            dfs(ryan, current + 1, remain - arrowForWin);
            ryan[current] = 0;
        }

        dfs(ryan, current + 1, remain); // 지는 경우
    }

    private void updateAnswer(int[] ryan) {
        int ryanScore = 0, apeachScore = 0;
        for (int i = 0; i <= MAX_SCORE; i++) {
            if (ryan[i] > apeach[i]) {
                ryanScore += MAX_SCORE - i;
            } else if (apeach[i] > 0) {
                apeachScore += MAX_SCORE - i;
            }
        }

        int scoreDiff = ryanScore - apeachScore;
        if (scoreDiff > maxDiff) {
            maxDiff = scoreDiff;
            answer = ryan.clone();
        } else if (scoreDiff == maxDiff) {
            for (int i = MAX_SCORE; i >= 0; i--) {
                if (ryan[i] > answer[i]) {
                    answer = ryan.clone();
                    break;
                } else if (ryan[i] < answer[i]) {
                    break;
                }
            }
        }
    }
}
