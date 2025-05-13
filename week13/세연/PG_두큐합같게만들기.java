package week13.세연;

import java.util.*;

class PG_두큐합같게만들기 {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        int length = queue1.length, cnt = 0;
        int[][] matrix = {queue1, queue2};
        int[] startQ1 = {0,0}, startQ2 = {1,0};
        long sumQ1 = 0, sumQ2 = 0;
        for (int q : queue1) sumQ1 += q;
        for (int q : queue2) sumQ2 += q;
        if ((sumQ1 + sumQ2) % 2 != 0) return -1;

        while (cnt <= 4*length && !(startQ1[0] == startQ2[0] && startQ1[1] == startQ2[1])) {
            if (sumQ1 < sumQ2) {
                int tmp = matrix[startQ2[0]][startQ2[1]++];
                sumQ1 += tmp; 
                sumQ2 -= tmp;
                if (startQ2[1] == length) {startQ2[0] = (startQ2[0]+1)%2; startQ2[1] = 0;}      
            }
            else if (sumQ1 > sumQ2) {
                int tmp = matrix[startQ1[0]][startQ1[1]++];
                sumQ2 += tmp; 
                sumQ1 -= tmp;
                if (startQ1[1] == length) {startQ1[0] = (startQ1[0]+1)%2; startQ1[1] = 0;}      
            }
            else return cnt;
            cnt++;
        }

        return -1;
    }
}