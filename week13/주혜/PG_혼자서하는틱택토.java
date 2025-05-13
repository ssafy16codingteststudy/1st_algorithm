class Solution {
    public int solution(String[] board) {
        int answer = -1;
        int O = 0;
        int X = 0;
        boolean [][] OBoard = new boolean[3][3];
        boolean [][] XBoard = new boolean[3][3];
        for (int i = 0; i < 3; i++) {
            String s = board[i];
            for (int j = 0; j < 3; j++) {
                char c = s.charAt(j);
                if (c == 'O') {
                    O++;
                    OBoard[i][j] = true;
                }
                if (c == 'X') {
                    X++;
                    XBoard[i][j] = true;
                }
            }
        }

        boolean Owin = win(OBoard);
        boolean Xwin = win(XBoard);

        if (X > O) answer = 0;
        else if (Owin && Xwin) answer = 0;
        else if (Owin && O != X + 1) answer = 0;
        else if (Xwin && O != X) answer = 0;
        else if (O > X+1) answer = 0;
        else answer = 1;

        return answer;
    }
    
    static boolean win(boolean [][] playerBoard) {

        if (playerBoard[0][0] == true && playerBoard[0][1] == true && playerBoard[0][2] == true) {
            return true;
        }
        else if (playerBoard[1][0] == true && playerBoard[1][1] == true && playerBoard[1][2] == true) {
            return true;
        }
        else if (playerBoard[2][0] == true && playerBoard[2][1] == true && playerBoard[2][2] == true) {
            return true;
        }
        else if (playerBoard[0][0] == true && playerBoard[1][0] == true && playerBoard[2][0] == true) {
            return true;
        }
        else if (playerBoard[0][1] == true && playerBoard[1][1] == true && playerBoard[2][1] == true) {
            return true;
        }
        else if (playerBoard[0][2] == true && playerBoard[1][2] == true && playerBoard[2][2] == true) {
            return true;
        }
        else if (playerBoard[0][0] == true && playerBoard[1][1] == true && playerBoard[2][2] == true) {
            return true;
        }
        else if (playerBoard[0][2] == true && playerBoard[1][1] == true && playerBoard[2][0] == true) {
            return true;
        }

        else return false;
    }
}
