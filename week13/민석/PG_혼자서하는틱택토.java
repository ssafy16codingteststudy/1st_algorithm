class Solution {
    public int solution(String[] board) {
        int oWin = 0, xWin = 0;
        int oCount = 0, xCount = 0;
        for(int y =0; y<3; y++){
            for(int x =0; x<3; x++){
                if(board[y].charAt(x) == '.') continue;
                if(board[y].charAt(x) == 'O'){ //O일때
                    oCount += 1;
                    if(y==0){
                        if(x == 0) { //대각선 확인
                            if(board[y].charAt(x) == board[y+1].charAt(x+1) && board[y].charAt(x) == board[y+2].charAt(x+2))
                                oWin += 1;
                        }
                        else if(x==2){ //대각선 확인
                            if(board[y].charAt(x) == board[y+1].charAt(x-1) && board[y].charAt(x) == board[y+2].charAt(x-2))
                                oWin += 1;
                        } 
                        if(board[y].charAt(x) == board[y+1].charAt(x) && board[y].charAt(x) == board[y+2].charAt(x)) //세로 확인
                                oWin += 1;
                    }
                    if(x==0){
                        if(board[y].charAt(x) == board[y].charAt(x+1) && board[y].charAt(x) == board[y].charAt(x+2)) //가로 확인
                                oWin += 1;
                    }
                        
                } else { //X일때
                    xCount+=1;
                    if(y==0){
                        if(x == 0) { //대각선 확인
                            if(board[y].charAt(x) == board[y+1].charAt(x+1) && board[y].charAt(x) == board[y+2].charAt(x+2))
                                xWin += 1;
                        }
                        else if(x==2){ //대각선 확인
                            if(board[y].charAt(x) == board[y+1].charAt(x-1) && board[y].charAt(x) == board[y+2].charAt(x-2))
                                xWin += 1;
                        } 
                        if(board[y].charAt(x) == board[y+1].charAt(x) && board[y].charAt(x) == board[y+2].charAt(x)) //세로 확인
                                xWin += 1;
                    }
                    if(x==0){
                        if(board[y].charAt(x) == board[y].charAt(x+1) && board[y].charAt(x) == board[y].charAt(x+2)) //가로 확인
                                xWin += 1;
                    }
                }
            }
        }
        
        
        int result = 1;
        
        if(xCount > oCount || oCount > xCount+1) 
            result = 0;
        else if(oWin > 0 && oCount-1 < xCount)
            result = 0;
        else if(xWin > 0 && xCount != oCount)
            result = 0;
        else if(oWin > 0 && xWin > 0)
            result = 0;
        
        
        return result;
    }
}