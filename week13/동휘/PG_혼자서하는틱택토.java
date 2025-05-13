package May1;


import java.util.*;


public class PG_혼자서하는틱택토 {

	class Solution {
	    public int solution(String[] board) {
	        int answer = 1;
		    int oCount = 0;
		    int xCount = 0;
		    int oBingo = 0;
		    int xBingo = 0;
		    for(int i = 0; i < 3; i++){
		        for(int j = 0; j < 3; j++){
		            if(board[i].charAt(j) == 'O'){
		                oCount++;
		            }else if(board[i].charAt(j) == 'X')
		                xCount++;
		        }
		    }
		    for(int i = 0; i < 3; i++){
		        char c = board[i].charAt(0);
		        if(c != '.'){
		            if(board[i].charAt(1) == c && board[i].charAt(2) == c){
		                if(c == 'O'){
		                    oBingo++;
		                }else{
		                    xBingo++;
		                }
		            }
		        }
	
		    }
		    for(int i = 0; i < 3; i++){
		        char c = board[0].charAt(i);
		        if(c != '.'){
		            if(board[1].charAt(i) == c && board[2].charAt(i) == c){
		                if(c == 'O'){
		                    oBingo++;
		                }else{
		                    xBingo++;
		                }
		            }
		        }
		    }
		    if(board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)){
		        if(board[1].charAt(1) == 'O'){
		            oBingo++;
		        }else if(board[1].charAt(1) == 'X'){
		            xBingo++;
		        }
		    }
		        if(board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)){
		        if(board[1].charAt(1) == 'O'){
		            oBingo++;
		        }else if(board[1].charAt(1) == 'X'){
		            xBingo++;
		        }
		    }
		    if(xCount > oCount){
		        answer = 0;
		    }
		    if(oCount - xCount > 1){
		        answer = 0;
		    }
		    if(oBingo > 0 && xBingo > 0){
		        answer = 0;
		    }
		    if(xBingo == 1 && oCount != xCount){
		        answer = 0;
		    }
		    if(oBingo > 2 || xBingo > 1){
		        answer = 0;
		    }
		    if(oBingo == 1 && oCount - 1 != xCount){
		        answer = 0;
		    }
		    if(oBingo == 2 && oCount + xCount != 9){
		        answer = 0;
		    }
		    return answer;
	    }
	}
	

}
