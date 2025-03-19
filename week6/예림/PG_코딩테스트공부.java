package week6.예림;

public class PG_코딩테스트공부 {

    // 모든 문제를 풀 수 있는 알고력/코딩력을 얻기 위한 최소 시간
    // (초기 알고력, 초기 코딩력) --최단 시간--> (목표 알고력, 목표 코딩력)
    public int solution(int alp, int cop, int[][] problems) {
        int goalAlp = 0, goalCop = 0;
        for (int[] problem : problems) {
            goalAlp = Math.max(goalAlp, problem[0]); // 0: 필요한 알고력
            goalCop = Math.max(goalCop, problem[1]); // 1: 필요한 코딩력
        }

        // 현재 알고력/코딩력이 목표보다 크다면, DP 배열의 범위를 줄여 인덱스 넘기지 않도록 조정
        alp = Math.min(alp, goalAlp);
        cop = Math.min(cop, goalCop);

        int[][] dp = new int[goalAlp + 1][goalCop + 1]; // dp[i][j]: (알고력 i, 코딩력 j) 상태에 도달하는 데 필요한 최단 시간
        for (int i = alp; i <= goalAlp; i++) {
            for (int j = cop; j <= goalCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;

        for (int i = alp; i <= goalAlp; i++) {
            for (int j = cop; j <= goalCop; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }

                // 1. 알고리즘 공부하여 알고력 1 증가
                if (i + 1 <= goalAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }

                // 2. 코딩을 공부하여 코딩력 1 증가
                if (j + 1 <= goalCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                // 3. 문제 하나를 선택하여 알고력과 코딩력 증가
                for (int[] problem : problems) {
                    if (i < problem[0] || j < problem[1]) { // 0: 필요한 알고력 / 1: 필요한 코딩력
                        continue;
                    }
                    int alpIndex = Math.min(i + problem[2], goalAlp); // 2: 증가하는 알고력
                    int copIndex = Math.min(j + problem[3], goalCop); // 3: 증가하는 코딩력
                    dp[alpIndex][copIndex] = Math.min(dp[alpIndex][copIndex], dp[i][j] + problem[4]); // 4: 드는 시간
                }
            }
        }

        return dp[goalAlp][goalCop];
    }
}
