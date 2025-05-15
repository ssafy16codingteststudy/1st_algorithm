package week13.일우;

public class 틱택토 {
    class Solution {
        public int solution(String[] board) {
            // 1) 보드 초기화 및 O/X 개수 세기
            char[][] matrix = new char[3][3];
            int oCount = 0, xCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    matrix[i][j] = board[i].charAt(j);
                    if (matrix[i][j] == 'O') oCount++;
                    else if (matrix[i][j] == 'X') xCount++;
                }
            }

            // 2) O와 X의 승리 여부 판별
            boolean oWin = isWin(matrix, 'O');
            boolean xWin = isWin(matrix, 'X');

            // 3) 기본 턴 검사: O는 X와 같거나 1개 많아야 함
            if (!(oCount == xCount || oCount == xCount + 1)) {
                return 0;
            }
            // 4) 동시에 승리한 경우 불가능
            if (oWin && xWin) {
                return 0;
            }
            // 5) O가 승리했다면 O가 한 수 더 둔 상태여야 함
            if (oWin && oCount != xCount + 1) {
                return 0;
            }
            // 6) X가 승리했다면 O와 동일한 수를 둔 상태여야 함
            if (xWin && oCount != xCount) {
                return 0;
            }

            return 1;
        }

        // 특정 플레이어(p)가 빙고를 만들었는지 검사
        private boolean isWin(char[][] m, char p) {
            // 가로 검사
            for (int i = 0; i < 3; i++) {
                if (m[i][0] == p && m[i][1] == p && m[i][2] == p) {
                    return true;
                }
            }
            // 세로 검사
            for (int j = 0; j < 3; j++) {
                if (m[0][j] == p && m[1][j] == p && m[2][j] == p) {
                    return true;
                }
            }
            // 대각선 검사
            if (m[0][0] == p && m[1][1] == p && m[2][2] == p) return true;
            if (m[0][2] == p && m[1][1] == p && m[2][0] == p) return true;

            return false;
        }
    }

}
