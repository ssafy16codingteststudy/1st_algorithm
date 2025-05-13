class Solution {
    
    public int solution(String[] board) {
        int oCount= 0, xCount = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'O') {
                    oCount++;
                } else if (c == 'X') {
                    xCount++;
                }
            }
        }
        
        if (oCount != xCount && oCount != xCount + 1) { // 턴 순서가 잘못됨
            return 0;
        }
        
        boolean oWin = isWin(board, 'O');
        boolean xWin = isWin(board, 'X');
        
        if (oWin && xWin) { // 둘 다 이긴 경우
            return 0;
        }
        
        if (oWin && oCount != xCount + 1) { // O가 이겼으면 o == x + 1
            return 0;
        }
        
        if (xWin && oCount != xCount) { // X가 이겼으면 o == x
            return 0;
        }
        
        return 1;
    }
    
    private boolean isWin(String[] board, char c) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c) { // 가로 빙고
                return true;
            }
            if (board[0].charAt(i) == c && board[1].charAt(i) == c && board[2].charAt(i) == c) { // 세로 빙고
                return true;
            }
        }
        if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) { // 대각성 빙고
            return true;
        }
        if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c) { // 대각성 빙고
            return true;
        }
        return false;
    }
}
