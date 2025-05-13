import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum1 = 0, sum2 = 0;

        for (int n : queue1) {
            q1.offer(n);
            sum1 += n;
        }

        for (int n : queue2) {
            q2.offer(n);
            sum2 += n;
        }

        long target = (sum1 + sum2) / 2;

        if ((sum1 + sum2) % 2 != 0) return -1;

        int max = queue1.length * 3;
        int count = 0;

        while (count <= max) {
            if (sum1 == target) return count;

            if (sum1 > target) {
                int n = q1.poll();
                q2.offer(n);
                sum1 -= n;
                sum2 += n;
            } else {
                int n = q2.poll();
                q1.offer(n);
                sum2 -= n;
                sum1 += n;
            }

            count++;
        }

        return -1;
    }
}
