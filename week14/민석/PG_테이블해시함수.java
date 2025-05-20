import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (a,b) -> {
            if(a[col-1] == b[col-1])
                return b[0] - a[0];
            return a[col-1] - b [col-1];
        });
        
        for(int i=row_begin-1; i<row_end; i++){
            if(i == row_begin-1)
            {
                for(int j=0; j<data[i].length; j++)
                    answer += data[i][j] % (i+1); 
                continue;
            }
            int b = 0;
            for(int j=0; j<data[i].length; j++)
                b += data[i][j] % (i+1);
            answer = answer^b;
        }
        
        
        return answer;
    }
    
}
