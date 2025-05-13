import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        
        long sum1 = 0, sum2 = 0;
        for (int num : queue1) {
            q1.add(num);
            sum1 += num;
        }
        for (int num : queue2) {
            q2.add(num);
            sum2 += num;
        }
        
        if ((sum1 + sum2) % 2 != 0) {
            return -1;
        }
        long target = (sum1 + sum2) / 2;
        
        int limit = (q1.size() + q2.size()) * 2;
        int count = 0;
        
        while (count <= limit) {
            if (sum1 == target) {
                return count;
            }
            
            if (sum1 > target) {
                int value = q1.poll();
                q2.add(value);
                sum1 -= value;
                sum2 += value;
            } else {
                int value = q2.poll();
                q1.add(value);
                sum1 += value;
                sum2 -= value;
            }
            count++;
        }
        
        return -1;
    }
}
