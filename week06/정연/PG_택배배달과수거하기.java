import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int delCap = 0;
        int pickCap = 0;
        for (int i = n - 1; i > -1; i--) {
            if (deliveries[i] == 0 && pickups[i] == 0)
                continue;
            delCap += deliveries[i];
            pickCap += pickups[i];

            int k = Math.max((int) Math.ceil((double) delCap / cap), (int) Math.ceil((double) pickCap / cap));
            int l = k * (i + 1) * 2;
            delCap -= k * cap;
            pickCap -= k * cap;
            // System.out.println(k);
            // System.out.println(l);
            answer += l;
        }

        return answer;
    }
}