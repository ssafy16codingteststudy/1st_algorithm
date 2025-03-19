import java.util.*;

class Solution {
    static int maxdiff = -1;
    static int[] answer = {-1};
    
    public static int caculateScore(int[] apeach, int[] ryan) {
        int apeachSum = 0;
        int ryanSum = 0;
        
        for (int i=0;i<apeach.length;i++) {
            if (apeach[i] < ryan[i])
                ryanSum += (10 - i);
            else if (apeach[i] >= ryan[i] && apeach[i] != 0)
                apeachSum += (10 - i);
        }
        
        return ryanSum - apeachSum;
        
    }
    
    public static  int back(int idx, int[] apeach, int[] ryan, int count) {
        if (count == 0)
            return caculateScore(apeach, ryan);
        else {
             for (int i = idx;i > -1;i--) {
                 ryan[i]++;
                 int now = back(i, apeach, ryan, count - 1);
                 if (maxdiff < now && now != 0) {
                     answer = ryan.clone();
                     maxdiff = now;
                     // for (int k=0;k<11;k++)
                     // System.out.printf("%d ", answer[k]);
                     // System.out.println("");
                 }
                 ryan[i]--;
             }
        }
        return -1;
    }
    
    public static int[] solution(int n, int[] info) {
        back(10, info, new int[] {0,0,0,0,0,0,0,0,0,0,0}, n);
        
        return answer;
    }
}